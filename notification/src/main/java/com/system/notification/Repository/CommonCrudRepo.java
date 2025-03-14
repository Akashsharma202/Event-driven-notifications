package com.system.notification.Repository;

import com.mongodb.client.result.DeleteResult;
import com.system.notification.Constants.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommonCrudRepo<T> {
    private final Class<T> model;

    public CommonCrudRepo(Class<T> model) {
        this.model = model;
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public T save(T data) {
        mongoTemplate.save(data);
        return data;
    }

    public List<T> findAll() {
        return mongoTemplate.findAll(model);
    }

    public Optional<T> findById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where(CommonConstants.ID).is(id));
        return mongoTemplate.find(query, model).stream().findFirst();
    }

    public List<T> findByProperties(Map<String, Object> queryMap) {
        Query query = new Query();
        for (String key : queryMap.keySet()) {
            query.addCriteria(Criteria.where(key).is(queryMap.get(key)));
        }
        return new ArrayList<>(mongoTemplate.find(query, model));
    }

    public boolean deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where(CommonConstants.ID).is(id));
        DeleteResult result = mongoTemplate.remove(query, model);
        return result.wasAcknowledged();
    }

    public void insert(List<T> items) {
        mongoTemplate.insert(items);
    }
}
