package com.itgarden.product.service;

import com.itgarden.product.common.CodeGenerator;
import com.itgarden.product.common.staticdata.CodeType;
import com.itgarden.product.common.staticdata.Constants;
import com.itgarden.product.common.staticdata.STATUS;
import com.itgarden.product.dto.OfferInfo;
import com.itgarden.product.entity.Offer;
import com.itgarden.product.mapper.OfferMapper;
import com.itgarden.product.messages.ResponseMessage;
import com.itgarden.product.repository.OfferRepository;
import com.itgarden.product.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Service
public class OfferService extends BaseService<OfferInfo> {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    public ResponseMessage save(OfferInfo offerInfo) throws Exception {

        Offer offer = OfferMapper.INSTANCE.offerInfoToOffer(offerInfo);
        if(StringUtils.isEmpty(offer.getOfferCode())) {
            String offerCode = codeGenerator.newCode(CodeType.OFFER_CODE);
            offer.setOfferCode(offerCode);
            if(offer.getStatus().isEmpty()) {
                offer.setStatus(STATUS.PENDING.name());
            }
        }
        Offer newOffer = offerRepository.save(offer);
        OfferInfo newOfferInfo = OfferMapper.INSTANCE.offerToOfferInfo(newOffer);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(newOfferInfo, "Offer saved successfully", Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(Long id) throws Exception {
        Offer offer = offerRepository.findById(id).orElse(null);
        OfferInfo offerInfo = OfferMapper.INSTANCE.offerToOfferInfo(offer);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(offerInfo, Constants.SUCCESS_STATUS, Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceByCode(String code) throws Exception {
        return null;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<OfferInfo> offerInfos = new ArrayList<>();
        List<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
            OfferInfo offerInfo = OfferMapper.INSTANCE.offerToOfferInfo(offer);
            offerInfos.add(offerInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(offerInfos, Constants.SUCCESS_STATUS, Constants.INFO_TYPE);
        return responseMessage;
    }
}
