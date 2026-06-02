package notificacao;

import prototype.Prototype;

public class Eleitor implements Observador, Prototype {

    private String email;

    public Eleitor(String email) {
        this.email = email;
    }

    @Override
    public void atualizar(String candidato) {
        System.out.println(
            "Eleitor " +
            email +
            " recebeu atualização: " +
            candidato +
            " está falando"
        );
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Eleitor clone() {
        return new Eleitor(email);
    }
}