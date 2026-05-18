package debate;

import configuracao.ConfiguraTempo;
import log.LogSistem;

public class MediarDebate implements MediadorBase {
    private Inquiridor inquiridor;
    private Inquirido inquirido;

    @Override
    public void debate(ConfiguraTempo config, LogSistem log) {
        if (inquiridor == null || inquirido == null) {
            throw new IllegalStateException(
                "Inquiridor ou inquirido não definidos."
            );
        }

        log.registerLog("Início do debate.");

        inquiridor.perguntar(config.getTempoPergunta(), log);
        inquirido.responder(config.getTempoResposta(), log);
        inquiridor.replica(config.getTempoReplica(), log);
        inquirido.treplica(config.getTempoTreplica(), log);

        log.registerLog("Fim do debate.");
    }

    public void setInquiridor(ColaboradorPolitico politico) {
        inquiridor = new Inquiridor(politico);
    }

    public Inquiridor getInquiridor() {
        return inquiridor;
    }

    public void setInquirido(ColaboradorPolitico politico) {
        inquirido = new Inquirido(politico);
    }

    public Inquirido getInquirido() {
        return inquirido;
    }
}