package com.little.anotherwebuploadtest.bean;

/**
 * @author luo x
 * @date 2020-12-13 12:53
 */


public class githubAccses {
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String code;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "githubAccses{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", redirect_uri='" + redirect_uri + '\'' +
                ", code='" + code + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
