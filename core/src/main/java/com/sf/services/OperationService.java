package com.sf.services;

import com.sf.bl.entity.TeBalance;
import com.sf.bl.entity.TeOperate;
import com.sf.bl.jpa.*;
import com.sf.error.CoreException;
import com.sf.types.ENOperateTypes;
import com.sf.types.ENOperationStatuses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class OperationService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private TeCustomerRepository customerRepository;
    @Autowired
    private TeBalanceRepository balanceRepository;
    @Autowired
    private TeOperateRepository operateRepository;
    @Autowired
    private TeOperateTypeRepository operateTypeRepository;
    @Autowired
    private TeCurrencyRepository currencyRepository;

    @Transactional
    public TeBalance getBalanceByCustomerId(Long customerId) throws CoreException {

        List<TeBalance> balances = balanceRepository.findBalancesByCustomer(customerId);
        if (balances == null || balances.isEmpty()) {
            throw new CoreException(CoreException.ERR_CUSTOMER_NF, String.valueOf(customerId));
        }
        return balances.stream().findFirst().get();
    }

    /**
     * add money
     * @param customerId
     * @param amount
     * @return
     * @throws CoreException
     */
    @Transactional
    public TeBalance takeMoney(Long customerId, BigDecimal amount) throws CoreException {

        if (customerRepository.findById(customerId).isEmpty()) {
            throw new CoreException(ENOperationStatuses.ERR_BALANCE_NF.getCode(), CoreException.ERR_CUSTOMER_NF, String.valueOf(customerId));
        }
        TeBalance balance = getBalanceByCustomerId(customerId);

        TeOperate operate = new TeOperate();
        operate.setDebitAcc(balance);
        operate.setCreditAcc(null);
        operate.setType(operateTypeRepository.getOperateTypeByCode(ENOperateTypes.SUBTR.name()));
        operate.setCurrency(dictionaryService.getDefaultCurrency());
        operate.setAmount(amount);
        operate.setDateAdded(LocalDateTime.now());
        operate.setDateModified(LocalDateTime.now());
        operateRepository.save(operate);

        if (amount.compareTo(balance.getAmount()) > 0) {
            throw new CoreException(ENOperationStatuses.ERR_BALANCE_NOT_ENOUGH_MONEY.getCode(), CoreException.ERR_BALANCE_NOT_ENOUGH_MONEY, String.valueOf(customerId));
        }
        BigDecimal newAmount = balance.getAmount().subtract(amount);
        balance.setAmount(newAmount);
        balanceRepository.save(balance);
        return balance;
    }

    /**
     * subtract money
     * @param customerId
     * @param amount
     * @return
     * @throws CoreException
     */
    @Transactional
    public TeBalance putMoney(Long customerId, BigDecimal amount) throws CoreException {
        if (customerRepository.findById(customerId).isEmpty()) {
            throw new CoreException(ENOperationStatuses.ERR_BALANCE_NF.getCode(), CoreException.ERR_CUSTOMER_NF, String.valueOf(customerId));
        }
        TeBalance balance = getBalanceByCustomerId(customerId);

        TeOperate operate = new TeOperate();
        operate.setDebitAcc(null);
        operate.setCreditAcc(balance);
        operate.setType(operateTypeRepository.getOperateTypeByCode(ENOperateTypes.ADD.name()));
        operate.setCurrency(dictionaryService.getDefaultCurrency());
        operate.setAmount(amount);
        operate.setDateAdded(LocalDateTime.now());
        operate.setDateModified(LocalDateTime.now());
        operateRepository.save(operate);

        BigDecimal newAmount = balance.getAmount().add(amount);
        balance.setAmount(newAmount);
        balanceRepository.save(balance);
        return balance;
    }

    @Transactional
    public void transferMoney(Long debitCustomerId, Long creditCustomerId, BigDecimal amount) throws CoreException {
        if (customerRepository.findById(debitCustomerId).isEmpty()) {
            throw new CoreException(ENOperationStatuses.ERR_BALANCE_NF.getCode(), CoreException.ERR_CUSTOMER_NF, String.valueOf(debitCustomerId));
        }
        TeBalance debitAcc = getBalanceByCustomerId(debitCustomerId);

        if (customerRepository.findById(creditCustomerId).isEmpty()) {
            throw new CoreException(ENOperationStatuses.ERR_BALANCE_NF.getCode(), CoreException.ERR_CUSTOMER_NF, String.valueOf(creditCustomerId));
        }
        TeBalance creditAcc = getBalanceByCustomerId(creditCustomerId);

        TeOperate operate = new TeOperate();
        operate.setDebitAcc(debitAcc);
        operate.setCreditAcc(creditAcc);
        operate.setType(operateTypeRepository.getOperateTypeByCode(ENOperateTypes.TRANSF.name()));
        operate.setCurrency(dictionaryService.getDefaultCurrency());
        operate.setAmount(amount);
        operate.setDateAdded(LocalDateTime.now());
        operate.setDateModified(LocalDateTime.now());
        operateRepository.save(operate);

        BigDecimal newDebitAmount = debitAcc.getAmount().add(amount);
        debitAcc.setAmount(newDebitAmount);
        balanceRepository.save(debitAcc);

        if (amount.compareTo(creditAcc.getAmount()) > 0) {
            throw new CoreException(ENOperationStatuses.ERR_BALANCE_NOT_ENOUGH_MONEY.getCode(), CoreException.ERR_BALANCE_NOT_ENOUGH_MONEY, String.valueOf(creditCustomerId));
        }
        BigDecimal newCreditAmount = creditAcc.getAmount().subtract(amount);
        creditAcc.setAmount(newCreditAmount);
        balanceRepository.save(creditAcc);
    }

    public List<TeOperate> getOperationList(Long customerId, LocalDateTime dateIn, LocalDateTime dateOut) {
        if (dateIn == null || dateOut == null) {
            return operateRepository.findOperatesByCustomer(customerId);
        }
        return operateRepository.findOperatesByCustomerAndPeriod(customerId, dateIn, dateOut);
    }
}
