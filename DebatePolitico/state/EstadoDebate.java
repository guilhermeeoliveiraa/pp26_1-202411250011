package state;

import debate.ColaboradorPolitico;

public interface EstadoDebate {

    void solicitarDireitoResposta(
        ColaboradorPolitico politico
    );

    void finalizarEtapa();
}