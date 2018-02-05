package com.godfkc.center.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
@Entity
@Table(name = "cs_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue
    @Column(length = 10)
    private Long id;
    @Column(name = "name", length = 10)
    private String name; //预约人姓名
    @Column(name = "phone", length = 20)
    private String phone; //预约人电话
    @Column(name = "state", length = 30)
    private String state; //省
    @Column(name = "city", length = 30)
    private String city; //市
    @Column(name = "district", length = 30)
    private String district; //区
    @Column(name = "address", length = 200)
    private String address; //详细地址
    @Column(name = "appointment_time")
    private Date appointmentTime; //预约时间
    @Column(name = "remark", length = 200)
    private String remark; //备注
    @Column(name = "report", length = 200)
    private String report; //报告
    @Column(name = "status", length = 1)
    private int status; //状态 1预约中 2出报告 0取消
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;

    //多对一 公司id
    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    //多对一 用户id
    @NotFound(action= NotFoundAction.IGNORE)
    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
