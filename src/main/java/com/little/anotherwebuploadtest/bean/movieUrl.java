package com.little.anotherwebuploadtest.bean;

/**
 * @author luo x
 * @date 2021-01-05 08:40
 */


public class movieUrl {
    private String name;
    private String url;

    public movieUrl(String name, String url) {
        this.name=name;
        this.url=url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "movieUrl{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
