package com.whiteskylabs.quoteapi.util;

public class Constants {
    /**
     * Suppress default constructor for noninstantiability
     */
    private Constants() {
        throw new AssertionError();
    }

    public static final String QUOTE_ACTIVITY_COMPRESS = "compress";
    public static final String QUOTE_ACTIVITY_DECOMPRESS = "decompress";

    public static final String SUCCESSFUL_RESULT_TEXT = "Successful";
    public static final int SUCCESSFUL_RESULT_CODE = 200;
    public static final int FAILED_RESULT_CODE = 500;

}
