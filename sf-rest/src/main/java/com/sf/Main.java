package com.sf;

import com.sf.bl.entity.TeBalance;
import com.sf.error.CoreException;
import com.sf.services.OperationService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;

@Slf4j
public class Main {


    public static void main(String[] args) throws CoreException {

        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            log.error("Could not setup logger configuration: " + e.toString());
        }

        OperationService operationService = new OperationService();
        TeBalance balance = operationService.getBalanceByCustomerId(12L);
        log.info("{}", balance.getAmount());

        balance = operationService.takeMoney(12L, new BigDecimal(10));
        log.info("{}", balance.getAmount());

        balance = operationService.putMoney(12L, new BigDecimal(1000));
        log.info("{}", balance.getAmount());
    }
}
