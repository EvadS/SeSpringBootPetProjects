package com.se.product.service.controller.base;

import com.se.product.service.model.PriceResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface PriceControllerBase {





    @ApiOperation(value = "Prices list with pagination", nickname = "pagged-base",
            notes = "Manual pageable price list with  manual pageable.",
            tags = {})

    ResponseEntity<Page<PriceResponse>> getPaged(
            @ApiParam(value = "Current page number.", example = "0", required = false)
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @ApiParam(value = "Number entities on page.", example = "0", required = false)
            @RequestParam(required = false, defaultValue = "10") Integer count,
            @ApiParam(value = "Field records will be sorted based on. By default is priority.",
                    required = false, example = "0")
            @RequestParam(required = false, defaultValue = "") String filter) ;



}
