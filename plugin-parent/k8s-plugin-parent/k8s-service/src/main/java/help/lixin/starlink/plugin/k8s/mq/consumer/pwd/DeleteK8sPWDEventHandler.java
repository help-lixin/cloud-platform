package help.lixin.starlink.plugin.k8s.mq.consumer.pwd;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.DELETE_PWD_EVENT_TOPIC;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.service.impl.K8sSecretService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialDeleteEvent;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 上午10:17
 * @Description
 */
public class DeleteK8sPWDEventHandler {
    private Logger logger = LoggerFactory.getLogger(DeleteK8sPWDEventHandler.class);

    private final AbstractServiceFactory k8sServiceFactory;

    public DeleteK8sPWDEventHandler(AbstractServiceFactory k8sServiceFactory) {
        this.k8sServiceFactory = k8sServiceFactory;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
            // 聚合根对象
            .forAggregateType(DELETE_PWD_EVENT_TOPIC)
            // 事件处理
            .onEvent(SysUserNamePasswordCredentialDeleteEvent.class, this::deleteK8sPWDCredential)
            //
            .build();
    }

    public void deleteK8sPWDCredential(DomainEventEnvelope<SysUserNamePasswordCredentialDeleteEvent> event) {
        SysUserNamePasswordCredentialDeleteEvent credentialSpecialCreateEvent = event.getEvent();

        if (!credentialSpecialCreateEvent.getPluginCode().equals("k8s")) {
            return;
        }

        String credentialKey = credentialSpecialCreateEvent.getCredentialKey();
        String instanceCode = credentialSpecialCreateEvent.getInstanceCode();

        logger.info("k8s凭证key为[{}]的凭证开始消费", credentialKey);

        K8sSecretService k8sSecretService = k8sServiceFactory.getInstance(instanceCode, K8sSecretService.class);

        K8sAppDTO k8sAppDTO = new K8sAppDTO();

        k8sAppDTO.setInstanceCode(instanceCode);
        k8sAppDTO.setNamespace(credentialSpecialCreateEvent.getNameSpace());
        k8sAppDTO.setName(credentialKey);

        k8sSecretService.deleteSecret(k8sAppDTO);

        logger.info("凭证key为[{}]的凭证删除成功", credentialKey);

    }
}
