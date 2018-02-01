package com.godfkc.center.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/2/1
 * @description
 */
@Entity
@Table(name = "cs_level")
public class Level implements Serializable {
    @Id
    @GeneratedValue
    @Column(length = 10)
    private Long id;
    @Column(name = "name", length = 30)
    private String name; //名称
    @Column(name = "commision", length = 10)
    private Long commision; //等级对应佣金
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    //一对多 公司
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "level")
    private Set<Company> companies;

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

    public Long getCommision() {
        return commision;
    }

    public void setCommision(Long commision) {
        this.commision = commision;
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

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }
}
