package help.lixin.k8s.action;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class NamespaceTest extends BaseTest {

    @Test
    public void testCreateNamespace() {
//        NonNamespaceOperation<Namespace, NamespaceList, Resource<Namespace>> namespaces = client.namespaces();
        // NonNamespaceOperation<Namespace, NamespaceList, DoneableNamespace, Resource<Namespace, DoneableNamespace>> namespaceOperation = client.namespaces();
        //Namespace namespace = namespaceOperation.create(new NamespaceBuilder().withApiVersion("v1").withKind("Namespace").withStatus(new NamespaceStatusBuilder().withPhase("Active").build()).withMetadata(new ObjectMetaBuilder().withName("dev").build()).build());
        //Assert.assertEquals("dev", namespace.getMetadata().getName());
    }

    @Test
    public void testNamespaceList() throws Exception {
        // NonNamespaceOperation<Namespace, NamespaceList, DoneableNamespace, Resource<Namespace, DoneableNamespace>> namespaceOperation = client.namespaces();
//        NamespaceList list = namespaceOperation.list();
//        List<Namespace> namespaces = list.getItems();

        NamespaceList list = client.namespaces().list();
        List<Namespace> namespaces = list.getItems();

        // default           Active   6h11m
        // kube-node-lease   Active   6h11m
        // kube-public       Active   6h11m
        // kube-system       Active   6h11m
        Assert.assertEquals(5, namespaces.size());
    }


    @Test
    public void testGetNamespace() throws Exception {
//        Resource<Namespace, DoneableNamespace> devNamespace = client.namespaces().withName("default");
//        Assert.assertEquals("default", devNamespace.get().getMetadata().getName());
    }

    @Test
    public void testDeleteNamespace() throws Exception {
//        NonNamespaceOperation<Namespace, NamespaceList, DoneableNamespace, Resource<Namespace, DoneableNamespace>> namespaceOperation = client.namespaces();
//        Boolean isSuccess = namespaceOperation.delete(new NamespaceBuilder().withApiVersion("v1").withKind("Namespace").withStatus(new NamespaceStatusBuilder().withPhase("Active").build()).withMetadata(new ObjectMetaBuilder().withName("dev").build()).build());
//        Assert.assertEquals(true, isSuccess);
    }
}