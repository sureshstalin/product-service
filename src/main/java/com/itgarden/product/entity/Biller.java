package com.itgarden.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
@Entity
@Table(name = "biller")
public class Biller extends BaseObject {

    @Column(name = "bill_no", nullable = false)
    private String billNo;

    @Column(name = "customer_id",nullable = false)
    private Long customerId;

    @Column(name = "grand_total", nullable = false)
    private double grandTotal;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_tax_amount", nullable = false)
    private double totalTaxAmount;

    @Column(name = "bill_status",nullable = false)
    private String billStatus;



}
