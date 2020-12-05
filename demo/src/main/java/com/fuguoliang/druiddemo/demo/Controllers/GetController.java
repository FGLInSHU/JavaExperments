package com.fuguoliang.druiddemo.demo.Controllers;

import com.fuguoliang.druiddemo.demo.annotations.LogRequired;
import com.fuguoliang.druiddemo.demo.mapper.UserMapper;
import com.fuguoliang.druiddemo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author FGL_S
 */
@RestController
@RequestMapping("/hello")
public class GetController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataSource dataSource;

    @LogRequired
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String get(int id) {
        int res = userMapper.insert(new User(id, "张三", 10, 0));
        System.out.println("datasource class is :" + dataSource.getClass().toString());
        System.out.println("datasource is:" + dataSource);
        return  "res is:" + res;
    }

    @LogRequired
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public String findAll() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println("datasource class is :" + dataSource.getClass().toString());
        System.out.println("datasource is:" + dataSource);
        return "find res is:" + user;
    }

    @LogRequired
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    @Transactional(propagation= Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public String deleteUser(int id){
        System.out.println("delete user:" + id);
        int i = userMapper.deleteByPrimaryKey(id);
        throw  new NullPointerException();
       // return "delete res is:" + i;
    }
}
