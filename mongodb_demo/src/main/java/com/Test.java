package com;



import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class Test {
//    private static final Log log = LogFactory.getLog(MongoApp.class);

    public static void main(String[] args) {
//
//        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoClientDbFactory(MongoClients.create(), "database"));
//
//        Person p = new Person("Joe", 34);
//
//        // Insert is used to initially store the object into the database.
//        mongoOps.insert(p);
//        log.info("Insert: " + p);
//
//        // Find
//        p = mongoOps.findById(p.getId(), Person.class);
//        log.info("Found: " + p);
//
//        // Update
//        mongoOps.updateFirst(Query.query(Criteria.where("name").is("Joe")), Update.update("age", 35), Person.class);
//        p = mongoOps.findOne(Query.query(Criteria.where("name").is("Joe")), Person.class);
//        log.info("Updated: " + p);
//
//        // Delete
//        mongoOps.remove(p);
//
//        // Check that deletion worked
//        List<Person> people =  mongoOps.findAll(Person.class);
//        log.info("Number of people = : " + people.size());
//
//
//        mongoOps.dropCollection(Person.class);
    }
}
