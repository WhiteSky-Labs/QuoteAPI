package com.whiteskylabs.quoteapi.model;

public class QuoteActivityResponse {
    private Quote quote;
    private EntityInfo[] quoteEntities;
    private ErrorInfo errorInfo;

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public EntityInfo[] getQuoteEntities() {
        return quoteEntities;
    }

    public void setQuoteEntities(EntityInfo[] quoteEntities) {
        this.quoteEntities = quoteEntities;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public static QuoteActivityResponse buildSuccessfulActivity(Quote quote, EntityInfo[] quoteEntities) {
        QuoteActivityResponse successResponse = new QuoteActivityResponse();
        successResponse.setQuote(quote);
        successResponse.setQuoteEntities(quoteEntities);
        successResponse.setErrorInfo(null);
        return successResponse;

    }

    public static QuoteActivityResponse buildFailedActivity(Quote quote, ErrorInfo errorInfo) {
        QuoteActivityResponse failedResponse = new QuoteActivityResponse();
        failedResponse.setQuote(quote);
        failedResponse.setErrorInfo(errorInfo);

        return failedResponse;
    }
    
    public EntityInfo getQuoteEntity(String name) {
        for (EntityInfo entityInfo : this.quoteEntities) {
            if (entityInfo.getName().equalsIgnoreCase(name)) {
                return entityInfo;
            }
        }

        return null;
    }
}
