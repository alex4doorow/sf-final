package com.sf.rest;

import com.sf.bl.entity.TeBalance;
import com.sf.bl.entity.TeOperate;
import com.sf.error.CoreException;
import com.sf.rest.dto.DtoBalance;
import com.sf.rest.dto.DtoOperate;
import com.sf.rest.dto.DtoOperates;
import com.sf.rest.dto.DtoResult;
import com.sf.rest.dto.model.Amount;
import com.sf.rest.dto.request.IMCustomerRequest;
import com.sf.rest.dto.request.IMOperationRequest;
import com.sf.services.OperationService;
import com.sf.services.converters.JsonMapper;
import com.sf.services.converters.out.OutDtoBalancesConverter;
import com.sf.services.converters.out.OutDtoOperatesConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sf.core.Defaults;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/operations")
@Slf4j
public class OperationController extends BaseRestController {

    @Autowired
    JsonMapper jsonMapper;
    @Autowired
    OperationService operationService;
    @Autowired
    OutDtoBalancesConverter outDtoBalancesConverter;
    @Autowired
    OutDtoOperatesConverter outDtoOperatesConverter;

    @GetMapping("/list")
    public ResponseEntity<Object> getOperations(@RequestHeader (value = Defaults.header_X_Request_ID) String requestId,
                                                @RequestHeader Map<String, String> headers,
                                                @RequestBody String body) throws CoreException {

        log.info("[START] {} request: {}", "getOperationList", body);

        IMOperationRequest operationRequest = jsonMapper.fromJSON(headers, body, IMOperationRequest.class);
        operationRequest.setRequestId(requestId);

        DtoOperates dtoOperations;
        try {
            List<TeOperate> operations = operationService.getOperationList(operationRequest.getCustomerId(),
                    operationRequest.getDateIn().atStartOfDay(), operationRequest.getDateOut().atStartOfDay());
            Collection<DtoOperate> dtoOperationList = outDtoOperatesConverter.convertTo(operations);
            dtoOperations = new DtoOperates(operationRequest.getCustomerId(), dtoOperationList);
            dtoOperations.setResult(DtoResult.success());
            return response("getOperationList", dtoOperations, false);
        } catch (Exception e) {
            DtoResult error = DtoResult.error(-1, "Error", e.getMessage());
            dtoOperations = new DtoOperates();
            dtoOperations.setCustomerId(operationRequest.getCustomerId());
            dtoOperations.setResult(error);
            return errorResponse("getOperationList", dtoOperations, e);
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<Object> getBalance(@RequestHeader (value = Defaults.header_X_Request_ID) String requestId,
                                             @RequestHeader Map<String, String> headers,
                                             @RequestBody String body) throws CoreException {
        log.info("[START] {} request: {}", "getBalance", body);

        IMCustomerRequest customerRequest = jsonMapper.fromJSON(headers, body, IMCustomerRequest.class);
        customerRequest.setRequestId(requestId);

        DtoBalance dtoBalance;
        try {
            TeBalance balance = operationService.getBalanceByCustomerId(customerRequest.getCustomerId());
            dtoBalance = outDtoBalancesConverter.convertTo(balance);
            return response("getBalance", dtoBalance, false);
        } catch (Exception e) {
            DtoResult error = DtoResult.error(-1, "Error", e.getMessage());
            dtoBalance = new DtoBalance();
            dtoBalance.setCustomerId(customerRequest.getCustomerId());
            dtoBalance.setResult(error);
            return errorResponse("getBalance", dtoBalance, e);
        }
    }

    @PostMapping("/take-money")
    public ResponseEntity<Object> takeMoney(@RequestHeader (value = Defaults.header_X_Request_ID) String requestId,
                                                @RequestHeader Map<String, String> headers,
                                                @RequestBody String body) throws CoreException {
        log.info("[START] {} request:\n{}", "take-money", body);
        IMCustomerRequest customerRequest = jsonMapper.fromJSON(headers, body, IMCustomerRequest.class);

        DtoBalance dtoBalance;
        try {
            TeBalance balance = operationService.takeMoney(customerRequest.getCustomerId(), customerRequest.getAmount().getValue());
            dtoBalance = outDtoBalancesConverter.convertTo(balance);
            dtoBalance.setResult(DtoResult.success(1));
            return response("takeMoney", dtoBalance, false);
        } catch (CoreException e) {

            DtoResult error = DtoResult.error(e.getCode(), e.getRespCode(), e.getMessage());
            dtoBalance = new DtoBalance();
            dtoBalance.setCustomerId(customerRequest.getCustomerId());

            TeBalance oldBalance = operationService.getBalanceByCustomerId(customerRequest.getCustomerId());
            dtoBalance.setAmount(new Amount(oldBalance.getAmount(), oldBalance.getCurrency().getCode()));

            dtoBalance.setResult(error);
            return errorResponse("takeMoney", dtoBalance, e);
        }
    }

    @PostMapping("/put-money")
    public ResponseEntity<Object> putMoney(@RequestHeader (value = Defaults.header_X_Request_ID) String requestId,
                                               @RequestHeader Map<String, String> headers,
                                               @RequestBody String body) throws CoreException {
        log.info("[START] {} request:\n{}", "put-money", body);
        IMCustomerRequest customerRequest = jsonMapper.fromJSON(headers, body, IMCustomerRequest.class);

        DtoBalance dtoBalance;
        try {
            TeBalance balance = operationService.putMoney(customerRequest.getCustomerId(), customerRequest.getAmount().getValue());
            dtoBalance = outDtoBalancesConverter.convertTo(balance);
            dtoBalance.setResult(DtoResult.success(1));
            return response("putMoney", dtoBalance, false);
        } catch (CoreException e) {

            DtoResult error = DtoResult.error(e.getCode(), e.getRespCode(), e.getMessage());
            dtoBalance = new DtoBalance();
            dtoBalance.setCustomerId(customerRequest.getCustomerId());

            TeBalance oldBalance = operationService.getBalanceByCustomerId(customerRequest.getCustomerId());
            dtoBalance.setAmount(new Amount(oldBalance.getAmount(), oldBalance.getCurrency().getCode()));

            dtoBalance.setResult(error);
            return errorResponse("putMoney", dtoBalance, e);
        }
    }

    @PostMapping("/transfer-money")
    public ResponseEntity<DtoBalance> transferMoney(@RequestHeader (value = Defaults.header_X_Request_ID) String requestId,
                                                    @RequestHeader Map<String, String> headers,
                                                    @RequestBody String body) throws CoreException {
        log.info("[START] {} request:\n{}", "transfer-money", body);
        IMCustomerRequest customerRequest = jsonMapper.fromJSON(headers, body, IMCustomerRequest.class);

        return null;
    }

    private ResponseEntity<Object> response(String msgInType, Object response, boolean isError) {
        log.info("[END] {} response:\n{}", msgInType, response);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(response, httpHeaders, (isError) ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }
}
