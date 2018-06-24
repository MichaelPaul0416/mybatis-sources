package com.wq.mybatis.source.demo;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/24
 * @Description:
 * @Resource:
 */
public class DefaultDateHandler extends BaseTypeHandler<Date> {

    private DateFormat dateFormat;

    private String regex;

    public DefaultDateHandler(){
        Properties properties = new Properties();
        try {
            properties.load(Resources.getResourceAsReader("com/wq/mybatis/source/demo/mybatis.properties"));
            init(properties.getProperty("handler.date.format"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DefaultDateHandler(String regex){
        init(regex);
    }

    private void init(String regex) {
        if(StringUtils.isNotEmpty(regex)){
            this.regex = regex;
            this.dateFormat = new SimpleDateFormat(this.regex);
        }else {
            throw new NullPointerException("DateFormat can not be null");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        if(!JdbcType.VARCHAR.equals(jdbcType)){
            throw new IllegalArgumentException(String.format("this typeHandler [%s] only handle jdbcType [%s],please check you config again",
                    this.getClass().getName(),JdbcType.VARCHAR.name()));
        }
        String dateTime = dateFormat.format(parameter);
        ps.setString(i,dateTime);
    }

    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String dateStamp = rs.getString(columnName);
        return dealWithProbablyException(dateStamp);
    }

    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return dealWithProbablyException(rs.getString(columnIndex));
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return dealWithProbablyException(cs.getString(columnIndex));
    }

    private Date dealWithProbablyException(String dateTime){
        if(StringUtils.isNotEmpty(dateTime)) {
            try {
                return this.dateFormat.parse(dateTime);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return null;
    }
}
