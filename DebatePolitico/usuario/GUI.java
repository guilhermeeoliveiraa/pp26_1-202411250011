package usuario;

import facade.FachadaDebate;

public class GUI {
    private FachadaDebate f;

    public GUI() {
        f = FachadaDebate.getInstance();
    }

    public void realizarOperacao() {
        System.out.println("Interface gráfica iniciada.");
    }
}