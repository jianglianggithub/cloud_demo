package com;



import com.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBoot {

    @Autowired
    TestMapper testMapper;

    /**
     *  当 where 后面条件跟的主键索引 或者 唯一索引的时候 有事务的情况下 是 行锁
     *  否则是表锁
     */
    @Test
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void test1 () throws InterruptedException {


                testMapper.update1("1","123");


        Thread.sleep(20000000L);
    }

    @Test
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void test2() {
       //List<Map<String,Object>> result= testMapper.select();
        testMapper.update("2","2");

    }
}
