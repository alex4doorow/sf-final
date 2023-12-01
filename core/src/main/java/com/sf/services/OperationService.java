package com.sf.services;

import com.sf.bl.entity.TeBalance;
import com.sf.bl.jpa.TeBalanceRepository;
import com.sf.error.CoreException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class OperationService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TeBalanceRepository balanceRepository;

    @Transactional
    public TeBalance getBalanceByCustomerId(Long customerId) throws CoreException {

        List<TeBalance> balances = balanceRepository.findBalancesByCustomer(customerId);
        if (balances == null || balances.isEmpty()) {
            throw new CoreException(CoreException.ERR_CUSTOMER_NF, String.valueOf(customerId));
        }
        return balances.get(0);
    }

    @Transactional
    public TeBalance takeMoney(Long customerId, BigDecimal amount) throws CoreException {
        TeBalance balance = getBalanceByCustomerId(customerId);
        if (amount.compareTo(balance.getAmount()) > 0) {
            throw new CoreException(0, CoreException.ERR_BALANCE_NOT_ENOUGH_MONEY, String.valueOf(customerId));
        }
        BigDecimal newAmount = balance.getAmount().subtract(amount);
        balance.setAmount(newAmount);
        balanceRepository.save(balance);
        return balance;
    }

    @Transactional
    public TeBalance putMoney(Long customerId, BigDecimal amount) throws CoreException {
        TeBalance balance = getBalanceByCustomerId(customerId);
        BigDecimal newAmount = balance.getAmount().add(amount);
        balance.setAmount(newAmount);
        balanceRepository.save(balance);
        return balance;
    }
}
