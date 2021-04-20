package com.itgarden.product.mapper;

import com.itgarden.product.dto.ProductItemInfo;
import com.itgarden.product.entity.ProductItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

@Mapper(implementationPackage = "mapper.impl")
public interface ProductItemMapper {

    ProductItemMapper INSTANCE =  Mappers.getMapper(ProductItemMapper.class);
    ProductItem productItemInfoToProductItem(ProductItemInfo productItemInfo);
    @InheritInverseConfiguration
    ProductItemInfo productItemToProductItemInfo(ProductItem productItem);

}
