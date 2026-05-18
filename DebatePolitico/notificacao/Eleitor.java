package notificacao;

public class Eleitor implements Observador {
    private String email;

    public Eleitor(String email) {
        this.email = email;
    }

    @Override
    public void atualizar(String candidato) {
        System.out.println("Eleitor " + email + " recebeu atualização: " + candidato + " está falando");
    }
}