package main.controller;

import main.model.EF;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.InputStream;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.stream.Collectors;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AFDControlador {
    //alfabetoLinguagem armazena a linguagem do autômato lido
    private String alfabetoLinguagem;

    //AFD é uma lista com todos os estados de um autômato
    private final List<EF> AFD = new ArrayList();

    //funcaoTransicaoEstadoAtual mapeia as funções de transição de um determinado estado
    private Map<String, String> funcaoTransicaoEstadoAtual = new HashMap();

    public void lerArquivoJSON() {
        //acessa e lê o arquivo a partir do resources
        InputStream inputStream = getClass().getResourceAsStream("/automato2.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String arquivoJSON = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        
        JSONParser parser = new JSONParser();
        
        try {
            JSONObject objetoJSON = (JSONObject) parser.parse(arquivoJSON);
            criarAutomato(objetoJSON);
        } catch (ParseException ex) {
            System.err.println("org.json.ParseException: " + ex.getMessage());
        }
    }

    private void criarAutomato(JSONObject arquivoJSON) {
        //converte um array do JSONObect em JSONArray
        JSONArray arrayEstados = (JSONArray) arquivoJSON.get("Estados");
        JSONArray arrayAlfabeto = (JSONArray) arquivoJSON.get("Alfabeto");
        JSONArray arrayTransicoes = (JSONArray) arquivoJSON.get("Transicoes");
        JSONArray arrayEstadosFinais = (JSONArray) arquivoJSON.get("EstadosFinais");
        this.alfabetoLinguagem = arrayAlfabeto.toJSONString();
        int qtdFuncoesTransicao = arrayAlfabeto.size();

        //contador para as funções de transição
        int n = 0;

        boolean estadoInicial;
        boolean estadoFinal;

        for (int i = 0; i < arrayEstados.size(); i++) {
            //valida se o estado atual é inicial
            estadoInicial = arquivoJSON.get("EstadoInicial").equals(arrayEstados.get(i));

            //valida se o estado atual é final
            estadoFinal = arrayEstadosFinais.contains(arrayEstados.get(i));

            AFD.add(new EF((String) arrayEstados.get(i), estadoInicial, estadoFinal));
            for (int j = 0; j < qtdFuncoesTransicao; j++) {
                //regex retira " [ ] da String
                String funcaoTransicao = arrayTransicoes.get(n).toString().replaceAll("[\"\\[\\]]", "");

                String[] arrayFuncaoTransicao = funcaoTransicao.split(",");
                AFD.get(i).addFuncaoTransicao(arrayFuncaoTransicao[1], arrayFuncaoTransicao[2]);
                n++;
            }
        }
    }

    public boolean validarCadeia(String cadeia) {
        EF estadoAtual = AFD.get(0);
        funcaoTransicaoEstadoAtual = estadoAtual.getFuncaoTransicao();

        // percorre a cadeia de elementos
        for (int i = 0; i < cadeia.length(); i++) {
            String nomeProximoEstado = (String) funcaoTransicaoEstadoAtual.get(Character.toString(cadeia.charAt(i)));

            // percorre o ArrayList de estados
            for (EF estado : AFD) {
                if (estado.getNomeEstado().equalsIgnoreCase(nomeProximoEstado)) {
                    estadoAtual = estado;
                    funcaoTransicaoEstadoAtual = estadoAtual.getFuncaoTransicao();
                    break;
                }
            }

            // if só é acessado na leitura no último elemento da cadeia
            if (i == cadeia.length() - 1) {
                return estadoAtual.isEstadoFinal();
            }
        }
        return false;
    }

    public List<EF> getAFD() {
        return Collections.unmodifiableList(AFD);
    }

    public String getAlfabetoLinguagem() {
        return this.alfabetoLinguagem;
    }
}
