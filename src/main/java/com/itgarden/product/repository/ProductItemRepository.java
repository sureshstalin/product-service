package com.itgarden.product.repository;

import com.itgarden.product.common.staticdata.StockStatus;
import com.itgarden.product.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {

    List<ProductItem> findProductItemByProductItemCodeIn(List<String> productItemCodes);
    List<ProductItem> findProductItemByStockStatusAndProductItemCodeIn(StockStatus stockStatus, List<String> productItemCodes);
}
