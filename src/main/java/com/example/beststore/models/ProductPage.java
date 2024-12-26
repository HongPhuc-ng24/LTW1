package com.example.beststore.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_page") // Tên bảng trong cơ sở dữ liệu
public class ProductPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private double price1;
    private String imageFilename;
    private String Sales;
    private String percent;
    private String title;
    public ProductPage() {
        this.id = id;
        this.name = name;
        this.price = price;
        this.price1 = price1;
        this.imageFilename = imageFilename;
        this.Sales = Sales;
        this.percent = percent;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSales() {
        return Sales;
    }

    public void setSales(String sales) {
        Sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice1() {
        return price1;
    }

    public void setPrice1(double price1) {
        this.price1 = price1;
    }



    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }
}

