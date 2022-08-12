package com.se.sample.product;


import javax.persistence.*;


@Entity
@Table(name="products")
public class Product {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length=120)
    private String status;

    private int count;

    private Double price;

    public Product() {

    }

    public Product(String status, int count, Double price) {
        this.status = status;
        this.count = count;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [count=" + count + ", id=" + id + ", name=" + status + ", price=" + price + "]";
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String name) {
        this.status = name;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }


}