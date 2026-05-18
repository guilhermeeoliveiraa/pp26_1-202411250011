package notificacao;

import java.util.ArrayList;
import java.util.List;

public class Sujeito {
    private List<Observador> observadores = new ArrayList<>();

    public void adicionar(Observador o) {
        observadores.add(o);
    }

    public void remover(Observador o) {
        observadores.remove(o);
    }

    public void notificar(String candidato) {
        for (Observador o : observadores) {
            o.atualizar(candidato);
        }
    }
}