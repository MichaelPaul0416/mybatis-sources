package com.wq.mybatis.source.reflect;

import com.wq.mybatis.source.proxy.ProxyFactory;
import org.apache.ibatis.reflection.ParamNameUtil;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/20
 * @Description:
 * @Resource:
 */
public class MethodParamTest {

    @Test
    public void methodActuallyParamName() throws NoSuchMethodException {
        Class<ProxyFactory> proxyFactoryClass = ProxyFactory.class;
        Method method = proxyFactoryClass.getDeclaredMethod("instanceProxy",new Class[]{String.class});
        System.out.println(ParamNameUtil.getParamNames(method));
    }
}
