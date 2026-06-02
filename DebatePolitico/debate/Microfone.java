package debate;

public class Microfone {

    private boolean microAtivo;

    public Microfone() {
        microAtivo = false;
    }

    public void liga() {
        microAtivo = true;
        System.out.println("Microfone Ligado.");
    }

    public void desliga() {
        microAtivo = false;
        System.out.println("Microfone Desligado.");
    }

    public boolean estaAtivo() {
        return microAtivo;
    }

    public void passaTempo(int tempo) {
        while (tempo > 0) {
            tempo--;
        }
    }
}