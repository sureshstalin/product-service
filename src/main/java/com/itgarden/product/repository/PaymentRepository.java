package com.itgarden.product.repository;

import com.itgarden.product.entity.Biller;
import com.itgarden.product.entity.Payment;
import com.itgarden.product.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

        Payment findPaymentByProductItem(ProductItem productItem);
        List<Payment> findPaymentByBiller(Biller biller);

}
