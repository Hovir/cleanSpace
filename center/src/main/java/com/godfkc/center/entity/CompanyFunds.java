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
@Table(name = "cs_company_funds")
public class CompanyFunds implements Serializable {
    @Id
    @GeneratedValue
    @Column(length = 10)
    private Long id;
    @Column(name = "money", length = 10)
    private Long money; //账户余额
    @Column(name = "pay_password", length = 20)
    private String payPassword; //提现密码
    @Column(name = "status", length = 1)
    private int status; //状态 1启用 0停用
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    //一对一 公司
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "company_id")
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

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
