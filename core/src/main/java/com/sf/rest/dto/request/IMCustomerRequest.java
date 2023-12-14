package com.sf.rest.dto.request;

import com.sf.rest.dto.model.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IMCustomerRequest extends IMBaseModel {
    private Long customerId;
    private Long debitCustomerId;
    private Long creditCustomerId;
    private Amount amount;
    private LocalDateTime dateIn;
    private LocalDateTime dateOut;
}
