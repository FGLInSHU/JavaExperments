package com.fuguoliang.druiddemo.demo.Controllers;

import com.fuguoliang.druiddemo.demo.utils.RedisUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FGL_S
 */
@RestController
@RequestMapping("/user")
public class VoteController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public void vote(int voter, int candidate) {
        String lockKey = "candidate:" + candidate;
        String lockVal = "voter:" + voter;
        String countKey = "count:" + candidate;
        System.out.println("start to vote");
        try {
            while (true) {
                if (redisUtil.tryLock(lockKey, lockVal)) {
                    System.out.println("get lock");
                    System.out.println("vote result:" + redisUtil.incr(countKey));
                    break;
                }
            }
        } finally {
            redisUtil.releaseLock(lockKey, lockVal);
        }
        System.out.println("finish vote");
    }
}
