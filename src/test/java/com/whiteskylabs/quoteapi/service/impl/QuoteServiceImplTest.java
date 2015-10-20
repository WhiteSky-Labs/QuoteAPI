package com.whiteskylabs.quoteapi.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.whiteskylabs.quoteapi.model.EntityInfo;
import com.whiteskylabs.quoteapi.model.Quote;
import com.whiteskylabs.quoteapi.model.QuoteActivityInfo;
import com.whiteskylabs.quoteapi.model.QuoteActivityResponse;

public class QuoteServiceImplTest {

    @Test
    public void validPerformActivityCompress() throws Exception {

        QuoteActivityInfo quoteActivityInfo = new QuoteActivityInfo();

        Quote quote = new Quote();
        quote.setAuthor("Author");
        quote.setQuoteText("sample quote quote pakit quote");
        quoteActivityInfo.setActivity("compress");
        quoteActivityInfo.setQuote(quote);

        EntityInfo[] activityEntities = new EntityInfo[1];

        EntityInfo keyInfo = new EntityInfo();
        keyInfo.setName("key");
        keyInfo.setValue(" ");
        activityEntities[0] = keyInfo;

        quoteActivityInfo.setActivityEntities(activityEntities);

        QuoteServiceImpl impl = new QuoteServiceImpl();
        assertEquals("6,12,18,24", impl.performQuoteActivity(quoteActivityInfo).getQuoteEntity("keyIndexes")
                .getValue());
        assertEquals("samplequotequotepakitquote", impl.performQuoteActivity(quoteActivityInfo).getQuote()
                .getQuoteText());

    }

    @Test
    public void validPerformActivityCompressNoIndexes() throws Exception {

        QuoteActivityInfo quoteActivityInfo = new QuoteActivityInfo();

        Quote quote = new Quote();
        quote.setAuthor("Author");
        quote.setQuoteText("sample");
        quoteActivityInfo.setActivity("compress");
        quoteActivityInfo.setQuote(quote);

        EntityInfo[] activityEntities = new EntityInfo[1];

        EntityInfo keyInfo = new EntityInfo();
        keyInfo.setName("key");
        keyInfo.setValue(" ");
        activityEntities[0] = keyInfo;

        quoteActivityInfo.setActivityEntities(activityEntities);

        QuoteServiceImpl impl = new QuoteServiceImpl();
        assertEquals("", impl.performQuoteActivity(quoteActivityInfo).getQuoteEntity("keyIndexes").getValue());
        assertEquals("sample", impl.performQuoteActivity(quoteActivityInfo).getQuote().getQuoteText());

    }
    
    @Test
    public void sampleTest() throws Exception {
        assertTrue(false);
    }

    @Test
    public void validPerformActivityDecompress() throws Exception {

        QuoteActivityInfo quoteActivityInfo = new QuoteActivityInfo();

        Quote quote = new Quote();
        quote.setAuthor("Author");
        quote.setQuoteText("samplequotequotepakitquote");
        quoteActivityInfo.setActivity("decompress");
        quoteActivityInfo.setQuote(quote);

        EntityInfo[] activityEntities = new EntityInfo[2];

        EntityInfo keyInfo = new EntityInfo();
        keyInfo.setName("key");
        keyInfo.setValue(" ");
        activityEntities[0] = keyInfo;
        
        EntityInfo keyIndexesInfo = new EntityInfo();
        keyIndexesInfo.setName("keyIndexes");
        keyIndexesInfo.setValue("[6,12,18,24]");
        activityEntities[1] = keyIndexesInfo;

        quoteActivityInfo.setActivityEntities(activityEntities);

        QuoteServiceImpl impl = new QuoteServiceImpl();
        QuoteActivityResponse response = impl.performQuoteActivity(quoteActivityInfo);
        System.out.println(response.getQuote()
                .getQuoteText());
        assertNull(response.getQuoteEntity("keyIndexes"));
        assertEquals("sample quote quote pakit quote", response.getQuote()
                .getQuoteText());

    }
}
