# A Step by Step guide on Implementing CRUD Restful API tests with Junit5 + REST-assured

## Introduction

We had a previous article published on <ExternalLink href="https://2much2learn.com/crud-rest-api-using-spring-boot-spring-data-jpa">Creating CRUD Restful APIs using SpringBoot + Spring Data JPA</ExternalLink>. Below are the operations the application supports.

| HTTP <br/> Method | API Name | Path | Response <br/> Status Code |
| -------- | ----------------- | ---- | -------------------------- |
| POST | Create Catalogue Item | / | 201<br/>(Created) |
| GET | Get Catalogue Items | / | 200<br/>(Ok) |
| GET | Get Catalogue Item | /{sku} | 200<br/>(Ok) |
| PUT | Update Catalogue Item | /{sku} | 200<br/>(Ok) |
| DELETE | Delete Catalogue Item | /{sku} | 204<br/>(No Content) |
| POST | Upload Catalog Item Picture | /{sku}/image | 201<br/>(Created) |

We will be implementing our API tests for these operations that are exposed via the application to manage items for a Catalogue Management System.

## Technology stack for implementing Rest-assured tests for CRUD Restful APIs…

* Redhat OpenJDK 8
* JUnit5
* REST-assured
* Hamcrest
* Project Lombok
* Maven v3.6.3
* Gradle v6.1.1
* IntelliJ Idea v2019.3.2

## Structure of test class with different test methods

Below are the tests that we are planning to include as part of this Test class:

* Application Health Check
* Create Catalogue Item
* Get Catalogue Items
* Get Catalogue Item by SKU
* Update Catalogue Item by SKU
* Delete Catalogue Item by SKU
* Resource Not Found
* Handler Not Found
* Handling Validation Errors
* Handling Invalid Request

## Executing from Maven Build

Run the below command to execute the tests by maven

```bash
~:\> mvn clean test
```

If tests are successful, something like this should be displayed.

```bash
[INFO] Compiling 4 source files to E:\Projects\2much2learn\2much2learn_examples\testing\rest-assured-crud-api-tests\target\test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.22.2:test (default-test) @ rest-assured-crud-api-tests ---
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running org.toomuch2learn.crud.catalogue.RestAssuredCatalogueCRUDTest
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.373 s - in org.toomuch2learn.crud.catalogue.RestAssuredCatalogueCRUDTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  9.681 s
[INFO] Finished at: 2020-02-22T21:30:04+11:00
[INFO] ------------------------------------------------------------------------
```