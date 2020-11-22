package com.fuguoliang.druiddemo.demo.Controllers;

import com.fuguoliang.druiddemo.demo.mapper.UserMapper;
import com.fuguoliang.druiddemo.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author FGL_S
 */
@RestController
public class GetController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DataSource dataSource;

    @RequestMapping("/put")
    public String get() {
        int res = userMapper.insert(new User(1, "张三", 10, 0));
        System.out.println("datasource is:" + dataSource);
        return  "res is:" + res;
    }

    @RequestMapping("/find")
    public String findAll() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println("datasource is:" + dataSource);
        return "find res is:" + user;
    }
}