package com.itgarden.product.entity;

import com.itgarden.product.common.staticdata.PurchaseOrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/*
 * Created by Suresh Stalin on 13 / Oct / 2020.
 */

@Getter
@Setter
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder extends BaseObject{

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_description", nullable = false)
    private String productDescription;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private double unitPrice;

    @Column(name = "purchase_order_code",nullable = false)
    private String purchaseOrderCode;

    @Column(name = "vendor_id",nullable = false)
    private Long vendorId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tax_id")
    private Tax tax;

    @Column(name = "tax_amount", nullable = false)
    private double taxAmount;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "grand_total",nullable = false)
    private double grandTotal;

    @Column(name = "status",nullable = false)
    private PurchaseOrderStatus purchaseOrderStatus;
}
