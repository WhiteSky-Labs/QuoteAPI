package com.whiteskylabs.quoteapi.service.impl;

import com.whiteskylabs.quoteapi.model.EntityInfo;
import com.whiteskylabs.quoteapi.model.ErrorInfo;
import com.whiteskylabs.quoteapi.model.Quote;
import com.whiteskylabs.quoteapi.model.QuoteActivityInfo;
import com.whiteskylabs.quoteapi.model.QuoteActivityResponse;
import com.whiteskylabs.quoteapi.service.QuoteService;
import com.whiteskylabs.quoteapi.util.Constants;
import com.whiteskylabs.quoteapi.util.DataTransformer;

public class QuoteServiceImpl implements QuoteService {

    public QuoteActivityResponse performQuoteActivity(QuoteActivityInfo src) {

        QuoteActivityInfo quoteActivityInfo = (QuoteActivityInfo) src;
        Quote quote = quoteActivityInfo.getQuote();

        if (quoteActivityInfo.getActivity().equalsIgnoreCase(Constants.QUOTE_ACTIVITY_COMPRESS)
                || quoteActivityInfo.getActivity().equalsIgnoreCase(Constants.QUOTE_ACTIVITY_DECOMPRESS)) {

            EntityInfo keyInfo = quoteActivityInfo.getActivityEntity("key");
            if (keyInfo == null) {
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setErrorCode(Constants.FAILED_RESULT_CODE);
                errorInfo.setErrorMessage("Unable to compress missing key entity.");
                return QuoteActivityResponse.buildFailedActivity(quote, errorInfo);
            }

            if (quoteActivityInfo.getActivity().equalsIgnoreCase(Constants.QUOTE_ACTIVITY_COMPRESS)) {
                return compressQuote(quote, keyInfo.getValue());
            }

            if (quoteActivityInfo.getActivity().equalsIgnoreCase(Constants.QUOTE_ACTIVITY_DECOMPRESS)) {
                EntityInfo keyIndexesInfo = quoteActivityInfo.getActivityEntity("keyIndexes");
                return decompressQuote(quote, keyInfo.getValue(),
                        DataTransformer.StringToIntArrayConverter(keyIndexesInfo.getValue()));
            }
        }

        return null;

    }

    private QuoteActivityResponse compressQuote(final Quote quote, final String key) {
        if (quote == null) {
            return null;
        }
        String strToCompress = quote.getQuoteText();

        StringBuilder indexArray = new StringBuilder();

        int index = strToCompress.indexOf(key);
        while (index >= 0) {
            indexArray.append(String.valueOf(index) + ",");
            index = strToCompress.indexOf(key, index + 1);
        }

        if (indexArray.length() > 0) {
            indexArray.deleteCharAt(indexArray.length() - 1);
        }

        Quote compressedQuote = new Quote();
        if (key.equalsIgnoreCase(" ")) {
            // if key is "space" we also want to remove other instance of
            // whitespaces
            compressedQuote.setQuoteText(strToCompress.replaceAll("\\s", ""));
        } else {
            compressedQuote.setQuoteText(strToCompress.replaceAll(key, ""));
        }
        compressedQuote.setAuthor(quote.getAuthor());

        EntityInfo[] quoteEntities = new EntityInfo[3];

        quoteEntities[0] = new EntityInfo();
        quoteEntities[0].setName("compressedFlag");
        quoteEntities[0].setValue("true");

        quoteEntities[1] = new EntityInfo();
        quoteEntities[1].setName("key");
        quoteEntities[1].setValue(key);

        quoteEntities[2] = new EntityInfo();
        quoteEntities[2].setName("keyIndexes");
        quoteEntities[2].setValue(indexArray.toString());

        return QuoteActivityResponse.buildSuccessfulActivity(compressedQuote, quoteEntities);

    }

    private QuoteActivityResponse decompressQuote(final Quote quote, final String key, final int[] keyIndexes) {
        if (quote == null) {
            return null;
        }

        StringBuilder uncompressedQuotetext = new StringBuilder();
        uncompressedQuotetext.append(quote.getQuoteText());
        for (int index : keyIndexes) {
            uncompressedQuotetext.insert(index, key);
        }
        Quote decompressedQuote = new Quote();
        decompressedQuote.setAuthor(quote.getAuthor());
        decompressedQuote.setQuoteText(uncompressedQuotetext.toString());

        EntityInfo[] quoteEntities = new EntityInfo[1];

        quoteEntities[0] = new EntityInfo();
        quoteEntities[0].setName("compressedFlag");
        quoteEntities[0].setValue("false");

        return QuoteActivityResponse.buildSuccessfulActivity(decompressedQuote, quoteEntities);
    }

}
