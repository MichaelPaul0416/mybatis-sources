package com.wq.mybatis.source.demo.plugins;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/26
 * @Description:
 * @Resource:
 */
public enum SqlDialect {

    DEFAULT("mysql"),
    MYSQL("mysql"),
    SQLSERVER("sqlserver");

    private String database;

    SqlDialect(String database){
        this.database = database;
    }

    public static SqlDialect instance(String database){
        SqlDialect[] directs = SqlDialect.values();
        SqlDialect correct = DEFAULT;
        for(SqlDialect sqlDialect : directs){
            if(sqlDialect.database.equals(database) && !sqlDialect.equals(DEFAULT)){
                correct = sqlDialect;
                break;
            }
        }
        return correct;
    }
}
