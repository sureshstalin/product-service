package com.itgarden.product.repository;


import com.itgarden.product.entity.AppEntityCode;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Created by Suresh Stalin on 20 / Oct / 2020.
 */

public interface AppEntityCodeRepository extends JpaRepository<AppEntityCode,Long> {
    AppEntityCode findByCode(String code);
}
