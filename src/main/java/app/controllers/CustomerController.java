package app.controllers;

import lombok.RequiredArgsConstructor;
import app.model.Account;
import app.model.Customer;
import org.springframework.web.bind.annotation.*;
import app.service.CustomerService;
import app.utils.CustomCurrency;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }

    @PutMapping("{id}") //After change on token
    public void changeCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.changeCustomer(id, customer);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @PostMapping("{id}/accounts")
    public void createAccountForCustomer(@PathVariable Long id, @RequestParam CustomCurrency currency) {
        customerService.createAccountForCustomer(id, currency);
    }

    @DeleteMapping("{id}/accounts")
    public void deleteAccountForCustomer(@PathVariable Long id, @RequestBody Account account) {
        customerService.deleteAccountForCustomer(id, account);
    }
}
