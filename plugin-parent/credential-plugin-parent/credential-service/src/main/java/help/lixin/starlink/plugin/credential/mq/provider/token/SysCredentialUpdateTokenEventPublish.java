package help.lixin.starlink.plugin.credential.mq.provider.token;

import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialUpdateEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static help.lixin.starlink.plugin.credential.constants.CredentialConstants.CREATE_TOKEN_EVENT_TOPIC;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午5:01
 * @Description
 */
public class SysCredentialUpdateTokenEventPublish {
    private Logger logger = LoggerFactory.getLogger(SysCredentialUpdateTokenEventPublish.class);

    private DomainEventPublisher domainEventPublisher;

    public void updateToken(SysTokenCredentialUpdateEvent tokenCredentialUpdateEvent) {
        String aggregateType = CREATE_TOKEN_EVENT_TOPIC;

        String credentialKey = tokenCredentialUpdateEvent.getCredentialKey();

        // 发布事件.
        domainEventPublisher.publish(
                //
                aggregateType,
                //
                credentialKey,
                //
                Collections.singletonList(tokenCredentialUpdateEvent));
        logger.info("发送更新token——凭证key为[{}]的消息成功", credentialKey);
    }

    public SysCredentialUpdateTokenEventPublish(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }
}
