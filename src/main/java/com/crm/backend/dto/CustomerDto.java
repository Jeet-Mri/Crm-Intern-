package com.crm.backend.dto;

import com.crm.backend.model.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CustomerDto {
    @NotBlank(message = "Customer name should not be blank")
    private String firstName;

    @NotBlank(message = "Customer last name should not be blank")
    private String lastName;

    @NotBlank(message = "Customer email should not be blank")
    private String email;

    @NotBlank(message = "Customer password should not be blank")
    private String password;

    @NotBlank(message = "Customer phone number should not be blank")
    private String phoneNo;

    @NotNull(message = "Customer is assigned to which sales representative should not be null")
    private int assignedTo;

    public Customer addCustomer(){
        return Customer.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .password(this.password)
                .phoneNo(this.phoneNo)
                .assignedTo(this.assignedTo)
                .build();
    }
}
