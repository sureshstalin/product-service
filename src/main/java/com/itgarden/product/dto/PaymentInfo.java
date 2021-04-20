package com.itgarden.product.dto;

import com.itgarden.product.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfo extends BaseObject {

    private BillerInfo biller;

    private ProductItemInfo productItem;

    private TaxInfo tax;

    private double price;

    private double totalPrice;

    private double taxAmount;

    private long productId;

    private String paymentStatus;

}
