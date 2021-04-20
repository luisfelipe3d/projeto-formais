package model;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class AFD {
    // AFD referencia cada estado de um autômato
    private final String nomeEstado;
    private final boolean estadoInicial;
    private final boolean estadoFinal;
    private final Map<String, String> funcaoTransicao;

    public AFD(String nomeEstado, boolean estadoInicial, boolean estadoFinal) {
        this.nomeEstado = nomeEstado;
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.funcaoTransicao = new HashMap();
    }
    
    public String getNomeEstado() {
        return nomeEstado;
    }

    public boolean isEstadoInicial() {
        return estadoInicial;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }
    
    public void addFuncaoTransicao(String caractere, String proximoEstado) {
        funcaoTransicao.put(caractere, proximoEstado);
    }
    
    public Map getFuncaoTransicao() {
        return Collections.unmodifiableMap(funcaoTransicao);
    }

    @Override
    public String toString() {
        return "AFD{" + "nomeEstado=" + nomeEstado + ", estadoInicial=" + estadoInicial + ", estadoFinal=" + estadoFinal + ", funcaoTransicao=" + funcaoTransicao + '}';
    }    
}
