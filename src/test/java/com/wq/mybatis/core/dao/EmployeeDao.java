package com.wq.mybatis.core.dao;

import com.wq.mybatis.core.model.Employee;

import java.util.List;

/**
 * @Author: wangqiang20995
 * @Date:2019/4/2
 * @Description:
 * @Resource:
 */
public interface EmployeeDao {

    List<Employee> queryAllEmployee();
}
