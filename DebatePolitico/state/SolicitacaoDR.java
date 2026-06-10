package state;

import debate.ColaboradorPolitico;

public class SolicitacaoDR {

    private ColaboradorPolitico politico;
    private boolean concedido;
    private int ordem;

    public SolicitacaoDR(
        ColaboradorPolitico politico,
        int ordem
    ) {
        this.politico = politico;
        this.ordem = ordem;
        this.concedido = false;
    }

    public void setConcedido(
        boolean valor
    ) {
        concedido = valor;
    }

    public boolean getConcedido() {
        return concedido;
    }

    public ColaboradorPolitico getPolitico() {
        return politico;
    }

    public int getOrdem() {
        return ordem;
    }
}