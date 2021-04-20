package com.itgarden.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponse {

    private List<ProductItemInfo> productItemList;
    private ProductInfo productInfo;

}
