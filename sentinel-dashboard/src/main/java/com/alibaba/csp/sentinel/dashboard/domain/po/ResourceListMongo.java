package com.alibaba.csp.sentinel.dashboard.domain.po;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ResourceListMongo")
public class ResourceListMongo {

    private String resources;
    private String app;

    public String getResources() {
        return resources;
    }

    public ResourceListMongo setResources(String resources) {
        this.resources = resources;
        return this;
    }

    public String getApp() {
        return app;
    }

    public ResourceListMongo setApp(String app) {
        this.app = app;
        return this;
    }
}