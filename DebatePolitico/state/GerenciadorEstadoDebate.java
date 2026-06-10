package state;

import debate.ColaboradorPolitico;
import debate.Microfone;
import log.LogSistem;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GerenciadorEstadoDebate {

    private EstadoDebate estadoAtual;

    private Queue<SolicitacaoDR>
        filaSolicitacoes =
            new LinkedList<>();

    private int contadorSolicitacoes = 0;

    public GerenciadorEstadoDebate() {

        estadoAtual =
            new EstadoDebateNormal(this);
    }

    public void setEstado(
        EstadoDebate estado
    ) {

        estadoAtual = estado;
    }

    public EstadoDebate getEstado() {

        return estadoAtual;
    }

    public void solicitarDireitoResposta(
        ColaboradorPolitico politico
    ) {

        estadoAtual
            .solicitarDireitoResposta(
                politico
            );
    }

    public void registrarSolicitacao(
        ColaboradorPolitico politico
    ) {

        for (SolicitacaoDR s : filaSolicitacoes) {

            if (
                s.getPolitico()
                .equals(politico)
            ) {

                System.out.println(
                    politico.getNome()
                    + " já solicitou Direito de Resposta."
                );

                return;
            }
        }

        SolicitacaoDR solicitacao =
            new SolicitacaoDR(
                politico,
                ++contadorSolicitacoes
            );

        filaSolicitacoes.add(
            solicitacao
        );

        System.out.println(
            politico.getNome()
            + " solicitou Direito de Resposta."
        );

        LogSistem
            .getInstance("log/debate.log")
            .registerLog(
                politico.getNome()
                + " solicitou Direito de Resposta."
            );
    }

    public void avaliarSolicitacoes() {

        if (
            filaSolicitacoes.isEmpty()
        ) {

            return;
        }

        setEstado(
            new EstadoAnaliseDR(this)
        );
    }

    public void aprovarSolicitacao(
        SolicitacaoDR s
    ) {

        s.setConcedido(true);

        LogSistem
            .getInstance("log/debate.log")
            .registerLog(
                "Direito de Resposta APROVADO para "
                + s.getPolitico().getNome()
            );
    }

    public void rejeitarSolicitacao(
        SolicitacaoDR s
    ) {

        s.setConcedido(false);

        LogSistem
            .getInstance("log/debate.log")
            .registerLog(
                "Direito de Resposta REJEITADO para "
                + s.getPolitico().getNome()
            );
    }

    public void concederDireitoResposta(
        SolicitacaoDR s
    ) {

        ColaboradorPolitico politico =
            s.getPolitico();

        System.out.println(
            "\nDireito de Resposta concedido para "
            + politico.getNome()
        );

        LogSistem
            .getInstance("log/debate.log")
            .registerLog(
                "Início do Direito de Resposta de "
                + politico.getNome()
            );

        politico.operacaoMediada();

        Microfone microfone =
            politico.getMicrofone();

        microfone.liga();

        microfone.passaTempo(60);

        microfone.desliga();

        LogSistem
            .getInstance("log/debate.log")
            .registerLog(
                "Fim do Direito de Resposta de "
                + politico.getNome()
            );
    }

    public void executarDireitosResposta() {

        if (
            filaSolicitacoes.isEmpty()
        ) {

            retornarFluxoNormal();

            return;
        }

        setEstado(
            new EstadoExecucaoDR(this)
        );

        Scanner scanner =
            new Scanner(System.in);

        while (
            !filaSolicitacoes.isEmpty()
        ) {

            SolicitacaoDR s =
                filaSolicitacoes.poll();

            System.out.println();

            System.out.println(
                "Solicitação de DR de "
                + s.getPolitico()
                    .getNome()
            );

            System.out.print(
                "Conceder DR? (s/n): "
            );

            String resposta =
                scanner.nextLine();

            if (
                resposta.equalsIgnoreCase(
                    "s"
                )
            ) {

                aprovarSolicitacao(s);

                concederDireitoResposta(
                    s
                );
            }

            else {

                rejeitarSolicitacao(
                    s
                );

                System.out.println(
                    "DR rejeitado."
                );
            }
        }

        retornarFluxoNormal();
    }

    public void retornarFluxoNormal() {

        setEstado(
            new EstadoDebateNormal(
                this
            )
        );
    }

    public Queue<SolicitacaoDR>
        getFilaSolicitacoes() {

        return filaSolicitacoes;
    }

    public void finalizarEtapa() {

        estadoAtual.finalizarEtapa();
    }
}