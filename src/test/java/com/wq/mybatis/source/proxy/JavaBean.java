package com.wq.mybatis.source.proxy;

/**
 * @Author: wangqiang20995
 * @Date:2018/6/15
 * @Description:
 * @Resource:
 */
public class JavaBean {
    private String jdk;
    private String language;
    private String country;

    public JavaBean(){}
    @Override
    public String toString() {
        return "JavaBean{" +
                "jdk='" + jdk + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getJdk() {
        return jdk;
    }

    public void setJdk(String jdk) {
        this.jdk = jdk;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
