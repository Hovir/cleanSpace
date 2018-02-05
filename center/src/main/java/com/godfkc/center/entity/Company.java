package com.godfkc.center.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
@Entity
@Table(name = "cs_company")
public class Company implements Serializable {
    @Id
    @GeneratedValue
    @Column(length = 10)
    private Long id;
    @Column(name = "name", length = 30)
    private String name;//公司名称
    @Column(name = "password", length = 20)
    private String password; //登录密码
    @Column(name = "img_url")
    private String imgUrl; //公司图片
    @Column(name = "profile")
    private String profile; //公司简介
    @Column(name = "bn", length = 5)
    private String bn;
    @Column(name = "status", length = 1)
    private int status; //状态 1启用 0停用
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    //自关联
    @JsonIgnore
    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "parent_id")
    private Company parent;


    @NotFound(action= NotFoundAction.IGNORE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    @JsonIgnore
    private Set<Company> Children;

    //一对多 激活卡
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    @JsonIgnore
    private Set<Card> cards;

    //一对多 预约订单
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    @JsonIgnore
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    @JsonIgnore
    private Set<CompanyFunds> companyFundsSet;

    //一对多 账户日志
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    @JsonIgnore
    private Set<CompanyFundsLog> companyFundsLogs;

    //一对多 提现
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    @JsonIgnore
    private Set<CompanyFundsWithdraw> companyFundsWithdraws;

    //多对一 等级
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "level_id")
    private Level level;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn;
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

    public Company getParent() {
        return parent;
    }

    public void setParent(Company parent) {
        this.parent = parent;
    }

    public Set<Company> getChildren() {
        return Children;
    }

    public void setChildren(Set<Company> children) {
        Children = children;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<CompanyFunds> getCompanyFundsSet() {
        return companyFundsSet;
    }

    public void setCompanyFundsSet(Set<CompanyFunds> companyFundsSet) {
        this.companyFundsSet = companyFundsSet;
    }

    public Set<CompanyFundsLog> getCompanyFundsLogs() {
        return companyFundsLogs;
    }

    public void setCompanyFundsLogs(Set<CompanyFundsLog> companyFundsLogs) {
        this.companyFundsLogs = companyFundsLogs;
    }

    public Set<CompanyFundsWithdraw> getCompanyFundsWithdraws() {
        return companyFundsWithdraws;
    }

    public void setCompanyFundsWithdraws(Set<CompanyFundsWithdraw> companyFundsWithdraws) {
        this.companyFundsWithdraws = companyFundsWithdraws;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
