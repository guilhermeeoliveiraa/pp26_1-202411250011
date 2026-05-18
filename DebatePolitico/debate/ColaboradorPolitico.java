package debate;

import notificacao.Sujeito;

public class ColaboradorPolitico extends Sujeito {
    private String nome;
    private String partido;
    private boolean foiInquiridor;
    private Microfone microfone;
    private MediadorBase mediador;

    public ColaboradorPolitico(String nome, String partido) {
        this.nome = nome;
        this.partido = partido;
        this.foiInquiridor = false;
        this.microfone = new Microfone();
    }

    public void operacaoMediada() {
        notificar(nome);
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setMediador(MediadorBase mediador) {
        this.mediador = mediador;
    }

    public MediadorBase getMediador() {
        return mediador;
    }

    public void setInquiridor(boolean valor) {
        this.foiInquiridor = valor;
    }

    public boolean getInquiridor() {
        return foiInquiridor;
    }

    public Microfone getMicrofone() {
        return microfone;
    }

    @Override
    public String toString() {
        return nome + " (" + partido + ")";
    }
}