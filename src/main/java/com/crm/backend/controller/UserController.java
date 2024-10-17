package com.crm.backend.controller;

import com.crm.backend.dto.CustomerDto;
import com.crm.backend.dto.UserDto;
import com.crm.backend.model.Customer;
import com.crm.backend.model.User;
import com.crm.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("createAdmin")
    public ResponseEntity<User> createAdmin(@RequestBody UserDto userDto) {
        User createdAdmin = userService.addAdmin(userDto);
        return ResponseEntity.ok(createdAdmin);
    }

    @PostMapping("create_sales-representative")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createSalesRepresentative(@RequestBody UserDto userDto) {
        User createdSalesRep = userService.addSalesRepresentative(userDto);
        return ResponseEntity.ok(createdSalesRep);
    }


//    @PostMapping("admin/createCustomer")
//    public ResponseEntity<Customer> createCustomerByAdmin(@RequestBody CustomerDto customerDto) {
//        Customer createdCustomer = customerService.addCustomerByAdmin(customerDto);
//        return ResponseEntity.ok(createdCustomer);
//    }
//
//    @PostMapping("sales_representative/createCustomer")
//    public ResponseEntity<Customer> createCustomerBySalesRepresentative(@RequestBody CustomerDto customerDto) {
//        Customer createdCustomer = customerService.addCustomerBySalesRepresentative(customerDto);
//        return ResponseEntity.ok(createdCustomer);
//    }

    @GetMapping("/salesReps")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getSalesReps() {
        List<User> salesReps = userService.getAllSalesRepresentative();
        return ResponseEntity.ok(salesReps);
    }



}


