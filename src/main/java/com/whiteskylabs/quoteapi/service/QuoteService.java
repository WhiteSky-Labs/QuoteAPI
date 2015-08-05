package com.whiteskylabs.quoteapi.service;

import com.whiteskylabs.quoteapi.model.QuoteActivityInfo;
import com.whiteskylabs.quoteapi.model.QuoteActivityResponse;

public interface QuoteService {

    public QuoteActivityResponse performQuoteActivity(QuoteActivityInfo src);
}
