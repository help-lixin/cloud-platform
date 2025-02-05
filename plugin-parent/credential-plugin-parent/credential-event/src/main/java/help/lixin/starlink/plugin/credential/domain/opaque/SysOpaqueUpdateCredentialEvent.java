package help.lixin.starlink.plugin.credential.domain.opaque;

import help.lixin.starlink.plugin.credential.event.opaque.ISysOpaqueCredentialEvent;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysOpaqueUpdateCredentialEvent implements ISysOpaqueCredentialEvent {

    private Long id;

    private Map<String,String> dataMap;

    private String nameSpace;

    private String instanceCode;

    private String pluginCode;

    private String credentialKey;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getPluginCode() {
        return pluginCode;
    }

    public void setPluginCode(String pluginCode) {
        this.pluginCode = pluginCode;
    }

    public String getCredentialKey() {
        return credentialKey;
    }

    public void setCredentialKey(String credentialKey) {
        this.credentialKey = credentialKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, String> dataMap) {
        this.dataMap = dataMap;
    }
}
