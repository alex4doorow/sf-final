package com.sf.rest;

import com.sf.rest.dto.DtoBalance;
import com.sf.rest.dto.DtoError;
import com.sf.services.converters.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@Slf4j
public class OperationController extends BaseRestController {

    @Autowired
    JsonMapper jsonMapper;

    @GetMapping("balance/{userId}")
    public ResponseEntity<Object> getBalance(@PathVariable Long userId) {
        log.info("[START] {} request: {}", "FIND", userId);

        try {
            DtoBalance dtoBalance = new DtoBalance();
            dtoBalance.setId(111L);
            dtoBalance.setUserId(userId);
            dtoBalance.setMoney(new BigDecimal(100));
            dtoBalance.setCurrencyCode("USD");

            return response("getBalance", dtoBalance, false);
        } catch (Exception e) {
            DtoError error = new DtoError();
            error.setCode(-1);
            error.setInfo(e.getMessage());
            return errorResponse("getBalance", e);
        }
    }

/*
    Операции API
    getBalance
    Принимает параметры	Результат
    Значение	Текстовое поле
    ID пользователя	Ошибка при выполнении операции (-1)
    Текущий баланс пользователя

    Причина ошибки (если она была)
    takeMoney
    Принимает параметры	Результат
    Значение	Текстовое поле
    ID пользователя

    Сумма

    Недостаточно средств (0)

    Успех (1)

    Причина ошибки (если она была)
    putMoney
    Принимает параметры	Результат
    Значение	Текстовое поле
    ID пользователя

    Сумма

    Ошибка при выполнении операции (0)

    Успех (1)

    Причина ошибки (если она была)
    getOperationList
    Принимает параметры	Результат
    ID пользователя

    Начало диапазона дат (параметр может быть пустым)

    Конец диапазона дат (параметр может быть пустым)

    Список из строк со следующими полями:

    Дата операции
    Тип операции (снятие со счета, пополнение счета, перевод другому клиенту, перевод от другого клиента вам)
    Сумма операции
    transferMoney
*/


    private ResponseEntity<Object> response(String msgInType, DtoBalance response, boolean isError) {
        log.info("[END] {} response:\n{}", msgInType, response);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(response, httpHeaders, (isError) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
