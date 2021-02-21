package com.fuguoliang.druiddemo.demo.aspects;import com.fuguoliang.druiddemo.demo.annotations.UseRedisCache;import com.fuguoliang.druiddemo.demo.utils.RedisUtil;import org.aspectj.lang.ProceedingJoinPoint;import org.aspectj.lang.annotation.Around;import org.aspectj.lang.annotation.Aspect;import org.aspectj.lang.annotation.Pointcut;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Component;import org.aspectj.lang.reflect.MethodSignature;import org.springframework.web.context.request.RequestContextHolder;import org.springframework.web.context.request.ServletRequestAttributes;import javax.servlet.http.HttpServletRequest;/** * @author FGL_S */@Component@Aspectpublic class RedisCacheAspect {    @Autowired    private RedisUtil redisUtil;    @Pointcut("@annotation(com.fuguoliang.druiddemo.demo.annotations.UseRedisCache)")    public void redisCache() {    }    @Around("redisCache()")    public Object useRedis(ProceedingJoinPoint point) throws Throwable {        UseRedisCache redisCache = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(UseRedisCache.class);        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder                .getRequestAttributes();        HttpServletRequest request = attributes.getRequest();        String parameter = request.getParameter(redisCache.paramName());        String key = redisCache.prefix() + "-" + parameter;        System.out.println("[aspect] start");        System.out.println("[aspect] point is :" + point.toString());        if (redisUtil.hasKey(key)) {            System.out.println("use key " + key);            return redisUtil.get(key);        }        Object res = point.proceed();        redisUtil.set(key, res.toString());        return  res;    }}