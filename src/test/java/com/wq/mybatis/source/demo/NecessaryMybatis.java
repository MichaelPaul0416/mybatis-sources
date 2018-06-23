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
import org.junit.Before;
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

    @Before
    public void init() throws IOException {
        Reader reader = new InputStreamReader(Resources.getResourceAsStream("com/wq/mybatis/source/demo/CommonConfig.xml"));
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }


    /**
     * mybatis二级缓存测试
     */
    @Test
    public void queryInDifferentSqlSession(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        OfficeMapper mapper1 = sqlSession1.getMapper(OfficeMapper.class);
        OfficeMapper mapper2 = sqlSession2.getMapper(OfficeMapper.class);

        System.out.println(mapper1.queryAllOffices());

        System.out.println(mapper2.queryAllOffices());

        System.out.println(mapper1.queryAllOffices());
    }


    @Test
    public void queryDemo() {
        SqlSession sqlSession = sqlSessionFactory.openSession();//与数据库的会话，由datasource打开,CachingExecutor
        OfficeMapper officeMapper = sqlSession.getMapper(OfficeMapper.class);//代理工厂产生的代理对象
        System.out.println(officeMapper.getClass().getName());
        List<Map<String,String>> result = officeMapper.queryAllOffices();
        System.out.println(result);
        List<Map<String,String>> again = officeMapper.queryAllOffices();
        System.out.println(again);
    }

    @Test
    public void queryAllOffices(){
        loadWithConfigBean();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        QueryAPI officeMapper = sqlSession.getMapper(QueryAPI.class);
        List<Map<String,String>> result = officeMapper.queryAllList();
        System.out.println(result);

    }

    @Test
    public void queryByChoose(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OfficeMapper officeMapper = sqlSession.getMapper(OfficeMapper.class);
        System.out.println(officeMapper.queryById("4"));
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

    interface QueryAPI{
        @Select("select * from offices")
        List<Map<String,String>> queryAllList();
    }

}
