package com.Service.implement;

import com.EntiryPackage.userEntiry;
import com.Mapper.LoginInterface;
import com.Service.LoginUser;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class loginGetnameByName implements LoginUser {

    private final SqlSessionFactory sqlSessionFactory;
    private final RedisTemplate redisTemplate;
    @Autowired
    public loginGetnameByName(SqlSessionFactory sqlSessionFactory, @Qualifier("RedisTemplateLocal") RedisTemplate redisTemplate) {
        this.sqlSessionFactory = sqlSessionFactory;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public userEntiry getname(String name) {
        SqlSession session = sqlSessionFactory.openSession();
        LoginInterface loginUser = session.getMapper(LoginInterface.class);
        System.out.println("loginUser = " + loginUser.getName(name));
        return loginUser.getName(name);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean getInsert(userEntiry userEntiry) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        LoginInterface loginInterface = sqlSession.getMapper(LoginInterface.class);
        return loginInterface.insert(userEntiry);
    }

    @Override
    public boolean inuset(userEntiry entiry) {

        Long aLong = redisTemplate.boundSetOps("user123").add(entiry.toString());
         if (0 != aLong){
              return true;
         }
        return false;
    }
}
