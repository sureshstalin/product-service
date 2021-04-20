package com.itgarden.product.service;

import com.itgarden.product.common.staticdata.Constants;
import com.itgarden.product.dto.TaxInfo;
import com.itgarden.product.entity.Tax;
import com.itgarden.product.mapper.TaxMapper;
import com.itgarden.product.messages.ResponseMessage;
import com.itgarden.product.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Service
public class TaxService extends BaseService<TaxInfo> {


    @Autowired
    private TaxRepository taxRepository;

    /*
     * This method for adding new Tax Records and updating existing Tax Records
     */
    public ResponseMessage save(TaxInfo taxInfo) throws Exception {
        Tax tax = TaxMapper.INSTANCE.taxInfoToTax(taxInfo);
        Tax newTax = taxRepository.save(tax);
        TaxInfo newTaxInfoResponse = TaxMapper.INSTANCE.taxToTaxInfo(newTax);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(newTaxInfoResponse,"Tax saved successfully",Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(Long id) throws Exception {
        Tax tax = taxRepository.findById(id).orElse(null);
        TaxInfo newTaxInfoResponse = TaxMapper.INSTANCE.taxToTaxInfo(tax);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(newTaxInfoResponse,"Tax saved successfully",Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceByCode(String code) throws Exception {
        return null;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<Tax> taxes = taxRepository.findAll();
        List<TaxInfo> taxInfos = new ArrayList<>();
        for (Tax tax: taxes) {
            TaxInfo taxInfo = TaxMapper.INSTANCE.taxToTaxInfo(tax);
            taxInfos.add(taxInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(taxInfos, Constants.SUCCESS_STATUS, Constants.INFO_TYPE);
        return responseMessage;
    }
}
