package com.godfkc.center.entity;

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
    @Column(length = 30)
    private String name;//公司名称
    @Column(name = "img_url")
    private String imgUrl; //公司图片
    @Column(length = 255)
    private String profile; //公司简介
    @Column(length = 1)
    private int level; //公司等级 1体验店 2联盟 3蚂蚁
    @Column(length = 1)
    private int status; //状态 1启用 0停用
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    //自关联
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "parent_id")
    private Company parent;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<Company> Children;

    //一对多 激活卡
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private Set<Card> cards;

    //一对多 预约订单
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private Set<Order> orders;

    @OneToOne(mappedBy = "company")
    private CompanyFunds companyFunds;

    //一对多 账户日志
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private Set<CompanyFundsLog> companyFundsLogs;

    //一对多 提现
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private Set<CompanyFundsWithdraw> companyFundsWithdraws;

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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public CompanyFunds getCompanyFunds() {
        return companyFunds;
    }

    public void setCompanyFunds(CompanyFunds companyFunds) {
        this.companyFunds = companyFunds;
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
}
