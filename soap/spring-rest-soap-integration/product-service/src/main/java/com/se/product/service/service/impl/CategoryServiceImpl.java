package com.se.product.service.service.impl;

import com.se.product.service.domain.Category;
import com.se.product.service.domain.specification.CategorySpecification;
import com.se.product.service.exception.AlreadyExistException;
import com.se.product.service.exception.model.ResourceNotFoundException;
import com.se.product.service.mapper.CategoryMapper;
import com.se.product.service.model.request.CategoryRequest;
import com.se.product.service.model.response.CategoryResponse;
import com.se.product.service.model.response.CategoryResponseList;
import com.se.product.service.model.search.CategorySearch;
import com.se.product.service.repository.CategoryRepository;
import com.se.product.service.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;
    private final CategorySpecification categorySpecification;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategorySpecification categorySpecification) {
        this.categoryRepository = categoryRepository;
        this.categorySpecification = categorySpecification;
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        validateCategoryName(request.getName());
        validateCategoryCode(request.getCode());
        validateBaseCategory(request.getBaseCategory());

        Category category = CategoryMapper.MAPPER.toCategory(request);

        // TODO: tmp
        if(category.getBaseCategory() == 0){
            category.setBaseCategory(null);
            category.setBaseCategory(null);
        }
        category = categoryRepository.save(category);
        return CategoryMapper.MAPPER.toCategoryResponse(category);

    }

    private void validateCategoryCode(String categoryCode) {

        boolean exists = categoryRepository.existsAllByCode(categoryCode);

        if (exists)
            throw new AlreadyExistException("Category", "code", categoryCode);
    }

    private void validateCategoryName(String categoryName) {
        boolean exists = categoryRepository.existsAllByName(categoryName);

        if (exists)
            throw new AlreadyExistException("Category", "name", categoryName);
    }

    private void validateBaseCategory(Long baseCategoryId) {
        boolean exists;
        if (baseCategoryId != null) {
            exists = categoryRepository.existsById(baseCategoryId);

            if (exists) {
                throw new ResourceNotFoundException("Category", "base category id", baseCategoryId);
            }
        }
    }

    @Override
    public CategoryResponse updateItem(Long id, CategoryRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        validateCategoryName(request.getName());
        validateCategoryCode(request.getCode());
        validateBaseCategory(request.getBaseCategory());

        category.setBaseCategory(request.getBaseCategory());
        category.setCode(request.getCode());
        category.setName(request.getName());

        categoryRepository.save(category);

        return CategoryMapper.MAPPER.toCategoryResponse(category);

    }

    @Override
    public CategoryResponse changeBase(Long id, Long baseId) {
        // TODO:
        return null;
    }

    @Override
    public void remove(Long id) {
        // TODO: check is categories uses
        logger.debug("Remove category id:{}", id);
        Category item = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        categoryRepository.delete(item);

        logger.info("Category :{} removed ", id);
    }

    @Override
    public CategoryResponse getById(Long id) {
        return categoryRepository.findById(id)
                .map(category -> CategoryMapper.MAPPER.toCategoryResponse(category))
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
    }

    @Override
    public CategoryResponseList getAll() {
        List<CategoryResponse> categoriesList = categoryRepository.findAll()
                .stream()
                .map(CategoryMapper.MAPPER::toCategoryResponse)
                .collect(Collectors.toList());
        logger.debug("Get all categories. Found: {} items.", categoriesList.size());

        CategoryResponseList categoryResponseList = new CategoryResponseList();
        categoryResponseList.setCategories(categoriesList);
        return categoryResponseList;
    }

    @Override
    public Page<CategoryResponse> search(CategorySearch request,
                                         @PageableDefault(page = 0, size = 20,
                                                 sort = "createdAt",direction = Sort.Direction.DESC) Pageable pageable) {

        Page<CategoryResponse> userPage =
                categoryRepository.findAll(categorySpecification.getFilter(request), pageable)
                .map(CategoryMapper.MAPPER::toCategoryResponse);
        return userPage;
    }
}
