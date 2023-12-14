package com.sf.rest.dto;

import com.sf.rest.dto.model.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoBalance {
    private Long id;
    private Long customerId;
    private Amount amount = new Amount();
    private DtoResult result;

    public DtoBalance(Long id, Long customerId, Amount amount) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        result = new DtoResult();
    }
}
