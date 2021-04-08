package model;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class AFD {
    // AFD referencia cada estado de um autômato
    private final boolean estadoInicial;
    private final boolean estadoFinal;
    private final Map funcaoTransicao;

    public AFD(boolean estadoInicial, boolean estadoFinal) {
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.funcaoTransicao = new HashMap();
    }

    public boolean isEstadoInicial() {
        return estadoInicial;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public Map getFuncaoTransicao() {
        return Collections.unmodifiableMap(funcaoTransicao);
    }
    
}
