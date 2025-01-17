package help.lixin.starlink.plugin.k8s.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.starlink.plugin.k8s.constants.Constant;
import help.lixin.starlink.plugin.k8s.service.IDeploymentApiService;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.StatusDetails;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import io.fabric8.kubernetes.api.model.extensions.DeploymentRollbackBuilder;
import io.fabric8.kubernetes.api.model.extensions.RollbackConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.AppsAPIGroupDSL;
import io.fabric8.kubernetes.client.utils.Serialization;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DeploymentApiService implements IDeploymentApiService {

    private KubernetesClient client;

    public DeploymentApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public Deployment createDeployment(Deployment deployment) {
        String namespace = Constant.DEFAULT_NAMESPACE;
        if (null != deployment.getMetadata().getNamespace()) {
            namespace = deployment.getMetadata().getNamespace();
        }
        return client.apps().deployments().inNamespace(namespace).resource(deployment).create();
    }

    @Override
    public Deployment updateDeployment(Deployment deployment) {
        ObjectMeta metadata = deployment.getMetadata();
        return client.apps().deployments().inNamespace(metadata.getNamespace()).withName(metadata.getName())
            .edit(d -> new DeploymentBuilder(deployment).build());
    }

    @Override
    public Deployment apply(Deployment deployment) {
        Assert.notNull(deployment.getMetadata().getName(), "deployment.metadata.name is require");
        // 配置默认的命名空间
        if (null == deployment.getMetadata().getNamespace()) {
            deployment.getMetadata().setNamespace(Constant.DEFAULT_NAMESPACE);
        }
        try {
            // YAML描述文件
            String ymlDesc = deploymentConvertYAML(deployment);
            ByteArrayInputStream ymlStream = new ByteArrayInputStream(ymlDesc.getBytes(StandardCharsets.UTF_8));
            Deployment newDeploy = client.apps().deployments().load(ymlStream).createOrReplace();
            return newDeploy;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Deployment apply(String yamlContent) {
        Deployment deployment = null;
        try {
            deployment = yamlConvertDeployment(yamlContent);
            Assert.notNull(deployment.getMetadata().getName(), "deployment.metadata.name is require");
            // 配置默认的命名空间
            if (null == deployment.getMetadata().getNamespace()) {
                deployment.getMetadata().setNamespace(Constant.DEFAULT_NAMESPACE);
            }
            ByteArrayInputStream ymlStream = new ByteArrayInputStream(yamlContent.getBytes(StandardCharsets.UTF_8));
            client.apps().deployments().load(ymlStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Deployment apply(File yamlFile) throws IOException {
        if (yamlFile.exists()) {
            StringWriter writer = new StringWriter();
            IOUtils.copy(new FileInputStream(yamlFile), writer, StandardCharsets.UTF_8);
            return this.apply(writer.toString());
        }
        return null;
    }

    @Override
    public String deploymentConvertYAML(Deployment deployment) throws JsonProcessingException {
        ObjectMapper mapper = Serialization.yamlMapper();
        String yaml = mapper.writeValueAsString(deployment);
        return yaml;
    }

    @Override
    public Deployment yamlConvertDeployment(String yaml) throws JsonProcessingException {
        Deployment deployment = Serialization.yamlMapper().readValue(yaml, Deployment.class);
        return deployment;
    }

    @Override
    public Deployment queryDeployment(String namespace, String deployName) {
        Deployment deployment = client.apps().deployments().inNamespace(namespace).withName(deployName).get();
        return deployment;
    }

    @Override
    public DeploymentList queryDeployments(String namespace) {
        return client.apps().deployments().inNamespace(namespace).list();
    }

    @Override
    public Boolean deleteDeployment(String namespace, String deployName) {
        List<StatusDetails> statusDetails =
            client.apps().deployments().inNamespace(namespace).withName(deployName).delete();
        return null != statusDetails && statusDetails.size() > 0;
    }

    @Override
    public Deployment scale(String namespace, String deployName, int scale, boolean wait) {
        namespace = checkAndReturnNamespace(namespace, deployName);
        AppsAPIGroupDSL apps = client.apps();
        Deployment deployment = apps.deployments().inNamespace(namespace).withName(deployName).scale(scale, wait);
        return deployment;
    }

    @Override
    public List<Deployment> showHistory(String namespace, String deployName) {
        AppsAPIGroupDSL apps = client.apps();
        DeploymentList list = apps.deployments().inNamespace(namespace).list();
        List<Deployment> resultList = new ArrayList<>();
        for (Deployment item : list.getItems()) {
            if (item.getMetadata().getName().equals(deployName)) {
                item.getMetadata().getAnnotations().get("deployment.kubernetes.io/revision");
            }
        }
        return resultList;
    }

    @Override
    public Status rollBack(String namespace, String deployName, Long version) {
        return client.apps().deployments().inNamespace(namespace).withName(deployName)
            .rollback(new DeploymentRollbackBuilder()
                .withRollbackTo(new RollbackConfigBuilder().withRevision(6l).build()).build());
    }

    protected String checkAndReturnNamespace(String namespace, String deployName) {
        namespace = null == namespace ? Constant.DEFAULT_NAMESPACE : namespace;
        Assert.notNull(deployName, "deployName is require");
        return namespace;
    }
}
