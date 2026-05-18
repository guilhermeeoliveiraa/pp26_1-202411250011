package facade;

import configuracao.ConfiguraTempo;
import configuracao.GerenciaPolitico;
import debate.ColaboradorPolitico;
import debate.Inquiridor;
import debate.MediadorBase;
import debate.MediarDebate;
import log.LogSistem;

public class FachadaDebate {
    private static FachadaDebate instance;

    private ConfiguraTempo config;
    private MediadorBase mediador;
    private GerenciaPolitico gerenciador;
    private LogSistem log;

    private FachadaDebate() {
        config = new ConfiguraTempo();
        mediador = new MediarDebate();
        gerenciador = new GerenciaPolitico();
        log = LogSistem.getInstance("log/debate.log");
    }

    public static FachadaDebate getInstance() {
        if (instance == null) {
            instance = new FachadaDebate();
        }
        return instance;
    }

    public MediadorBase getMediador() {
        return mediador;
    }

    public void configuracao(int pergunta, int resposta, int replica, int treplica) {
        config.setTempoPergunta(pergunta);
        config.setTempoResposta(resposta);
        config.setTempoReplica(replica);
        config.setTempoTreplica(treplica);

        log.registerLog("Tempos do debate configurados.");
    }

    public void cadastrarPoliticos(String nome, String partido, MediadorBase mediador) {
        gerenciador.criarPolitico(nome, partido, mediador);
        log.registerLog("Político " + nome + " do partido " + partido + " cadastrado.");
    }

    public void cadastrarEleitor(String nomeEleitor, String email, String nomePolitico) {
        ColaboradorPolitico p = gerenciador.obterPolitico(nomePolitico);

        if (p == null) {
            throw new IllegalArgumentException("Político não encontrado.");
        }

        p.adicionar(new notificacao.Eleitor(email));

        log.registerLog(
            "Eleitor " + nomeEleitor +
            " (" + email + ") cadastrado para " +
            nomePolitico
        );
    }

    public void sorteioInquiridor() {
        ColaboradorPolitico escolhido = gerenciador.sortearPolitico();
        ((MediarDebate) mediador).setInquiridor(escolhido);

        log.registerLog(
            "Inquiridor sorteado: "
            + escolhido.getNome()
            + " ("
            + escolhido.getPartido()
            + ")"
        );
    }

    public void escolherInquirido(String nome) {
        ColaboradorPolitico escolhido =
            gerenciador.obterPolitico(nome);

        if (escolhido == null) {
            throw new IllegalArgumentException(
                "Político não encontrado."
            );
        }

        Inquiridor inquiridor =
            ((MediarDebate) mediador).getInquiridor();

        if (inquiridor == null) {
            throw new IllegalStateException(
                "Nenhum inquiridor foi sorteado."
            );
        }

        if (inquiridor.getPolitico() == escolhido) {
            throw new IllegalArgumentException(
                "O inquiridor não pode escolher a si mesmo."
            );
        }

        inquiridor.escolhaInquirido(escolhido);

        log.registerLog(
            "Inquirido escolhido: "
            + escolhido.getNome()
            + " ("
            + escolhido.getPartido()
            + ")"
        );
    }

    public void executaDebate() {
        mediador.debate(config, log);
    }

    public void acessarLog() {
        System.out.println(log.getLogsRegister());
    }

    public ColaboradorPolitico getPoliticoInquiridor() {
        Inquiridor inquiridor =
            ((MediarDebate) mediador).getInquiridor();

        if (inquiridor == null) {
            return null;
        }

        return inquiridor.getPolitico();
    }
}