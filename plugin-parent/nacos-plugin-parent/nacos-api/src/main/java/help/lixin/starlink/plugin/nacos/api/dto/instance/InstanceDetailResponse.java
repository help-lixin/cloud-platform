/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package help.lixin.starlink.plugin.nacos.api.dto.instance;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Instance.
 *
 * @author nkorange
 */
@JsonInclude(Include.NON_NULL)
public class InstanceDetailResponse implements Serializable {
    
    private static final long serialVersionUID = -742906310567291979L;
    
    /**
     * unique id of this instance.
     */
    private String instanceId;
    
    /**
     * instance ip.
     */
    private String ip;
    
    /**
     * instance port.
     */
    private int port;
    
    /**
     * instance weight.
     */
    private double weight = 1.0D;
    
    /**
     * instance health status.
     */
    private boolean healthy = true;
    
    /**
     * If instance is enabled to accept request.
     */
    private boolean enabled = true;
    
    /**
     * If instance is ephemeral.
     *
     * @since 1.0.0
     */
    private boolean ephemeral = true;
    
    /**
     * cluster information of instance.
     */
    private String clusterName;
    
    /**
     * Service information of instance.
     */
    private String serviceName;
    
    /**
     * user extended attributes.
     */
    private Map<String, String> metadata = new HashMap<>();


    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(boolean ephemeral) {
        this.ephemeral = ephemeral;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
