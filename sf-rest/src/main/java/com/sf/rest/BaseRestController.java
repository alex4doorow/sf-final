package com.sf.rest;

import com.sf.rest.dto.DtoResult;
import com.sf.services.converters.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class BaseRestController {

    protected ResponseEntity<Object> errorResponse(String msgInType, Object result, Exception exception) {
        log.error(msgInType, exception);
        return JsonMapper.errorResponse(null, result, exception);
    }
}
