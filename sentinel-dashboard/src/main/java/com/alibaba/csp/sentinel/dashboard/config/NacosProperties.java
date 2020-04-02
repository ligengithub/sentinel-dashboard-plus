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
package com.alibaba.csp.sentinel.dashboard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Component
@ConfigurationProperties(prefix = "nacos")
public class NacosProperties {

    private String serverAddr;

    private String namespace;

    private String groupId = "DEFAULT_GROUP";

    private String flowDataIdPrefix = "-flow-rules";

    private String degradeDataIdPrefix = "-degrade-rules";

    private String paramFlowDataIdPrefix = "-param-rules";

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getFlowDataIdPrefix() {
        return flowDataIdPrefix;
    }

    public void setFlowDataIdPrefix(String flowDataIdPrefix) {
        this.flowDataIdPrefix = flowDataIdPrefix;
    }

    public String getDegradeDataIdPrefix() {
        return degradeDataIdPrefix;
    }

    public void setDegradeDataIdPrefix(String degradeDataIdPrefix) {
        this.degradeDataIdPrefix = degradeDataIdPrefix;
    }

    public String getParamFlowDataIdPrefix() {
        return paramFlowDataIdPrefix;
    }

    public void setParamFlowDataIdPrefix(String paramFlowDataIdPrefix) {
        this.paramFlowDataIdPrefix = paramFlowDataIdPrefix;
    }



//    public static final String CLUSTER_MAP_DATA_ID_POSTFIX = "-cluster-map";
//
//    /**
//     * cc for `cluster-client`
//     */
//    public static final String CLIENT_CONFIG_DATA_ID_POSTFIX = "-cc-config";
//    /**
//     * cs for `cluster-server`
//     */
//    public static final String SERVER_TRANSPORT_CONFIG_DATA_ID_POSTFIX = "-cs-transport-config";
//    public static final String SERVER_FLOW_CONFIG_DATA_ID_POSTFIX = "-cs-flow-config";
//    public static final String SERVER_NAMESPACE_SET_DATA_ID_POSTFIX = "-cs-namespace-set";
}
