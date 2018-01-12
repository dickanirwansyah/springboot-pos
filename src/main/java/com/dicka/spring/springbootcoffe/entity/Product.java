package com.dicka.spring.springbootcoffe.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product",
        catalog = "users")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unitprice", nullable = false)
    private int unitprice;


    public Long getProductId(){
        return productId;
    }

    public void setProductId(Long productId){
        this.productId=productId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getUnitprice(){
        return unitprice;
    }

    public void setUnitprice(int unitprice){
        this.unitprice=unitprice;
    }
}
