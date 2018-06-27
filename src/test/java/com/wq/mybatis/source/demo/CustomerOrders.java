package com.wq.mybatis.source.demo;


import java.util.List;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/26
 * @Description:
 * @Resource:
 */
public class CustomerOrders {
    private Customer customer;
    private List<Orders> orders;

    @Override
    public String toString() {
        return "CustomerOrders{" +
                "customer=" + customer +
                ", orders=" + orders +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
