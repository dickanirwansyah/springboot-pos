package com.dicka.spring.springbootcoffe.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customerorder",
catalog = "users")
public class CustomerOrder implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "totalprice", nullable = false)
    private Double totalprice;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    fetch = FetchType.LAZY)
    @JoinTable(name = "order_products",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private Set<Product> products;

    @OneToOne
    private Customer customer;

    public Long getOrderId(){
        return orderId;
    }

    public void setOrderId(Long orderId){
        this.orderId=orderId;
    }

    public Double getTotalprice(){
        return totalprice;
    }

    public void setTotalprice(Double totalprice){
        this.totalprice=totalprice;
    }

    public Set<Product> getProducts(){
        return products;
    }

    public void setProducts(Set<Product> products){
        this.products=products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
