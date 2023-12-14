package com.sf.services.converters.out;

import com.sf.bl.entity.TeBalance;
import com.sf.rest.dto.DtoBalance;
import com.sf.rest.dto.DtoResult;
import com.sf.rest.dto.model.Amount;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class OutDtoBalancesConverter implements IOConverter<TeBalance, DtoBalance>, IOConverterOfList<TeBalance, DtoBalance> {

    @Override
    public DtoBalance convertTo(TeBalance input) {
        return new DtoBalance(input.getId(),
                input.getCustomer().getId(),
                new Amount(input.getAmount(), input.getCurrency().getCode()),
                DtoResult.empty());
    }

    @Override
    public Collection<DtoBalance> convertTo(Collection<TeBalance> inputList) {
        return inputList
                .stream()
                .map(this::convertTo)
                .toList();
    }
}
