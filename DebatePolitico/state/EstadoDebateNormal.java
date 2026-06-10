package state;

import debate.ColaboradorPolitico;

public class EstadoDebateNormal
implements EstadoDebate {

    private GerenciadorEstadoDebate
        gerenciador;

    public EstadoDebateNormal(
        GerenciadorEstadoDebate gerenciador
    ) {
        this.gerenciador = gerenciador;
    }

    @Override
    public void solicitarDireitoResposta(
        ColaboradorPolitico politico
    ) {

        gerenciador
            .registrarSolicitacao(
                politico
            );
    }

    @Override
    public void finalizarEtapa() {

        gerenciador
            .avaliarSolicitacoes();
    }
}