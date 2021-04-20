package com.itgarden.product.repository;

import com.itgarden.product.entity.Biller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillerRepository extends JpaRepository<Biller,Long> {

    Biller findBillerByBillNo(String billNo);
}
