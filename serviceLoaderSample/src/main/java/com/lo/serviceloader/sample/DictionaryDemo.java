package com.lo.serviceloader.sample;

import com.lo.serviceloader.sample.service.DictionaryService;

/**
 * Created by Patrick on 22/12/16.
 */
public class DictionaryDemo {

    public static void main(String[] args){
        DictionaryService dictionary = DictionaryService.getInstance();
        System.out.println(DictionaryDemo.lookup(dictionary, "book"));
        System.out.println(DictionaryDemo.lookup(dictionary, "editor"));
        System.out.println(DictionaryDemo.lookup(dictionary, "xml"));
        System.out.println(DictionaryDemo.lookup(dictionary, "REST"));
    }

    public static String lookup(DictionaryService dictionary, String word) {
        String outputString = word + ": ";
        String definition = dictionary.getDefinition(word);
        if (definition == null) {
            return outputString + "Cannot find definition for this word.";
        } else {
            return outputString + definition;
        }
    }
    }

