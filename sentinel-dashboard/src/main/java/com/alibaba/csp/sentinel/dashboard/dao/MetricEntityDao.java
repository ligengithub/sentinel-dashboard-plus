package com.alibaba.csp.sentinel.dashboard.dao;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author ligen
 * @title: MertricEntityDao
 * @projectName sentinel-dashboard
 * @description:
 * @date 2020/3/319:19
 */
@Component
public class MetricEntityDao {

//    @Autowired
//    private MongoTemplate mongoTemplate;

    public void save(MetricEntity metric) {
//        mongoTemplate.insert(metric);
    }
//
//    public void savaAll(List<MetricEntity> metricEntities) {
//        metricEntities.forEach(m -> mongoTemplate.save(m));
//    }
//
    public List<MetricEntity> queryByAppAndResourceBetween(String app, String resource,
                                                           long startTime, long endTime) {
        Criteria criteria = new Criteria();
        criteria.and("app").is(app)
                .and("resource").is(resource)
                .and("timestamp").gt(new Date( startTime)).lt(new Date(endTime));
        Query query = new Query(criteria);

//        return mongoTemplate.find(query, MetricEntity.class);
        return null;
    }

}
