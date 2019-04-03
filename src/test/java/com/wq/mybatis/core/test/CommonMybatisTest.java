package com.wq.mybatis.core.test;

import com.wq.mybatis.core.base.AbstractMybatisTest;
import com.wq.mybatis.core.dao.EmployeeDao;
import com.wq.mybatis.core.model.Employee;
import org.apache.ibatis.session.RowBounds;
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

    @Test
    public void queryByCondition(){
        Employee employee = new Employee();
        employee.setOfficeCode("5");
        //逻辑分页，数据库所有记录查出来然后一次次获取
        RowBounds page = new RowBounds(0,4);
        List<Employee> employees = employeeDao.conditionQuery(employee, page);
        logger.info("condition employee page[{}] -->{}",page,employees);
        System.out.println("-----------------------------------------");
        //如果RowBound的条件是一样的话，那么是会去查缓存的，如果条件不一样，那么即便开了缓存，还是会去查询sql
        //见CachingExecutor#query方法，会先构建CacheKey，构建的时候考虑了RowBound，所以rowBound的toString不一样的话，那么key就会变化，最终还是去查询数据库
//        page = new RowBounds(0,4);
        page = new RowBounds(4,4);
        logger.info("condition employee page[{}] -->{}",page,employeeDao.conditionQuery(employee,page));
    }
}
