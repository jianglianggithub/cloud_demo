package com;


import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class T {

    @Autowired
    StringEncryptor stringEncryptor;


    @Test
    public void ts() {
        System.out.println(stringEncryptor.encrypt("123456"));
        System.out.println(stringEncryptor.decrypt("VWLC6beCTWhD1+xl+FxIvVujm/KfIZ3Y"));
    }

}
