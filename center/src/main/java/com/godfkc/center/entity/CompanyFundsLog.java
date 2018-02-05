package com.godfkc.center.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "cs_company_funds_log")
public class CompanyFundsLog implements Serializable {
    @Id
    @GeneratedValue
    @Column(length = 10)
    private Long id;
    @Column(name = "money", length = 10)
    private Long money; //操作的金额 增减值
    @Column(name = "descreption", length = 200)
    private String descreption; //描述
    @Column(name = "type", length = 1)
    private int type; //类型
    @Column(name = "current_money", length = 10)
    private Long currentMoney; //剩余金额
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    //多对一 公司id
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(Long currentMoney) {
        this.currentMoney = currentMoney;
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
}
