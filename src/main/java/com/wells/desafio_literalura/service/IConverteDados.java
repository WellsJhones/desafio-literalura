package com.wells.desafio_literalura.service;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> tclasse);

}
