package com.loiko.alex.customerservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
public class Customer extends RepresentationModel<Customer> {

    @Id
    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "comment")
    private String comment;

    public Customer withComment(String comment) {
        setComment(comment);
        return this;
    }
}