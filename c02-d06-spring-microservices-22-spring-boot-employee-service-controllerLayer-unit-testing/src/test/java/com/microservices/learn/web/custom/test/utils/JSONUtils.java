package com.microservices.learn.web.custom.test.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


/*

To test this, in src/test/java, we will create a utility class: JSONUtils.java
Because, whenever we make a request to any handler method which implements a microservice, it returns a result in XML or JSON format.
It should be converted into Java Object. Or the Java Object should be converted into XML or JSON.
To do so, we are creating two methods: convertFromJsonToObject() & convertFromObjectToJSON(). These are same as convertBeanToEntity and convertEntityToBean.
convertFromJsonToObject() method will readValue in JSON and will convert it into var using ObjectMapper class. What is var? Var is the object of the Class of type <T>.
convertFromObjectToJSON() will convert a given object into a JSON String.

 */

public class JSONUtils {

    //Generic type safe method
    static public <T> T convertFromJsonToObject(String json, Class<T> var) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, var);
    }

    public static String convertFromObjectToJson(Object obj) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
