package com.itgarden.product.repository;

import com.itgarden.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Created by Suresh Stalin on 22 / Nov / 2020.
 */

public interface ProductRepository  extends JpaRepository<Product, Long> {


}
