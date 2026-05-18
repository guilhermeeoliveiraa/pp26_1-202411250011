package configuracao;

public class ConfiguraTempo {
    private int tempoPergunta;
    private int tempoResposta;
    private int tempoReplica;
    private int tempoTreplica;

    public ConfiguraTempo() {
        tempoPergunta = 120;
        tempoResposta = 90;
        tempoReplica = 60;
        tempoTreplica = 60;
    }

    public void setTempoPergunta(int t) {
        tempoPergunta = t;
    }

    public int getTempoPergunta() {
        return tempoPergunta;
    }

    public void setTempoResposta(int t) {
        tempoResposta = t;
    }

    public int getTempoResposta() {
        return tempoResposta;
    }

    public void setTempoReplica(int t) {
        tempoReplica = t;
    }

    public int getTempoReplica() {
        return tempoReplica;
    }

    public void setTempoTreplica(int t) {
        tempoTreplica = t;
    }

    public int getTempoTreplica() {
        return tempoTreplica;
    }
}