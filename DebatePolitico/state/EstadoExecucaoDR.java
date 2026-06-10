package state;

import debate.ColaboradorPolitico;

public class EstadoExecucaoDR
implements EstadoDebate {

    private GerenciadorEstadoDebate
        gerenciador;

    public EstadoExecucaoDR(
        GerenciadorEstadoDebate gerenciador
    ) {
        this.gerenciador = gerenciador;
    }

    @Override
    public void solicitarDireitoResposta(
        ColaboradorPolitico politico
    ) {

        System.out.println(
            "Não é permitido solicitar DR durante a execução dos Direitos de Resposta."
        );
    }

    @Override
    public void finalizarEtapa() {

        gerenciador
            .retornarFluxoNormal();
    }
}