package app.service;


import app.CustomException.EntityNotFoundException;

import app.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import app.model.Account;
import app.model.Customer;
import org.springframework.stereotype.Service;
import app.utils.CustomCurrency;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) {
            throw new EntityNotFoundException("No Customer by id : " + id);
       }
        return customerOpt.get();
    }

    public List<Customer> getAllCustomers() {
        List<Customer> mylist = customerRepository.findAll();;
        if (mylist.isEmpty()) {
            throw new EntityNotFoundException("No Customer found");
        }
        System.out.println(customerRepository.findAll());
        return mylist;
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
        System.out.println(customerRepository.findAll());
    }

    public void changeCustomer(Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("No Customer by id : " + id);
        }
        customer.setId(id); // Устанавливаем ID клиента, чтобы обновить существующего клиента
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("No Customer by id : " + id);
        }
        customerRepository.deleteById(id);
    }

    public void createAccountForCustomer(Long id , CustomCurrency currency) {
       Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty()) throw new EntityNotFoundException("No Customer by id : " + id);
        customerOpt.get().getAccounts().add(new Account(currency , customerOpt.get()));
    }

    public void deleteAccountForCustomer(Long id , Account account ) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isEmpty())  throw new EntityNotFoundException("No Customer by id : " + id);
        if (!customerOpt.get().getAccounts().remove(account)) {
            throw new EntityNotFoundException("Not found this Account : " + account);
        }
    }
}
