package state;

import debate.ColaboradorPolitico;

public class EstadoAnaliseDR
implements EstadoDebate {

    private GerenciadorEstadoDebate
        gerenciador;

    public EstadoAnaliseDR(
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
            .executarDireitosResposta();
    }
}