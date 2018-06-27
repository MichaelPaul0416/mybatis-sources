package com.wq.mybatis.source.demo;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/26
 * @Description:
 * @Resource:
 */
@MappedTypes(String.class)
@MappedJdbcTypes(value = {JdbcType.VARCHAR,JdbcType.CHAR},includeNullJdbcType = true)
public class DefaultNullValueHandler extends BaseTypeHandler<String> {

    private String defaultNull;

    public DefaultNullValueHandler(){
        this.defaultNull = "";
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String info = rs.getString(columnName);
        return trimNullValue(info);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return trimNullValue(rs.getString(columnIndex));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return trimNullValue(cs.getString(columnIndex));
    }

    private String trimNullValue(String nullValue){
        if(nullValue == null){
            return defaultNull;
        }else {
            return  nullValue.trim();
        }
    }
}
