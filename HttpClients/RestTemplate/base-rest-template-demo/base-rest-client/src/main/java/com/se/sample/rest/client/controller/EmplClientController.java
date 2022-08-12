package com.se.sample.rest.client.controller;


import com.se.sample.rest.client.exception.NotFoundException;
import com.se.sample.rest.client.model.request.EmployeeRequest;
import com.se.sample.rest.client.model.response.EmployeeResponse;
import com.se.sample.rest.client.model.response.EmployeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/empl-client")
@Api(value = "Controller for communicate with Rest Template")
public class EmplClientController {

    private static final String emplUrl = "http://localhost:8001/empl/add";
    private static final String emplUrl2 = "http://localhost:8001/empl/add2";
    private static final String emplUrlGetById = "http://localhost:8001/empl";

    private final Logger logger = LoggerFactory.getLogger(EmplClientController.class);

    @Autowired
    RestTemplate restTemplate;


    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeVO> getEmployeeById(@PathVariable("id") Long id) {
        String url = emplUrlGetById + "/{id}";
        try {

            logger.debug("Send get request  to: {} ", url);
            Map<String, Long> vars = new HashMap<>();
            vars.put("id", id);

            EmployeeVO result = restTemplate.getForObject(url, EmployeeVO.class, vars);
            logger.debug("Received activated user with status: {} ", result);
            return ResponseEntity.ok(result);

        } catch (Exception ex) {
            logger.error("An error while send to : {}", url);
            throw new NotFoundException(String.format("Error while validating session by token: %s", ex.getMessage()));
        }
    }

    @PostMapping(value = "/add")
    public ResponseEntity<EmployeeResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        ResponseEntity<EmployeeResponse> result = restTemplate.postForEntity(emplUrl, employeeRequest, EmployeeResponse.class);
        logger.info("response: " + result.getBody());
        return result;

    }


    @PostMapping(value = "/add-exception")
    public ResponseEntity<EmployeeResponse> addEmployee2(@Valid @RequestBody EmployeeRequest employeeRequest) {
        ResponseEntity<EmployeeResponse> result = restTemplate.postForEntity(emplUrl2, employeeRequest, EmployeeResponse.class);
        logger.info("response: " + result.getBody());
        return result;
    }
}
