package app.service;


import app.CustomException.EntityNotFoundException;
import app.dao.CustomerDao;
import lombok.RequiredArgsConstructor;
import app.model.Account;
import app.model.Customer;
import org.springframework.stereotype.Service;
import app.utils.CustomCurrency;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerDao customerDao;

    public Customer getCustomerById(Long id) {
        if (customerDao.getOne(id) == null) {
            throw new EntityNotFoundException("No Customer by id : " + id);
       }
        return customerDao.getOne(id);
    }

    public List<Customer> getAllCustomers() {
        List<Customer> mylist = customerDao.findAll();;
        if (mylist.isEmpty()) {
            throw new EntityNotFoundException("No Customer found");
        }
        System.out.println(customerDao.findAll());
        return mylist;
    }

    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
        System.out.println(customerDao.findAll());
    }

    public void changeCustomer(Customer customer) {
        customerDao.update(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerDao.deleteById(id)) throw new EntityNotFoundException("No Customer by id : " + id);
    }

    public void createAccountForCustomer(Long id , CustomCurrency currency) {
       Customer customer = customerDao.getOne(id);
        if (customerDao.getOne(id) == null) throw new EntityNotFoundException("No Customer by id : " + id);

        customer.getAccounts().add(new Account(currency , customer));
    }

    public void deleteAccountForCustomer(Long id , Account account ) {
        Customer customer = customerDao.getOne(id);
        if (customerDao.getOne(id) == null)  throw new EntityNotFoundException("No Customer by id : " + id);
        if (!customer.getAccounts().remove(account)) {
            throw new EntityNotFoundException("Not found this Account : " + account);
        }
    }
}
