package com.fuguoliang.druiddemo.demo.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class OperationLogs implements Serializable {
    private Integer id;

    /**
     * user表主键
     */
    private Integer userid;

    /**
     * 操作内容
     */
    private String msg;

    /**
     * 创建时间
     */
    private Date createTime;

    public OperationLogs(Integer id, Integer userid, String msg, Date createTime) {
        this.id = id;
        this.userid = userid;
        this.msg = msg;
        this.createTime = createTime;
    }

    private static final long serialVersionUID = 1L;
}