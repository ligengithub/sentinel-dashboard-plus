package com.alibaba.csp.sentinel.dashboard;

import com.alibaba.csp.sentinel.dashboard.dao.MetricEntityDao;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author ligen
 * @title: Test
 * @projectName sentinel-dashboard
 * @description:
 * @date 2020/3/3111:53
 */

@RestController
public class Test {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    MetricEntityDao metricEntityDao;


    @GetMapping("/test")
    public Date test() {
        logger.info("测试保存数据");
        metricEntityDao.save(new MetricEntity(){{setGmtCreate(new Date());}});
        return new Date();
    }
}
