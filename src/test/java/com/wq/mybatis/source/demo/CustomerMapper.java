package com.wq.mybatis.source.demo;

import com.wq.mybatis.source.demo.plugins.DefaultPager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/26
 * @Description:
 * @Resource:
 */
public interface CustomerMapper {

    List<Customer> queryTotalCustomer();

    Customer queryByCustomerId(int customerId);

    List<CustomerOrders> queryOrdersByCustomerId(int customerId);

    List<Customer> queryCustomersByPager(@Param("creditLimit") int creditLimit, @Param("pager") DefaultPager pager);
}
