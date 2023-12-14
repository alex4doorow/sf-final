package com.sf.services;

import com.sf.bl.entity.TeCurrency;
import com.sf.bl.jpa.TeCurrencyRepository;
import com.sf.core.Defaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DictionaryService {

    @Autowired
    private TeCurrencyRepository currencyRepository;

    public TeCurrency getDefaultCurrency() {
        return currencyRepository.getCurrencyByCode(Defaults.CURRENCY_CODE_DEFAULT);
    }
}
