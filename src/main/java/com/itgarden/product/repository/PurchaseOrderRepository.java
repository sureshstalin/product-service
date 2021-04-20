package com.itgarden.product.repository;

import com.itgarden.product.common.staticdata.PurchaseOrderStatus;
import com.itgarden.product.entity.PurchaseOrder;
import com.itgarden.product.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {

    PurchaseOrder findPurchaseOrderByVendorIdAndProductNameAndPurchaseOrderStatus
            (Long vendorId, String productName, PurchaseOrderStatus purchaseOrderStatus);
}
