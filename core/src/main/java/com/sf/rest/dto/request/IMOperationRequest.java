package com.sf.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sf.core.Defaults;
import com.sf.rest.dto.model.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IMOperationRequest extends IMBaseModel {
    private Long customerId;
    private Amount amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Defaults.DATE_yyyy_MM_dd)
    private LocalDate dateIn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Defaults.DATE_yyyy_MM_dd)
    private LocalDate dateOut;

}
