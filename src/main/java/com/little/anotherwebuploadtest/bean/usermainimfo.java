package com.little.anotherwebuploadtest.bean;

import javax.persistence.*;

/**
 * @author luo x
 * @date 2020-12-13 17:15
 * 用户信息绑定表
 */

@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name="usermainimfo") //Table来指定和那个数据表对应；如果省略，默认表名就是user
public class usermainimfo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user")
    private String user;
    @Column(name="pwd")
    private String pwd;
    @Column(name="mail")
    private String mail;
    @Column(name = "githubBangId")
    private Integer githubBangId;
    @Column(name = "QQBang")
    private Integer QQBang;
    @Column(name = "wechatBang")
    private Integer wechatBang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getGithubBangId() {
        return githubBangId;
    }

    public void setGithubBangId(Integer githubBangId) {
        this.githubBangId = githubBangId;
    }

    public Integer getQQBang() {
        return QQBang;
    }

    public void setQQBang(Integer QQBang) {
        this.QQBang = QQBang;
    }

    public Integer getWechatBang() {
        return wechatBang;
    }

    public void setWechatBang(Integer wechatBang) {
        this.wechatBang = wechatBang;
    }

    @Override
    public String toString() {
        return "usermainimfo{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                ", mail='" + mail + '\'' +
                ", githubBangId=" + githubBangId +
                ", QQBang=" + QQBang +
                ", wechatBang=" + wechatBang +
                '}';
    }
}
