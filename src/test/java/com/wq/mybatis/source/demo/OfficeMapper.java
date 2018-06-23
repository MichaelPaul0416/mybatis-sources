package com.wq.mybatis.source.demo;

import java.util.List;
import java.util.Map;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/12
 * @Description:
 * @Resource:
 */
public interface OfficeMapper {

    List<Map<String,String>> queryAllOffices();

    Offices queryById(String id);
}
