package com.sf.rest.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoBalance {
    private Long id;
    private Long userId;
    private BigDecimal money;
    private String currencyCode;

    private DtoError error;
}
