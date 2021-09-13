package com.example.dao;

/**
 * @author Skiba Evgeniy
 * @date 02.09.2021
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Document(indexName = "sample")
public class Employee {

    @Id
    private String id;

    @Field(type = FieldType.Object)
    private Organization organization;
    @Field(type = FieldType.Object)
    private Department department;

    @NotBlank(message = "{employee.name.not.null}")
    private String name;

    @Min(value = 10, message = "{age.min.requirement}")
    @Max(value = 50, message = "{age.max.requirement}")
    private int age;

    private String position;

    @Field(name="customer_id")
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", organization=" + organization +
                ", department=" + department +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

}