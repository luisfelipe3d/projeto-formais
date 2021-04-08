package controller;

import model.AFD;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AFDControlador {
    //repositorioAFD é uma lista com todos os estados de um autômato
    List <AFD> repositorioAFD = new ArrayList();
    private Map<String, String> funcaoTransicao = new HashMap();
    
    public boolean validarCadeia(String cadeia) {
//        exemplo de como cada estado pode ser inserido no repositório:
//        repositorioAFD.add(new AFD("A", true, false));
//        repositorioAFD.get(0).addFuncaoTransicao("0", "A");
//        repositorioAFD.get(0).addFuncaoTransicao("1", "B");
//        repositorioAFD.add(new AFD("B", false, false));
//        repositorioAFD.get(1).addFuncaoTransicao("0", "B");
//        repositorioAFD.get(1).addFuncaoTransicao("1", "C");
//        repositorioAFD.add(new AFD("C", false, false));
//        repositorioAFD.get(2).addFuncaoTransicao("0", "C");
//        repositorioAFD.get(2).addFuncaoTransicao("1", "D");
//        repositorioAFD.add(new AFD("D", false, true));
//        repositorioAFD.get(3).addFuncaoTransicao("0", "D");
//        repositorioAFD.get(3).addFuncaoTransicao("1", "D");
        
        // estadoAtual é inicializado com o estado inicial
        AFD estadoAtual = repositorioAFD.get(0);
        funcaoTransicao = estadoAtual.getFuncaoTransicao();
        
        // percorre a cadeia de caracteres
        for(int i = 0; i < cadeia.length(); i++) {
            String nomeProximoEstado = (String) funcaoTransicao.get(Character.toString(cadeia.charAt(i)));
            
            // percorre o ArrayList de estados
            for(AFD estado: repositorioAFD) {
                if(estado.getNomeEstado().equalsIgnoreCase(nomeProximoEstado)) {
                    estadoAtual = estado;
                    funcaoTransicao = estadoAtual.getFuncaoTransicao();
                    break;
                }
            }
            
            // if só é acessado na leitura no último caractere da cadeia
            if(i == cadeia.length() - 1) {
                return estadoAtual.isEstadoFinal();
            }
        }        
        return false;
    }
}
