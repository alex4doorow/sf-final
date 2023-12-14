package com.sf.rest.dto;

import com.sf.rest.dto.model.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTransferBalances {
    DtoBalance debitAcc;
    DtoBalance creditAcc;

    private DtoResult result;

    public DtoTransferBalances(DtoBalance debitAcc, DtoBalance creditAcc) {
        this.debitAcc = debitAcc;
        this.creditAcc = creditAcc;
        result = new DtoResult();
    }

}
