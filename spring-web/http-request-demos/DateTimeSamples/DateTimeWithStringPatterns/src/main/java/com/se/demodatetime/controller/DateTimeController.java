package com.se.demodatetime.controller;

import com.se.demodatetime.model.DateTimeDto;
import com.se.demodatetime.model.FormattedDateTimeDto;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
@RequestMapping("api/")
public class DateTimeController {

    private final Logger logger = LoggerFactory.getLogger(DateTimeController.class);

    /**
     * Конвертировать параметры даты на уровне запроса
     *
     * @param date
     */
//    @PostMapping("/date")
//    public void date(@RequestParam("date") Date date) {
//        /**
//         * Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDate';
//         *   nested exception is org.springframework.core.convert.ConversionFailedExceptio
//         */
//    }
//
    private Clock clock = Clock.fixed(Instant.parse("2019-02-05T16:45:42.01Z"), ZoneId.of("Europe/Paris"));


    @ApiOperation(value = "Base Time",
            notes = "Base local date time", tags = {"get"}
            , response = Void.class)
    @GetMapping("/local-date-time")
    public LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    @ApiOperation(value = "Time",
            notes = "Current time", tags = {"get"}
            , response = Void.class)
    @GetMapping("/time")
    public DateTimeDto timeMapping() {
        return new DateTimeDto(clock);
    }

    @ApiOperation(value = "Formatted result",
            notes = "Formatted Time Mapping", tags = {"get"}
            , response = Void.class)
    @GetMapping("/formatted")
    public DateTimeDto formattedTimeMapping() {
        return new FormattedDateTimeDto(clock);
    }

    @ApiOperation(value = "Test Date as a param",
            notes = "Date as a param with ISO Date Date format", tags = {"set"}
            , response = Void.class)
    @PostMapping("/date")
    public void date(@ApiParam(defaultValue = "2000-10-31" )
                     @RequestParam("date")
                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {

        logger.info(" ** Date : {}", date);
    }

    // The problem (one of the problems actually) with java.util.Date is that it's really a date-time,
    // and swagger correctly detects it as such. I do understand that the @JsonFormat
    @ApiOperation(value = "Test set Date 2",
               notes = "Date with custom pattern", tags = {"set"})
    @PostMapping("/date-custom")
    public void dateCustom( @ApiParam(required = true, defaultValue="30.10.2010", type="java.util.Date")
                            @RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy") Date date) {
        logger.info("** Date, Custom pattern : {}", date);
    }

    @PostMapping("/local-date")
    public void localDate( @ApiParam(required = true, defaultValue="2000-10-31")
            @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
          logger.info("LocalDate : {}", localDate);
    }

    @ApiOperation(value = "Test set local date time",
            notes = "Test set local date time", tags = {"set"})
    @PostMapping("/local-date-time")
    public void dateTime( @ApiParam(required = true, defaultValue="2000-10-31T01:30:00.000-05:00")
                         @RequestParam("localDateTime")
                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        logger.info("LocalDateTime : {}", localDateTime);
    }

    /**
     * for test return as a time stamp
     * org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
     *
     * SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false | true;
     * @return
     */
    @ApiOperation(value = "Instant now", tags = {"get"}
            , response = Void.class)
    @GetMapping("/now")
    public Instant getNow() {
        return Instant.now();
    }

    @ApiOperation(value = "LocalDateTime now", tags = {"get"})
    @GetMapping("/now2")
    public LocalDateTime getNow2() {
        return LocalDateTime.now();
    }
}