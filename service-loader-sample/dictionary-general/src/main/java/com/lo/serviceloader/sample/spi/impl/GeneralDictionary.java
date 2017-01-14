package com.lo.serviceloader.sample.spi.impl;

import com.lo.serviceloader.sample.spi.Dictionary;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Patrick on 22/12/16.
 */
public class GeneralDictionary implements Dictionary {

    private SortedMap<String, String> map;

    public GeneralDictionary() {
        map = new TreeMap<String, String>();
        map.put(
                "book",
                "a set of written or printed pages, usually bound with " +
                        "a protective cover");
        map.put(
                "editor",
                "a person who edits");
    }

    public String getDefinition(String word){
        return map.get(word);
    }
}
