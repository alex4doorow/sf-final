package com.sf.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sf.core.Defaults;
import com.sf.rest.dto.model.Amount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOperate {

    private Long id;
    private Long type;
    private Amount amount = new Amount();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Defaults.DATE_FORMAT_UTC)
    private LocalDateTime dateAdded;
}
