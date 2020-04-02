package com.alibaba.csp.sentinel.demo.springboot.nacos.config;

import com.alibaba.cloud.sentinel.SentinelProperties;
import com.alibaba.cloud.sentinel.datasource.RuleType;
import com.alibaba.cloud.sentinel.datasource.config.NacosDataSourceProperties;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * @author lnj
 * createTime 2018-11-07 22:37
 **/
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SentinelProperties sentinelProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("[NacosSource初始化,从Nacos中获取熔断规则]");
        sentinelProperties.getDatasource().entrySet().stream().filter(map -> {
            return map.getValue().getNacos() != null;
        }).forEach(map -> {
            NacosDataSourceProperties nacos = map.getValue().getNacos();
            Properties properties = new Properties() {{
                setProperty("serverAddr", nacos.getServerAddr());
                setProperty("namespace", "31b168cb-51ee-4b8b-adfa-29b42d4afa0b");
            }};
            if (Objects.equals(nacos.getRuleType(), RuleType.FLOW)) {
                ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties,
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new com.alibaba.fastjson.TypeReference<List<FlowRule>>() {
                        }));
                FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
            }

            if (Objects.equals(nacos.getRuleType(), RuleType.DEGRADE)) {
                ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(properties,
                        nacos.getGroupId(), nacos.getDataId(),
                        source -> JSON.parseObject(source, new com.alibaba.fastjson.TypeReference<List<DegradeRule>>() {
                        }));
                DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
            }


        });
    }
}