package com.wq.mybatis.source.demo.plugins;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/26
 * @Description:
 * @Resource:
 */
@Intercepts(@Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class}))
public class QueryPagePlugin implements Interceptor {

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler;
        Object target = invocation.getTarget();
        String queryPageSize = properties.getProperty("queryPageSize");
//        String database = properties.getProperty("dialect");

        if(target instanceof StatementHandler){
            statementHandler = (StatementHandler) target;
            MetaObject metaObject = SystemMetaObject.forObject(statementHandler);//获取statementHandler绑定的参数原对象

            BoundSql boundSql = (BoundSql) metaObject.getValue("delegate.boundSql");//反射获取对象
            Map<String,Object> paramMap = (Map<String, Object>) boundSql.getParameterObject();
            /**
             *  如果dao接口中的入参之前没有加@Param(value="xxx")注解
             *  那么在这里获取的时候key=argI/paramI,I的取值为该参数在dao接口入参顺序中的位数（跳过@RowBounds,@ResultHandler）
             *  具体见ParamNameResolver#getNamedParams
             *
             *  DefaultPager pager = (DefaultPager) paramMap.get("param2");---dao接口入参中该参数之前没有加@Param注解
             *  DefaultPager pager = (DefaultPager) paramMap.get("pager");---dao接口入参中该参数加了@Param注解
             */

            DefaultPager pager = (DefaultPager) paramMap.get("pager");

            pager.setPageSize(Integer.parseInt(queryPageSize));

            String pagerSql = buildPagerSql(boundSql,pager);

            metaObject.setValue("delegate.boundSql.sql",pagerSql);

        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private String buildPagerSql(BoundSql boundSql,DefaultPager defaultPager){
        String oriSql = boundSql.getSql();
        int start = (defaultPager.getCurrentPage() - 1) * defaultPager.getPageSize();
        oriSql += " limit " + start + " , " + defaultPager.getPageSize();

        return oriSql;
    }
}
