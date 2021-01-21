package com.little.anotherwebuploadtest.bean;

import javax.persistence.*;

/**
 * @author luo x
 * @date 2021-01-04 10:12
 */

@Entity //告诉JPA这是一个实体类（和数据表映射的类）
@Table(name="videoimfo") //Table来指定和那个数据表对应；如果省略，默认表名就是user
public class movieImfo {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="videoId")
    private Integer videoId;
    @Column(name="videoname")
    private String videoname;
    @Column(name="pic")
    private String pic;
    @Column(name="type")
    private String type;
    @Column(name="playUrl")
    private String playUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    @Override
    public String toString() {
        return "movieImfo{" +
                "id=" + id +
                ", videoId=" + videoId +
                ", videoname='" + videoname + '\'' +
                ", pic='" + pic + '\'' +
                ", type='" + type + '\'' +
                ", playUrl='" + playUrl + '\'' +
                '}';
    }
}
