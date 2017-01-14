package com.lo.serviceloader.sample.spi.impl;


import com.lo.serviceloader.sample.spi.Dictionary;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Patrick on 22/12/16.
 */
public class ExtendedDictionary implements Dictionary {

    private SortedMap<String, String> map;

    public ExtendedDictionary() {
        map = new TreeMap<String, String>();
        map.put(
                "xml",
                "a document standard often used in web services, among other " +
                        "things");
        map.put(
                "REST",
                "an architecture style for creating, reading, updating, " +
                        "and deleting data that attempts to use the common " +
                        "vocabulary of the HTTP protocol; Representational State " +
                        "Transfer");
    }

    public String getDefinition(String word) {
        return map.get(word);
    }
}
