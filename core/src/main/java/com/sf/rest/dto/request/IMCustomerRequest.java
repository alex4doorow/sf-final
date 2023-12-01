package com.sf.rest.dto.request;

import com.sf.rest.dto.model.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IMCustomerRequest extends IMBaseModel {
    private Long customerId;
    private Amount amount;
}
