package com.welcome.aemworldreact.core.service;

import com.fasterxml.jackson.databind.util.JSONPObject;

public interface JSONConverter {

    @SuppressWarnings("rawtypes")
    Object convertToObject(JSONPObject jsonpObject, Class clazz);

    @SuppressWarnings("rawtypes")
    Object convertToObject(String jsonString, Class clazz);

    String convertToJsonString(Object object);

    JSONPObject convertToJSONPObject(Object object);
}
