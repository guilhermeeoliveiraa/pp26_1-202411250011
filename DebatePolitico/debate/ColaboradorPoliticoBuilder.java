package debate;

public class ColaboradorPoliticoBuilder {

    private String nome;
    private String partido;
    private boolean inquiridor;
    private Microfone microfone;
    private MediadorBase mediador;

    public ColaboradorPoliticoBuilder nome(
        String nome
    ) {
        this.nome = nome;
        return this;
    }

    public ColaboradorPoliticoBuilder partido(
        String partido
    ) {
        this.partido = partido;
        return this;
    }

    public ColaboradorPoliticoBuilder inquiridor(
        boolean valor
    ) {
        this.inquiridor = valor;
        return this;
    }

    public ColaboradorPoliticoBuilder microfone(
        Microfone microfone
    ) {
        this.microfone = microfone;
        return this;
    }

    public ColaboradorPoliticoBuilder mediador(
        MediadorBase mediador
    ) {
        this.mediador = mediador;
        return this;
    }

    public ColaboradorPolitico build() {

        ColaboradorPolitico p =
            new ColaboradorPolitico(
                nome,
                partido
            );

        p.setInquiridor(
            inquiridor
        );

        p.setMediador(
            mediador
        );

        if (microfone != null) {

            p.setMicrofone(
                microfone
            );
        }

        return p;
    }
}