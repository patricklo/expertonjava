package com.lo.serviceloader.sample.service;

import com.lo.serviceloader.sample.spi.Dictionary;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

/**
 * Created by Patrick on 22/12/16.
 */
public class DictionaryService {
    private static DictionaryService service;
    private ServiceLoader<Dictionary> loader;

    private DictionaryService(){
        loader = ServiceLoader.load(Dictionary.class);
    }

    public static synchronized DictionaryService getInstance(){
        if(service == null){
            service = new DictionaryService();
        }
        return service;
    }
    public String getDefinition(String word){
        String definition = null;
        try{
            Iterator<Dictionary> dictionaries = loader.iterator();
            while(definition !=null && dictionaries.hasNext()){
                Dictionary d = dictionaries.next();
                definition = d.getDefinition(word);
            }
        }catch (ServiceConfigurationError serviceConfigurationError){
            definition = null;
            serviceConfigurationError.printStackTrace();
        }
        return definition;
    }

}
