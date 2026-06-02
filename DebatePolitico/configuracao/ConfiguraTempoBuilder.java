package configuracao;

public class ConfiguraTempoBuilder {

    private int tempoPergunta;
    private int tempoResposta;
    private int tempoReplica;
    private int tempoTreplica;

    public ConfiguraTempoBuilder tempoPergunta(int tempo) {
        this.tempoPergunta = tempo;
        return this;
    }

    public ConfiguraTempoBuilder tempoResposta(int tempo) {
        this.tempoResposta = tempo;
        return this;
    }

    public ConfiguraTempoBuilder tempoReplica(int tempo) {
        this.tempoReplica = tempo;
        return this;
    }

    public ConfiguraTempoBuilder tempoTreplica(int tempo) {
        this.tempoTreplica = tempo;
        return this;
    }

    public ConfiguraTempo build() {

        ConfiguraTempo c = new ConfiguraTempo();

        c.setTempoPergunta(tempoPergunta);
        c.setTempoResposta(tempoResposta);
        c.setTempoReplica(tempoReplica);
        c.setTempoTreplica(tempoTreplica);

        return c;
    }
}