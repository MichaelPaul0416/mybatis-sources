package org.apache.ibatis.reflection;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @Author: wangqiang20995
 * @Date:2019/4/3
 * @Description:
 * @Resource:
 */
public class ParamNameUtilTest {

    public void display(String name,Map<String,String> info){
        System.out.println("ok");
    }

    @Test
    public void paramName() throws NoSuchMethodException {
        Class<ParamNameUtilTest> test = ParamNameUtilTest.class;
        Method method = test.getDeclaredMethod("display", String.class, Map.class);
        System.out.println(ParamNameUtil.getParamNames(method));
    }

}