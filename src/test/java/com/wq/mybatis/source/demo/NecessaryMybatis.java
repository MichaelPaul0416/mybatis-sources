package com.wq.mybatis.source.demo;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/12
 * @Description:
 * @Resource:
 */
public class NecessaryMybatis {

    private SqlSessionFactory sqlSessionFactory;//DefaultSqlSessionFactory

    @Test
    public void queryDemo() throws IOException{
        loadFromConfigFile();
    }

    @Test
    public void queryAllOffices(){
        loadWithConfigBean();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QueryAPI officeMapper = sqlSession.getMapper(QueryAPI.class);
        List<Map<String,String>> result = officeMapper.queryAllList();
        System.out.println(result);

    }

    private void loadWithConfigBean(){
        Properties properties = loadProperties();
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(properties.getProperty("driver"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("mybatis-demo",transactionFactory,dataSource);
        Configuration configuration = new Configuration(environment);//作为一个成员变量
        configuration.addMapper(QueryAPI.class);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
    }

    private Properties loadProperties(){
        Properties properties = new Properties();
        try {
            properties.load(Resources.getResourceAsReader("com/wq/mybatis/source/demo/driver.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private void loadFromConfigFile() throws IOException {
        Reader reader = new InputStreamReader(Resources.getResourceAsStream("com/wq/mybatis/source/demo/CommonConfig.xml"));
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        OfficeMapper officeMapper = sqlSession.getMapper(OfficeMapper.class);
        List<Map<String,String>> result = officeMapper.queryAllOffices();
        System.out.println(result);

    }

    interface QueryAPI{
        @Select("select * from offices")
        List<Map<String,String>> queryAllList();
    }

}