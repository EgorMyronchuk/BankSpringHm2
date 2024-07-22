package app.dao;


import app.CustomException.EntityNotFoundException;
import app.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerDao implements Dao<Customer> {

    List<Customer> customers = new ArrayList<Customer>();

    @Override
    public Customer save(Customer obj) {
        if (customers.isEmpty()) {
            obj.setId(0L);
        }else {
            obj.setId(customers.get(customers.size() - 1).getId() + 1L);
        }
        customers.add(obj);
        return obj;
    }

    public Customer update(Customer obj) {
        Customer indexOfPrevObj = customers.stream()
                .filter(x -> Objects.equals(x.getId(), obj.getId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No Customer by id : "));
        customers.set(customers.indexOf(indexOfPrevObj) , obj);
        return obj;
    }

    @Override
    public boolean delete(Customer obj) {
        return customers.remove(obj);
    }

    @Override
    public void deleteAll(List<Customer> entities) {
       entities.forEach(this::save);
    }

    @Override
    public void saveAll(List<Customer> entities) {
        entities.forEach(this::save);
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public boolean deleteById(long id) {
        return customers.removeIf(x -> x.getId() == id);
    }

    @Override
    public Customer getOne(long id) {
        return customers.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }
}
