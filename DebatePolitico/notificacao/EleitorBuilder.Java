package notificacao;

public class EleitorBuilder {

    private String email;

    public EleitorBuilder email(String email) {
        this.email = email;
        return this;
    }

    public Eleitor build() {
        return new Eleitor(email);
    }
}