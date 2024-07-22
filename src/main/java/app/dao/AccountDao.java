package app.dao;

import app.CustomException.EntityNotFoundException;
import app.model.Account;
import app.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Arrays.stream;

@Repository
public class AccountDao implements Dao<Account> {

    List<Account> accounts = new ArrayList<Account>();

    @Override
    public Account save(Account obj) {
        if (accounts.isEmpty()) {
            obj.setId(0L);
        }else {
            obj.setId(accounts.get(accounts.size() - 1).getId() + 1L);
            accounts.add(obj);
        }
        return obj;
    }

    public Account update(Account obj) {
        Account indexOfPrevObj = accounts.stream()
                .filter(x -> Objects.equals(x.getId(), obj.getId()))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No Customer by id : "));
        accounts.set(accounts.indexOf(indexOfPrevObj) , obj);
        return obj;
    }

    @Override
    public boolean delete(Account obj) {
      return accounts.remove(obj);
    }

    @Override
    public void deleteAll(List<Account> entities) {
        accounts.removeAll(entities);
    }

    @Override
    public void saveAll(List<Account> entities) {
      entities.forEach(this::save);
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public boolean deleteById(long id) {
        return accounts.removeIf(x -> x.getId() == id);
    }

    @Override
    public Account getOne(long id) {
        return accounts.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public Optional<Account> findByNumber (String number) {
        return accounts
                .stream()
                .filter(x -> x.getNumber().equals(number))
                .findFirst();
    }
}