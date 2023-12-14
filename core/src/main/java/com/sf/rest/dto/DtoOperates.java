package com.sf.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoOperates {

    private Long customerId;
    private Collection<DtoOperate> operations = Collections.emptyList();
    private DtoResult result;

    public DtoOperates(Long customerId, Collection<DtoOperate> operations) {
        this.customerId = customerId;
        this.operations = operations;
        result = new DtoResult();
    }
}
