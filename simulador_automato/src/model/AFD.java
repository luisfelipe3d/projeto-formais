package model;

public class AFD {
    // AFD referencia cada estado de um autômato
    private final boolean estadoInicial;
    private final boolean estadoFinal;
    private final FuncaoTransicao funcaoTransicao;

    public AFD(boolean estadoInicial, boolean estadoFinal, FuncaoTransicao funcaoTransicao) {
        this.estadoInicial = estadoInicial;
        this.estadoFinal = estadoFinal;
        this.funcaoTransicao = funcaoTransicao;
    }

    public boolean isEstadoInicial() {
        return estadoInicial;
    }

    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public FuncaoTransicao getFuncaoTransicao() {
        return funcaoTransicao;
    }
    
}
