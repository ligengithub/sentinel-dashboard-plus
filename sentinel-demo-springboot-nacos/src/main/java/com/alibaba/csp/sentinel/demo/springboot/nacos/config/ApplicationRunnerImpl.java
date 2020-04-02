package com.alibaba.csp.sentinel.demo.springboot.nacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author lnj
 * createTime 2018-11-07 22:37
 **/
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Autowired
    private NacosStartService nacosStartService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        nacosStartService.initNacosConfigFromNacos();
    }
}