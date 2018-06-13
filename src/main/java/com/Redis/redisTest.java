package com.Redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class redisTest {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:redis-conf.xml");

        RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);

        String name= (String) redisTemplate.opsForValue().get("key");
        System.out.println("name = " + name);
    }
}
