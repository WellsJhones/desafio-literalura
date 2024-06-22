package com.wells.desafio_literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> tclasse) {
        try {
            return mapper.readValue(json, tclasse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
