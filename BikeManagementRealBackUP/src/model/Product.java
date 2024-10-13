
package model;

import java.io.Serializable;


public class Product implements Serializable{
    private String id;
    private String name;
    private String brandId;
    private String categoryId;
    private int modelYear;
    private float price;

     public Product() {
       
    }
    public Product(String id, String name, String brandId, String categoryId, int modelYear, float price) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandId;
    }

    public void setBrandName(String brandId) {
        this.brandId = brandId;
    }

    public String getCategoryName() {
        return categoryId;
    }

    public void setCategoryName(String categoryId) {
        this.categoryId = categoryId;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    }
    

    
