package com.itgarden.product.service;

import com.itgarden.product.common.CodeGenerator;
import com.itgarden.product.common.staticdata.CodeType;
import com.itgarden.product.common.staticdata.Constants;
import com.itgarden.product.dto.CategoryInfo;
import com.itgarden.product.entity.Category;
import com.itgarden.product.mapper.CategoryMapper;
import com.itgarden.product.messages.ResponseMessage;
import com.itgarden.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@Service
public class CategoryService extends BaseService<CategoryInfo> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CodeGenerator codeGenerator;

    public ResponseMessage save(CategoryInfo categoryInfo) throws Exception {
        Category category = CategoryMapper.INSTANCE.categoryInfoToCategory(categoryInfo);
        if(StringUtils.isEmpty(category.getCategoryCode())) {
            String categoryCode = codeGenerator.newCode(CodeType.CATEGORY_CODE);
            category.setCategoryCode(categoryCode);
        }
        Category newCategory = categoryRepository.save(category);
        CategoryInfo categoryInfoResponse = CategoryMapper.INSTANCE.categoryToCategoryInfo(newCategory);


        ResponseMessage responseMessage = ResponseMessage.withResponseData(categoryInfoResponse, "Category Saved" +
                " Successfully", Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceById(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElse(null);
        CategoryInfo categoryInfoResponse = CategoryMapper.INSTANCE.categoryToCategoryInfo(category);
        ResponseMessage responseMessage = ResponseMessage.withResponseData(categoryInfoResponse, Constants.SUCCESS_STATUS, Constants.INFO_TYPE);
        return responseMessage;
    }

    @Override
    public ResponseMessage findResourceByCode(String code) throws Exception {
        return null;
    }

    @Override
    public ResponseMessage findAll() throws Exception {
        List<CategoryInfo> categoryInfos = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for (Category category:categories) {
            CategoryInfo categoryInfo = CategoryMapper.INSTANCE.categoryToCategoryInfo(category);
            categoryInfos.add(categoryInfo);
        }
        ResponseMessage responseMessage = ResponseMessage.withResponseData(categoryInfos,Constants.SUCCESS_STATUS,Constants.INFO_TYPE);
        return responseMessage;
    }
}
