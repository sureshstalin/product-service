package com.itgarden.product.service;


import com.itgarden.product.common.CodeGenerator;
import com.itgarden.product.common.TaxCalculation;
import com.itgarden.product.common.TaxCalculationInput;
import com.itgarden.product.common.TaxCalculationResponse;
import com.itgarden.product.common.staticdata.CodeType;
import com.itgarden.product.dto.PurchaseOrderInfo;
import com.itgarden.product.entity.Category;
import com.itgarden.product.entity.PurchaseOrder;
import com.itgarden.product.entity.Tax;
import com.itgarden.product.mapper.PurchaseOrderMapper;
import com.itgarden.product.messages.ResponseMessage;
import com.itgarden.product.repository.CategoryRepository;
import com.itgarden.product.repository.PurchaseOrderRepository;
import com.itgarden.product.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseOrderService extends BaseService<PurchaseOrderInfo> {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private CodeGenerator codeGenerator;
//
//    @Autowired
//    private VendorRepository vendorRepository;

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private TaxCalculation taxCalculation;

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseMessage<PurchaseOrderInfo> save(PurchaseOrderInfo purchaseOrderInfo) {

        PurchaseOrder purchaseOrder = PurchaseOrderMapper.INSTANCE
                .purchaseOrderInfoToPurchaseOrder(purchaseOrderInfo);
        if (purchaseOrderInfo.getId() == null) {
            Category category = categoryRepository.findById(purchaseOrderInfo.getCategory().getId()).orElse(null);
            purchaseOrder.setCategory(category);
//            Vendor vendor = vendorRepository.getOne(purchaseOrder.getVendor().getId());
            // need webservice
//            purchaseOrder.setVendor(vendor);
            Tax tax = taxRepository.getOne(purchaseOrder.getTax().getId());
            purchaseOrder.setTax(tax);
            String purchaseOrderCode = codeGenerator.newCode(CodeType.PURCHASE_ORDER_CODE);
            purchaseOrder.setPurchaseOrderCode(purchaseOrderCode);
//            purchaseOrder.setPurchaseOrderStatus(PurchaseOrderStatus.PENDING);
        }
        TaxCalculationInput taxCalculationInput =
                new TaxCalculationInput(
                        purchaseOrder.getUnitPrice(), purchaseOrder.getTax().getTaxPercentage(), purchaseOrder.getQuantity());
        TaxCalculationResponse taxCalculationResponse
                = taxCalculation.calculateTax(taxCalculationInput);
        purchaseOrder.setTotalAmount(taxCalculationResponse.getTotalAmount());
        purchaseOrder.setGrandTotal(taxCalculationResponse.getGrandTotal());
        purchaseOrder.setTaxAmount(taxCalculationResponse.getTaxAmount());
        PurchaseOrder newPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
        PurchaseOrderInfo newPurchaseOrderInfo = PurchaseOrderMapper
                .INSTANCE.purchaseOrderToPurchaseOrderInfo(newPurchaseOrder);
        ResponseMessage<PurchaseOrderInfo> responseMessage = ResponseMessage.withResponseData(newPurchaseOrderInfo, "", "");
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(Long id) throws Exception {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id).orElse(null);
        PurchaseOrderInfo purchaseOrderInfo = PurchaseOrderMapper.INSTANCE.purchaseOrderToPurchaseOrderInfo(purchaseOrder);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(purchaseOrderInfo, "", "");
        return responseMessage;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<PurchaseOrderInfo> purchaseOrderInfos = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        for (PurchaseOrder purchaseOrder : purchaseOrders) {
            PurchaseOrderInfo purchaseOrderInfo = PurchaseOrderMapper.INSTANCE.purchaseOrderToPurchaseOrderInfo(purchaseOrder);
            purchaseOrderInfos.add(purchaseOrderInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(purchaseOrderInfos, "", "");
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceByCode(String code) throws Exception {
        return null;
    }
//    public void findPurchaseOrderByVendorAndProductNameAndStatus(Long vendorId,String productName,
//                                                                 PurchaseOrderStatus purchaseOrderStatus) {
//        PurchaseOrder purchaseOrder
//    }
}
