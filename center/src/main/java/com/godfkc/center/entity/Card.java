package com.godfkc.center.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/30
 * @description
 */
@Entity
@Table(name = "cs_card")
public class Card implements Serializable {
    @Id
    @GeneratedValue
    @Column(length = 10)
    private Long id;
    @Column(name = "num", length = 32)
    private String num; //卡编码
    @Column(name = "password", length = 32)
    private String password; //卡密码
    @Column(name = "times", length = 1)
    private int times = 3; //卡可以预约的次数  默认3次
    @Column(name = "status", length = 1)
    private int status; //状态 0停用 1未激活 2已激活
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    //多对一 公司id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    //一对一 用户
    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
