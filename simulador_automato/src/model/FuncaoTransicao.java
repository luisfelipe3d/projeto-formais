package model;

public class FuncaoTransicao {
    // FuncaoTransicao referencia cada função de transição de um estado
    private final int transicao_0;
    private final int transicao_1;

    public FuncaoTransicao(int transicao_0, int transicao_1) {
        this.transicao_0 = transicao_0;
        this.transicao_1 = transicao_1;
    }

    public int getTransicao_0() {
        return transicao_0;
    }

    public int getTransicao_1() {
        return transicao_1;
    }
        
}
