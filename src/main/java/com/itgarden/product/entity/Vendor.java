package com.itgarden.product.entity;

import com.itgarden.product.dto.BaseInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// Dummy class
@Getter
@Setter
@Entity
@Table(name = "vendor")
public class Vendor extends BaseObject {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @Column(name = "user_id")
    private long userId;
}
