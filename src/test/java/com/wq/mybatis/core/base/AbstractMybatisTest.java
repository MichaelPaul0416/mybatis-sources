package com.wq.mybatis.core.base;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @Author: wangqiang20995
 * @Date:2019/4/2
 * @Description:
 * @Resource:
 */
public abstract class AbstractMybatisTest {

    protected SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare(){
        Reader reader ;
        try {
            reader = new InputStreamReader(Resources.getResourceAsStream("com/wq/mybatis/core/MybatisConfig.xml"));
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
