package com.alibaba.csp.sentinel.demo.springboot.nacos.inti;

import com.alibaba.csp.sentinel.demo.springboot.nacos.config.NacosStartService;
import com.alibaba.csp.sentinel.init.InitFunc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lnj
 * createTime 2018-11-07 22:37
 **/
@Component
public class DemoNacosInitFunc implements InitFunc {

    @Autowired
    private NacosStartService nacosStartService;

    @Override
    public void init() throws Exception {
        nacosStartService.initNacosConfigFromNacos();
    }
}