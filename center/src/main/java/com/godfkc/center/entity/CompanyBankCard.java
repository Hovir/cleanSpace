package com.godfkc.center.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/2/5
 * @description
 */
@Entity
@Table(name = "cs_company_bankCard")
public class CompanyBankCard {
    @Id
    @GeneratedValue
    @Column(name = "id",length = 10)
    private Long id;
    @Column(name = "card_no",length = 30)
    private String cardNo;
    @Column(name = "phone",length = 15)
    private String phone;
    @Column(name = "status",length = 2)
    private String status;

    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private Company company;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "bankDict_id")
    private BankDict bankDict;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public BankDict getBankDict() {
        return bankDict;
    }

    public void setBankDict(BankDict bankDict) {
        this.bankDict = bankDict;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
