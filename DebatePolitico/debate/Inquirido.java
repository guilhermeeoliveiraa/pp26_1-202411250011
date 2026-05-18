package debate;

import log.LogSistem;

public class Inquirido {
    private ColaboradorPolitico politico;

    public Inquirido(ColaboradorPolitico politico) {
        this.politico = politico;
    }

    public void responder(int tempo, LogSistem log) {
        politico.operacaoMediada();
        Microfone m = politico.getMicrofone();
        m.liga();
        log.registerLog("Resposta iniciada por " + politico.getNome());
        m.passaTempo(tempo);
        m.desliga();
        log.registerLog("Tempo de resposta finalizado.");
    }

    public void treplica(int tempo, LogSistem log) {
        politico.operacaoMediada();
        Microfone m = politico.getMicrofone();
        m.liga();
        log.registerLog("Tréplica iniciada por " + politico.getNome());
        m.passaTempo(tempo);
        m.desliga();
        log.registerLog("Tempo de tréplica finalizado.");
    }

    public ColaboradorPolitico getPolitico() {
        return politico;
    }
}