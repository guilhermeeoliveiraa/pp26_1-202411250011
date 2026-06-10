package debate;

import notificacao.Sujeito;
import prototype.Prototype;

public class ColaboradorPolitico extends Sujeito implements Prototype {

    private String nome;
    private String partido;
    private boolean foiInquiridor;
    private Microfone microfone;
    private MediadorBase mediador;

    public ColaboradorPolitico(
        String nome,
        String partido
    ) {

        this.nome = nome;

        this.partido = partido;

        this.foiInquiridor = false;

        this.microfone =
            new Microfone();
    }

    public void operacaoMediada() {
        notificar(nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setPartido(String partido) {
        this.partido = partido;
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

    public void setMicrofone(Microfone microfone) {
        this.microfone = microfone;
    }

    @Override
    public ColaboradorPolitico clone() {
        ColaboradorPolitico copia =
            new ColaboradorPolitico(nome, partido);

        copia.setInquiridor(foiInquiridor);
        copia.setMediador(mediador);

        return copia;
    }

    @Override
    public String toString() {
        return nome + " (" + partido + ")";
    }
}