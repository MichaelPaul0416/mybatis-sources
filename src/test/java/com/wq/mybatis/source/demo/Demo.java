package com.wq.mybatis.source.demo;

/**
 * @Author: wangqiang20995
 * @Date:2018/11/8
 * @Description:
 * @Resource:
 */
public class Demo {
    private int id;
    private String name;
    private String price;

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
