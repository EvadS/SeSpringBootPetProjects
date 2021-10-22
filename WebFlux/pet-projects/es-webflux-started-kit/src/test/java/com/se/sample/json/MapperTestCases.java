package com.se.sample.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.se.sample.entity.Product;
import com.se.sample.json.model.Car;
import com.se.sample.json.model.Request;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.TemporaryFolder;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

/**
 * check Object Mapper works with data time
 *
 */

public class MapperTestCases {
   // @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    final String EXAMPLE_JSON = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
    final String LOCAL_JSON = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"BMW\" }]";

    @Test
    public void whenWriteToFile_thanCorrect() throws Exception {
        File resultFile = new File("car.json");

        System.out.println("========================================");
        System.out.println("file: "+ resultFile.getAbsolutePath());
        System.out.println("========================================");
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "renault");
        objectMapper.writeValue(resultFile, car);

        Car fromFile = objectMapper.readValue(resultFile, Car.class);
        Assert.assertEquals(car.getType(), fromFile.getType());
        Assert.assertEquals(car.getColor(), fromFile.getColor());
    }


    @Test
    void test1() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car("yellow", "renault");
        objectMapper.writeValue(new File("target/car.json"), car);

        String carAsString = objectMapper.writeValueAsString(car);

        String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
        Car car2 = objectMapper.readValue(json, Car.class);

     //   Car car = objectMapper.readValue(new File("src/test/resources/json_car.json"), Car.class);
    }

    @Test
    void test_JsonNode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }";
        JsonNode jsonNode = objectMapper.readTree(json);
        String color = jsonNode.get("color").asText();
// Output: color -> Black
        Assert.assertEquals("Black", color);

    }

    @Test
    void testDateFormat () throws JsonProcessingException {
        Car car = new Car();
        car.setColor("Green");
        car.setType("FIAT");

        Request request = new Request();
        request.setCar(car);
        request.setDatePurchased(new Date());

        LocalDateTime aDateTime = LocalDateTime.of(2015,
                Month.JULY, 29, 19, 30, 40);

        System.out.println("some datetime : " + aDateTime);
        request.setUpdatedAt(aDateTime);

        /// --------------------------------- create date
        // Creating a Calendar object
        Calendar c1 = Calendar.getInstance();

        // Set Month
        // MONTH starts with 0 i.e. ( 0 - Jan)
        c1.set(Calendar.MONTH, 06);

        // Set Date
        c1.set(Calendar.DATE, 12);

        // Set Year
        c1.set(Calendar.YEAR, 1996);

        // Creating a date object
        // with specified time.
        Date dateOne = c1.getTime();
        Instant instant = dateOne.toInstant();
        request.setCreatedDate(instant);
        //---------------------------------------------------

        ObjectMapper objectMapper = new ObjectMapper();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm::ss a z");
        objectMapper.setDateFormat(df);

        //time type `java.time.LocalDateTime` not supported by default:
        objectMapper.registerModule(new JavaTimeModule());

        String carAsString = objectMapper.writeValueAsString(request);
        System.out.println("car as a string \n --------- \n"+ carAsString +  "\n------------");

        Request carFromJson = objectMapper.readValue(carAsString, Request.class);

        Assert.assertEquals(carFromJson.getDatePurchased().getMonth(), request.getDatePurchased().getMonth());
        Assert.assertEquals(carFromJson.getUpdatedAt(), request.getUpdatedAt());
        Assert.assertEquals(carFromJson.getCreatedDate(), request.getCreatedDate());
    }

    @Test
    void product_from_string_should_parse_correct() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();


        //time type `java.time.LocalDateTime` not supported by default:
        objectMapper.registerModule(new JavaTimeModule());
        DateFormat df = new SimpleDateFormat("uuuuMMdd'T'HHmmss.SSSXXX");
        objectMapper.setDateFormat(df);

        Product product = new Product();
        product.setId("agkao3wBt81NUU8mBQtL");
        product.setName("name");
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedDate(Instant.now());

        String productAsString = objectMapper.writeValueAsString(product);

        System.out.println("updated at "+ product.getUpdatedAt());
        System.out.println("product: " + productAsString);

      //  String value  = objectMapper.writeValueAsString(request);
        Product productFromJson = objectMapper.readValue(request, Product.class);
       Assert.assertNotNull(productFromJson);
    }

    String request =
            " {\n" +
            "    \"id\": \"agkao3wBt81NUU8mBQtL\",\n" +
            "    \"name\": \"1111111111311111111111111111111\",\n" +
            "    \"price\": 0.001,\n" +
            "    \"quantity\": 1,\n" +
            "    \"category\": \"category\",\n" +
            "    \"desc\": \"Description\",\n" +
            "    \"manufacturer\": \"Manufacturer\",\n" +
            "    \"updatedAt\": \"2021-10-22T12:22:30.894529500\",\n" +
            "    \"createdDate\": \"2021-10-22T09:25:02.118323200Z\"\n" +
            "  }";

// 2021-10-22T12:22:30.894529500
//     \"updatedAt\": 1634823957213,\n" +
}
