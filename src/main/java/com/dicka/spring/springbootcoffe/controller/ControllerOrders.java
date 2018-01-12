package com.dicka.spring.springbootcoffe.controller;

import com.dicka.spring.springbootcoffe.entity.Customer;
import com.dicka.spring.springbootcoffe.entity.CustomerOrder;
import com.dicka.spring.springbootcoffe.entity.Product;
import com.dicka.spring.springbootcoffe.repository.CustomerRepository;
import com.dicka.spring.springbootcoffe.repository.OrderRepository;
import com.dicka.spring.springbootcoffe.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class ControllerOrders {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ControllerOrders(ProductRepository productRepository,
                            OrderRepository orderRepository,
                            CustomerRepository customerRepository){

        this.productRepository=productRepository;
        this.orderRepository=orderRepository;
        this.customerRepository=customerRepository;
    }

    @GetMapping(value = "/datas")
    @ResponseBody
    public ResponseEntity<List<CustomerOrder>> getAll(){
        List<CustomerOrder> orders = orderRepository.findAll();
        return new ResponseEntity<List<CustomerOrder>>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String productList(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("orders", orderRepository.findAll());
        return "orders";
    }

    @RequestMapping(value = "/createorder", method = RequestMethod.POST)
    @ResponseBody
    public String saveOrder(@RequestParam String firstname,
                            @RequestParam String lastname,
                            @RequestParam(value = "productid[]") Long productid[]){
        //save customer
        Customer customer = new Customer();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);

        customerRepository.save(customer);

        //save customer order
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customerRepository.findOne(customer.getCustomerId()));

        //save product from request customer
        Set<Product> productSet = new HashSet<Product>();
        for(Long productids:productid){
            productSet.add(productRepository.findOne(productids));
        }

        customerOrder.setProducts(productSet);
        Double total=0.0;
        for(Long productids:productid){
           total = total + (productRepository.findOne(productids).getUnitprice());
        }

        customerOrder.setTotalprice(total);
        orderRepository.save(customerOrder);

        return customerOrder.getOrderId().toString();
    }

    @PostMapping(value = "/removeorder")
    @ResponseBody
    public String removeOrder(@RequestParam Long Id){
        orderRepository.delete(Id);
        return Id.toString();
    }
}
