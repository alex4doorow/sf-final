package com.sf.services.converters.out;

import com.sf.bl.entity.TeOperate;
import com.sf.rest.dto.DtoOperate;
import com.sf.rest.dto.model.Amount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OutDtoOperatesConverter implements IOConverter<TeOperate, DtoOperate>, IOConverterOfList<TeOperate, DtoOperate> {


    @Override
    public DtoOperate convertTo(TeOperate input) {
        return new DtoOperate(input.getId(),
                input.getType().getId(),
                new Amount(input.getAmount(), input.getCurrency().getCode()),
                input.getDateAdded());
    }

    @Override
    public Collection<DtoOperate> convertTo(Collection<TeOperate> inputList) {
        List<DtoOperate> result = new ArrayList<>();
        for (TeOperate teOperate : inputList) {
            result.add(convertTo(teOperate));
        }
        return result;
    }
}
