package com.alibaba.csp.sentinel.dashboard.repository.metric;

import com.alibaba.csp.sentinel.dashboard.dao.MetricEntityDao;
import com.alibaba.csp.sentinel.dashboard.dao.ResourcesListMongoDao;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import com.alibaba.csp.sentinel.dashboard.domain.po.ResourceListMongo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * @author ligen
 * @title: MongoMetricsRepository
 * @projectName sentinel-dashboard
 * @description:
 * @date 2020/3/3016:51
 */
@Component("mongoMetricsRepository")
public class MongoMetricsRepository implements MetricsRepository<MetricEntity> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Autowired
    private MetricEntityDao metricEntityDao;
    @Autowired
    private ResourcesListMongoDao resourcesListMongoDao;


    @Override
    public void save(MetricEntity metric) {
        if (metric == null) {
            return;
        }
        try {
            readWriteLock.writeLock().lock();
            List<String> resourceNames = resourcesListMongoDao.getResourceNameListByApp(metric.getApp());
            if (!resourceNames.contains(metric.getResource())) {
                resourcesListMongoDao.save(new ResourceListMongo().setApp(metric.getApp()).setResources(metric.getResource()));
            }
            metricEntityDao.save(metric);
            logger.info("保存监控数据成功==>{}", metric);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    @Override
    public void saveAll(Iterable<MetricEntity> metrics) {
        try {
            readWriteLock.writeLock().lock();
            metrics.forEach(this::save);
            logger.info("--------saveAll------{}", metrics);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public List<MetricEntity> queryByAppAndResourceBetween(String app, String resource, long startTime, long endTime) {
        try {
            readWriteLock.readLock().lock();
            logger.info("获取监控数据成功");
            return metricEntityDao.queryByAppAndResourceBetween(app, resource, startTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<String> listResourcesOfApp(String app) {
        List<ResourceListMongo> data;
        try {
            readWriteLock.readLock().lock();
            data = resourcesListMongoDao.getByApp(app);
            if (data == null) {
                return null;
            }
            logger.info("获取监控资源列表成功");
            return data.parallelStream().map(da -> da.getResources())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
