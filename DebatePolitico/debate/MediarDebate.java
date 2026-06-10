package debate;

import configuracao.ConfiguraTempo;
import log.LogSistem;
import state.GerenciadorEstadoDebate;

public class MediarDebate
implements MediadorBase {

    private Inquiridor inquiridor;

    private Inquirido inquirido;

    private GerenciadorEstadoDebate
        gerenciadorEstado;

    public MediarDebate(
        GerenciadorEstadoDebate gerenciador
    ) {

        gerenciadorEstado =
            gerenciador;
    }

    @Override
    public void debate(
        ConfiguraTempo config,
        LogSistem log
    ) {

        if (
            inquiridor == null
            ||
            inquirido == null
        ) {

            throw new IllegalStateException(
                "Inquiridor ou inquirido não definidos."
            );
        }

        log.registerLog(
            "Início do debate."
        );

        inquiridor.perguntar(
            config.getTempoPergunta(),
            log
        );

        inquirido.responder(
            config.getTempoResposta(),
            log
        );

        inquiridor.replica(
            config.getTempoReplica(),
            log
        );

        inquirido.treplica(
            config.getTempoTreplica(),
            log
        );

        gerenciadorEstado
            .avaliarSolicitacoes();

        gerenciadorEstado
            .executarDireitosResposta();

        log.registerLog(
            "Fim do debate."
        );
    }

    public void avaliarDireitosResposta() {

        gerenciadorEstado
            .avaliarSolicitacoes();
    }

    public void setInquiridor(
        ColaboradorPolitico politico
    ) {

        inquiridor =
            new Inquiridor(
                politico
            );

        politico
            .getMicrofone()
            .setGerenciadorEstado(
                gerenciadorEstado
            );
    }

    public Inquiridor getInquiridor() {

        return inquiridor;
    }

    public void setInquirido(
        ColaboradorPolitico politico
    ) {

        inquirido =
            new Inquirido(
                politico
            );

        politico
            .getMicrofone()
            .setGerenciadorEstado(
                gerenciadorEstado
            );
    }

    public Inquirido getInquirido() {

        return inquirido;
    }

    public void solicitarDireitoResposta(
        ColaboradorPolitico politico
    ){
        gerenciadorEstado
            .solicitarDireitoResposta(
                politico
            );
    }
}