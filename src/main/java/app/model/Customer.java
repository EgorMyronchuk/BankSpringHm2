package app.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Customer {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private List<Account> accounts;

    public Customer(String name , String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        accounts = new ArrayList<Account>();
    }

}
