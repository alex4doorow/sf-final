package com.sf.services.converters.out;

import java.util.Collection;

public interface IOConverterOfList<IN, OUT> {
    Collection<OUT> convertTo(Collection<IN> inputList);
}
