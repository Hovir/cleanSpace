package com.godfkc.center.entity;

import javax.persistence.*;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/2/5
 * @description
 */
@Entity
@Table(name = "cs_company_address")
public class CompanyAddress {
    @Id
    @GeneratedValue
    @Column(name = "id",length = 10)
    private Long id;
    @Column(name = "state", length = 30)
    private String state; //省
    @Column(name = "city", length = 30)
    private String city; //市
    @Column(name = "district", length = 30)
    private String district; //区
    @Column(name = "address", length = 200)
    private String address; //详细地址

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "company_id")
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
