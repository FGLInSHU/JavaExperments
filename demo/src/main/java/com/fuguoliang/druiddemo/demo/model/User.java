package com.fuguoliang.druiddemo.demo.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别，男：0，女：1
     */
    private Integer gender;

    private static final long serialVersionUID = 1L;

    public User(int id, String name, int age, int gender) {
        this.age = age;
        this.gender = gender;
        this.id = id;
        this.name = name;
    }
}