package com.wq.mybatis.core.test;

import com.wq.mybatis.core.base.AbstractMybatisTest;
import com.wq.mybatis.core.dao.EmployeeDao;
import com.wq.mybatis.core.model.Employee;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Author: wangqiang20995
 * @Date:2019/4/2
 * @Description:
 * @Resource:
 */
public class CommonMybatisTest extends AbstractMybatisTest {

    private EmployeeDao employeeDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void employeeDao(){
        /**
         * 第一步获取的一般是DefaultSqlSession，可能已经打开了Connection to database
         */
        employeeDao = sqlSessionFactory.openSession().getMapper(EmployeeDao.class);
    }

    @Test
    public void queryEmployee(){
        List<Employee> employees = employeeDao.queryAllEmployee();
        logger.info("employees->{}",employees);
    }
}
