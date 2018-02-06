package com.godfkc.center.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/2/5
 * @description
 */
@Entity
@Table(name = "cs_bank_dict")
public class BankDict {
    @Id
    @GeneratedValue
    @Column(name = "id",length = 10)
    private Long id;
    @Column(name = "bank_name")
    private String bankName; //银行名称
    @Column(name = "bank_code")
    private String bankCode; //银行编号

    //@NotFound(action= NotFoundAction.IGNORE)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bankDict")
    @JsonIgnore
    private Set<CompanyBankCard> companyBankCards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Set<CompanyBankCard> getCompanyBankCards() {
        return companyBankCards;
    }

    public void setCompanyBankCards(Set<CompanyBankCard> companyBankCards) {
        this.companyBankCards = companyBankCards;
    }
}
