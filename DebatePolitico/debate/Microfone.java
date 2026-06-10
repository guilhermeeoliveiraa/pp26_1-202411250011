package debate;

import state.GerenciadorEstadoDebate;

public class Microfone {

    private boolean microAtivo;

    private GerenciadorEstadoDebate
        gerenciadorEstado;

    public Microfone() {

        microAtivo = false;
    }

    public Microfone(
        GerenciadorEstadoDebate gerenciador
    ) {

        microAtivo = false;

        gerenciadorEstado = gerenciador;
    }

    public void setGerenciadorEstado(
        GerenciadorEstadoDebate gerenciador
    ) {

        gerenciadorEstado = gerenciador;
    }

    public void liga() {

        microAtivo = true;

        System.out.println(
            "Microfone Ligado."
        );
    }

    public void desliga() {

        microAtivo = false;

        System.out.println(
            "Microfone Desligado."
        );
    }

    public boolean estaAtivo() {

        return microAtivo;
    }

    public void passaTempo(
        int tempo
    ) {

        while (tempo > 0) {
            tempo--;
        }
    }

    public void pressionarDR(
        ColaboradorPolitico politico
    ) {

        if (gerenciadorEstado == null) {

            throw new IllegalStateException(
                "Gerenciador de estado não configurado."
            );
        }

        gerenciadorEstado
            .solicitarDireitoResposta(
                politico
            );
    }
}