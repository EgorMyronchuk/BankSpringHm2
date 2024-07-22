package app.service;

import app.dao.AccountDao;
import lombok.RequiredArgsConstructor;
import app.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountDao accountDao;

    public Optional<Account> deposit(String accountNumber , Double amount) {
        Optional<Account> account = accountDao.findByNumber(accountNumber);
        if (account.isPresent()) {
            Double balance = account.get().getBalance();
            account.get().setBalance(balance + amount);
            accountDao.update(account.get());
        }
        return account;
    }

    public Optional<Account> withdraw (String accountNumber , Double amount) {
        Function<Account , Boolean> enoughBalance = x -> x.getBalance() >= amount;
        Optional<Account> accountopt = accountDao.findByNumber(accountNumber);
        if (accountopt.isEmpty()) {
            return Optional.empty();
        }
        Account account = accountopt.get();
        if (enoughBalance.apply(account))  {
            account.setBalance(account.getBalance() - amount);
            accountDao.update(account);
            return accountopt;
        }
        return Optional.empty();
    }

    public Optional<List<Account>> transfer(String accountFromNumber, String accountToNumber, Double amount) {
        Optional<Account> fromAccount = accountDao.findByNumber(accountFromNumber);
        Optional<Account> toAccount = accountDao.findByNumber(accountToNumber);

        if (fromAccount.isEmpty() || toAccount.isEmpty()) {
            return Optional.empty();
        }
        if (withdraw(accountFromNumber, amount).isPresent()) {
            deposit(accountToNumber, amount);
            List<Account> accounts = new ArrayList<>();
            accounts.add(fromAccount.get());
            accounts.add(toAccount.get());
            return Optional.of(accounts);
        }
        return Optional.empty();
    }

}