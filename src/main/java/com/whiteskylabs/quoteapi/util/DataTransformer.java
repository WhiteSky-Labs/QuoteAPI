package com.whiteskylabs.quoteapi.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DataTransformer {

    public static int[] StringToIntArrayConverter(String src) {
        int i = 0;
        ArrayList<Integer> intArray = new ArrayList<Integer>();
        ArrayList<String> strArray = new ArrayList<String>();

        StringTokenizer tokenizer = new StringTokenizer(src, "[,]"); // "[,]" is the delimeter

        while (tokenizer.hasMoreTokens()) {
            strArray.add(i, tokenizer.nextToken());
            intArray.add(i, Integer.parseInt(strArray.get(i))); // Converting into Integer
            i++;
        }
        
        int[] response = new int[intArray.size()];
        int counter = 0;
        for (int item : intArray) {
            response[counter] = item;
            counter++;
        }
        return response;
    }
}
