package com.gestao_habitos_saudaveis.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestao_habitos_saudaveis.model.Usuario;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper()
            .findAndRegisterModules();

    public static void salvarUsuarios(List<Usuario> usuarios, String caminhoArquivo) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(caminhoArquivo), usuarios);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar usuários em JSON", e);
        }
    }

    public static List<Usuario> carregarUsuarios(String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);
            if (!arquivo.exists()) return new ArrayList<>();
            return mapper.readValue(arquivo, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar usuários de JSON", e);
        }
    }
}
