package com.godfkc.center.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @version 1.0
 * @auther Administrator
 * @date 2018/1/29
 * @description
 */
@Entity
@Table(name = "cs_address_dict")
public class AddressDict implements Serializable {
    @Id
    @GeneratedValue
    @Column(length = 10)
    private Long id;
    @Column(length = 20)
    private String name;
    @Column(name = "is_city", length = 1)
    private int isCity;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "parent_id")
    private AddressDict parent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private Set<AddressDict> children;

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

    public int getIsCity() {
        return isCity;
    }

    public void setIsCity(int isCity) {
        this.isCity = isCity;
    }

    public AddressDict getParent() {
        return parent;
    }

    public void setParent(AddressDict parent) {
        this.parent = parent;
    }

    public Set<AddressDict> getChildren() {
        return children;
    }

    public void setChildren(Set<AddressDict> children) {
        this.children = children;
    }
}
