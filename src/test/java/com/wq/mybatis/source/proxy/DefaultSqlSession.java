package com.wq.mybatis.source.proxy;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/15
 * @Description:
 * @Resource:
 */
public class DefaultSqlSession implements SqlSession {
    @Override
    public JavaBean obtainPojo() {
        JavaBean javaBean = new JavaBean();
        javaBean.setLanguage("Chinese");
        javaBean.setCountry("CN");
        javaBean.setJdk("JDK8");
        return javaBean;
    }
}
