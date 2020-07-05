package com.jpa;

import com.entity.TestCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TestCollectionRepository extends MongoRepository<TestCollection,String> {
    List<TestCollection> findByIdIs(String id);
}
