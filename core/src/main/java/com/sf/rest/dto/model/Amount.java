package com.sf.rest.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amount {
    @NonNull
    private BigDecimal value;
    @NonNull
    private String currency;
}
