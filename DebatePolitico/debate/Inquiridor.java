package debate;

import log.LogSistem;

public class Inquiridor {
    private ColaboradorPolitico politico;

    public Inquiridor(ColaboradorPolitico politico) {
        this.politico = politico;
    }

    public void escolhaInquirido(ColaboradorPolitico p) {
        MediarDebate mediador = (MediarDebate) getPoliticoMediator();
        mediador.setInquirido(p);
    }

    private MediadorBase getPoliticoMediator() {
        try {
            var field = ColaboradorPolitico.class.getDeclaredField("mediador");
            field.setAccessible(true);
            return (MediadorBase) field.get(politico);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void perguntar(int tempo, LogSistem log) {
        politico.operacaoMediada();
        Microfone m = politico.getMicrofone();
        m.liga();
        log.registerLog("Pergunta iniciada por " + politico.getNome());
        m.passaTempo(tempo);
        m.desliga();
        log.registerLog("Tempo de pergunta finalizado.");
    }

    public void replica(int tempo, LogSistem log) {
        politico.operacaoMediada();
        Microfone m = politico.getMicrofone();
        m.liga();
        log.registerLog("Réplica iniciada por " + politico.getNome());
        m.passaTempo(tempo);
        m.desliga();
        log.registerLog("Tempo de réplica finalizado.");
    }

    public ColaboradorPolitico getPolitico() {
        return politico;
    }
}