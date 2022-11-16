package com.lschaan.dog;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@MongoEntity(collection = "dog")
public class Dog extends PanacheMongoEntity {

    private String name;
    private Integer age;

    public static List<Dog> list (Map<String, Object> params) {
        String query = params.keySet().stream().map(key -> key + "=:" + key)
            .collect(Collectors.joining(" and "));
        return Dog.list(query, params);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
