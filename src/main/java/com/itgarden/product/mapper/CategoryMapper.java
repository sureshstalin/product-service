package com.itgarden.product.mapper;

import com.itgarden.product.dto.CategoryInfo;
import com.itgarden.product.entity.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Mapper(implementationPackage = "mapper.impl")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryInfoToCategory(CategoryInfo categoryInfo);

    @InheritInverseConfiguration
    CategoryInfo categoryToCategoryInfo(Category category);

}
