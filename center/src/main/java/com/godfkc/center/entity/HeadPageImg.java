package com.godfkc.center.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/2/6
 * @description
 */
@Entity
@Table(name = "cs_head_page_img")
public class HeadPageImg {
    @Id
    @GeneratedValue
    @Column(name = "id",length = 10)
    private Long id;
    @Column(name = "name",length = 20)
    private String name;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "status",length = 1)
    private int status;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
