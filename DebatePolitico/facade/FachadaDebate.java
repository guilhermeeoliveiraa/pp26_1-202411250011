package facade;

import configuracao.ConfiguraTempo;
import configuracao.GerenciaPolitico;
import debate.ColaboradorPolitico;
import debate.Inquiridor;
import debate.MediadorBase;
import debate.MediarDebate;
import log.LogSistem;
import notificacao.Eleitor;
import notificacao.EleitorBuilder;
import state.GerenciadorEstadoDebate;

public class FachadaDebate {

    private static FachadaDebate instance;

    private ConfiguraTempo config;

    private MediadorBase mediador;

    private GerenciaPolitico gerenciador;

    private GerenciadorEstadoDebate gerenciadorEstado;

    private LogSistem log;

    private FachadaDebate() {

        config =
            new ConfiguraTempo();

        gerenciador =
            new GerenciaPolitico();

        gerenciadorEstado =
            new GerenciadorEstadoDebate();

        mediador =
            new MediarDebate(
                gerenciadorEstado
            );

        log =
            LogSistem.getInstance(
                "log/debate.log"
            );
    }

    public static FachadaDebate getInstance() {

        if (instance == null) {

            instance =
                new FachadaDebate();
        }

        return instance;
    }

    public MediadorBase getMediador() {

        return mediador;
    }

    public GerenciadorEstadoDebate
        getGerenciadorEstado() {

        return gerenciadorEstado;
    }

    public void configuracao(
        int pergunta,
        int resposta,
        int replica,
        int treplica
    ) {

        config.setTempoPergunta(
            pergunta
        );

        config.setTempoResposta(
            resposta
        );

        config.setTempoReplica(
            replica
        );

        config.setTempoTreplica(
            treplica
        );

        log.registerLog(
            "Tempos configurados."
        );
    }

    public void cadastrarPoliticos(
        String nome,
        String partido,
        MediadorBase mediador
    ) {

        gerenciador.criarPolitico(
            nome,
            partido,
            mediador
        );

        log.registerLog(
            "Político cadastrado: "
            + nome
        );
    }

    public void cadastrarEleitor(
        String nomeEleitor,
        String email,
        String nomePolitico
    ) {

        ColaboradorPolitico politico =
            gerenciador
                .obterPolitico(
                    nomePolitico
                );

        if (politico == null) {

            throw new IllegalArgumentException(
                "Político não encontrado."
            );
        }

        Eleitor eleitor =
            new EleitorBuilder()
                .email(email)
                .build();

        politico.adicionar(
            eleitor
        );

        log.registerLog(
            "Eleitor "
            + nomeEleitor
            + " cadastrado para "
            + nomePolitico
        );
    }

    public void sorteioInquiridor() {

        ColaboradorPolitico sorteado =
            gerenciador
                .sortearPolitico();

        ((MediarDebate) mediador)
            .setInquiridor(
                sorteado
            );

        log.registerLog(
            "Inquiridor sorteado: "
            + sorteado.getNome()
        );
    }

    public void escolherInquirido(
        String nome
    ) {

        ColaboradorPolitico escolhido =
            gerenciador
                .obterPolitico(
                    nome
                );

        if (escolhido == null) {

            throw new IllegalArgumentException(
                "Político não encontrado."
            );
        }

        Inquiridor inquiridor =
            ((MediarDebate) mediador)
                .getInquiridor();

        if (inquiridor == null) {

            throw new IllegalStateException(
                "Nenhum inquiridor foi sorteado."
            );
        }

        if (
            inquiridor
                .getPolitico()
                .equals(escolhido)
        ) {

            throw new IllegalArgumentException(
                "O inquiridor não pode escolher a si mesmo."
            );
        }

        inquiridor.escolhaInquirido(
            escolhido
        );

        log.registerLog(
            "Inquirido escolhido: "
            + escolhido.getNome()
        );
    }

    public void executaDebate() {

        mediador.debate(
            config,
            log
        );
    }

    public void solicitarDR(
        String nomePolitico
    ) {

        ColaboradorPolitico politico =
            gerenciador
                .obterPolitico(
                    nomePolitico
                );

        if (politico == null) {

            throw new IllegalArgumentException(
                "Político não encontrado."
            );
        }

        politico
            .getMicrofone()
            .pressionarDR(
                politico
            );
    }

    public ColaboradorPolitico getPolitico(
        String nome
    ) {

        return gerenciador
            .obterPolitico(
                nome
            );
    }

    public ColaboradorPolitico getPoliticoInquiridor() {

        Inquiridor inquiridor =
            ((MediarDebate) mediador)
                .getInquiridor();

        if (inquiridor == null) {

            return null;
        }

        return inquiridor
            .getPolitico();
    }

    public void acessarLog() {

        System.out.println(
            log.getLogsRegister()
        );
    }
}