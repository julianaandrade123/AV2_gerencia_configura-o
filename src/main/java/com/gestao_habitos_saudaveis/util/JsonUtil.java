package com.gestao_habitos_saudaveis.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestao_habitos_saudaveis.model.Usuario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File arquivoJson = new File("usuarios.json");

    public static List<Usuario> carregarUsuarios() {
        try {
            if (!arquivoJson.exists() || arquivoJson.length() == 0) {
                return new ArrayList<>();
            }
            return mapper.readValue(arquivoJson, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void salvarUsuarios(List<Usuario> usuarios) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(arquivoJson, usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}