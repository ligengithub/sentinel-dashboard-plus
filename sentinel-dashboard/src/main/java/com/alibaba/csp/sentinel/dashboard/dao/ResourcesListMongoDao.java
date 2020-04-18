package com.alibaba.csp.sentinel.dashboard.dao;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import com.alibaba.csp.sentinel.dashboard.domain.po.ResourceListMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ligen
 * @title: MertricEntityDao
 * @projectName sentinel-dashboard
 * @description:
 * @date 2020/3/319:19
 */
@Component
public class ResourcesListMongoDao {

//    @Autowired
//    private MongoTemplate mongoTemplate;

    public void save(ResourceListMongo resource) {
//        mongoTemplate.save(resource);
    }

    public List<ResourceListMongo> getByApp(String app) {
        Query query = new Query(Criteria.where("app").is(app));
//        return mongoTemplate.find(query, ResourceListMongo.class);
        return null;
    }

    public List<String> getResourceNameListByApp(String app) {
        Query query = new Query(Criteria.where("app").is(app));
//        List<ResourceListMongo> resourceListMongos = mongoTemplate.find(query, ResourceListMongo.class);
//        return resourceListMongos.stream().map(a -> a.getResources()).collect(Collectors.toList());
        return null;
    }

}
