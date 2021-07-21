package com.se.product.service.controller;

import com.se.product.service.controller.base.PriceControllerBase;
import com.se.product.service.model.PriceRequest;
import com.se.product.service.model.PriceResponse;
import com.se.product.service.service.PriceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/price")
public class PriceController implements PriceControllerBase {

    private static Logger logger = LoggerFactory.getLogger(PriceController.class);
    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping
    @ApiOperation(value = "Creat price.", nickname = "update-price",
            notes = "Create price.", tags = {})
    public ResponseEntity<PriceResponse> create(@Valid @RequestBody PriceRequest request) {

        logger.debug("handle create price request {}", request);
        PriceResponse pricResponse = priceService.create(request);
        return new ResponseEntity<>(pricResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update price.", nickname = "update-price",
            notes = "Update price.", tags = {})
    public ResponseEntity<?> update(
            @ApiParam(value = "ID of price", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long priceId,
            @ApiParam(value = "Price  details for update", required = true)
            @Valid @RequestBody PriceRequest requestModel) {

        logger.debug("handle update price, id:{}, model: {}",priceId , requestModel );

        PriceResponse articleResponse = priceService.updatePrice(priceId, requestModel);
        return new ResponseEntity<>(articleResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete price.", nickname = "delete",
            notes = "Delete price by id.",
            tags = {})
    public ResponseEntity<?> deletePrice(
            @ApiParam(value = "ID of price to return", required = true, example = "123")
            @PathVariable(value = "id") @NotNull Long id) {
        logger.debug("handle delete price request, id:{}",id);
        priceService.deletePrice(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Price.", nickname = "price-get",
            notes = "Get price by id.",tags = {})
    public ResponseEntity<PriceResponse> getById(
            @ApiParam(value = "ID of price to return", required = true, example = "123")
            @PathVariable(value = "id") Long priceId) {
        logger.debug("handle get price by id: {}",priceId);

        PriceResponse priceResponse = priceService.getById(priceId);
        return ResponseEntity.ok(priceResponse);
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "Current prices", nickname = "list",
            notes = "Prices list.",tags = {})
    public ResponseEntity<?> list() {
        logger.debug("handle list of price request");

        List<PriceResponse> priceResponseList = priceService.getAll();
        return ResponseEntity.ok(priceResponseList);
    }


    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public ResponseEntity<Page<PriceResponse>> getPaged(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer count,
            @RequestParam(required = false, defaultValue = "") String filter) {

        logger.debug("handle pagged price request, start:{}, count:{}",page,count);

        Pageable pageable = (filter == null) ? PageRequest.of(page, count) : PageRequest.of(page, count, Sort.by("id"));
        Page<PriceResponse> articleResponsePage = priceService.getPaged(pageable, filter);

        return ResponseEntity.ok(articleResponsePage);
    }
}
