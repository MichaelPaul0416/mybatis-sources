package com.wq.mybatis.source.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/15
 * @Description:
 * @Resource:
 */
public class ProxyFactory {

    private ProxyProcessor proxyProcessor;

    public ProxyFactory(){}

    public void setProxyProcessor(ProxyProcessor proxyProcessor) {
        this.proxyProcessor = proxyProcessor;
    }

    public <T> T instanceProxy(String clazz){
        try {
            Class<T> targetClass = (Class<T>) Class.forName(clazz);
            return (T) Proxy.newProxyInstance(targetClass.getClassLoader(),new Class[]{targetClass},proxyProcessor);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    class ProxyProcessor<T> implements InvocationHandler{

        private T proxy;

        public ProxyProcessor(T proxy){
            this.proxy = proxy;
        }

        public ProxyProcessor(){}

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("do something before proxy object does target method");
            Object result = method.invoke(this.proxy ,args);
            System.out.println("deal with something after target object does it's method");
            return result;
        }

    }



    @Test
    public void proxy(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setProxyProcessor(new ProxyProcessor(new DefaultSqlSession()));
        SqlSession sqlSession = proxyFactory.instanceProxy("com.wq.mybatis.source.proxy.SqlSession");
        System.out.println(sqlSession.getClass());
        System.out.println(sqlSession.obtainPojo());

    }
}
