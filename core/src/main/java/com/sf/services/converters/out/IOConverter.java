package com.sf.services.converters.out;

public interface IOConverter<IN, OUT> {
    OUT convertTo(IN input);
}
