package app.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.utils.CustomCurrency;


import java.util.UUID;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Account {
    private Long id;
    private String number;
    private CustomCurrency currency;
    private Double balance;
    private Customer customer;


    public Account(CustomCurrency currency, Customer customer) {
        this.currency = currency;
        this.customer = customer;
        this.balance = 0.0;
        this.number = UUID.randomUUID().toString();
    }
}
