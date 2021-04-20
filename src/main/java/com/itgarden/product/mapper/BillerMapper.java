package com.itgarden.product.mapper;

import com.itgarden.product.dto.BillerInfo;
import com.itgarden.product.entity.Biller;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(implementationPackage = "mapper.impl")
public interface BillerMapper {


    BillerMapper INSTANCE = Mappers.getMapper(BillerMapper.class);

    Biller billerInfoToBiller(BillerInfo billerInfo);

    @InheritInverseConfiguration
    BillerInfo billerToBillerInfo(Biller biller);
}
