package configuracao;

import debate.ColaboradorPolitico;
import debate.ColaboradorPoliticoBuilder;
import debate.MediadorBase;
import notificacao.Eleitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciaPolitico {

    private List<ColaboradorPolitico> politicos = new ArrayList<>();
    private int quantPoliticos = 0;

    public void criarPolitico(
        String nome,
        String partido,
        MediadorBase mediador
    ) {

        ColaboradorPolitico novo =
            new ColaboradorPoliticoBuilder()
                .nome(nome)
                .partido(partido)
                .mediador(mediador)
                .build();

        politicos.add(novo);
        quantPoliticos++;
    }

    public void adicionarEleitor(
        String nome,
        String partido,
        Eleitor eleitor
    ) {

        ColaboradorPolitico p = obterPolitico(nome);

        if (p == null) {
            throw new IllegalArgumentException(
                "Político não encontrado."
            );
        }

        p.adicionar(eleitor);
    }

    public ColaboradorPolitico obterPolitico(String nome) {

        for (ColaboradorPolitico p : politicos) {
            if (p.getNome().equals(nome)) {
                return p;
            }
        }

        return null;
    }

    public ColaboradorPolitico sortearPolitico() {

        if (politicos.isEmpty()) {
            throw new IllegalStateException(
                "Nenhum político cadastrado."
            );
        }

        List<ColaboradorPolitico> disponiveis =
            new ArrayList<>();

        for (ColaboradorPolitico p : politicos) {
            if (!p.getInquiridor()) {
                disponiveis.add(p);
            }
        }

        if (disponiveis.isEmpty()) {
            throw new IllegalStateException(
                "Todos os políticos já foram inquiridores."
            );
        }

        Random random = new Random();

        ColaboradorPolitico escolhido =
            disponiveis.get(
                random.nextInt(disponiveis.size())
            );

        escolhido.setInquiridor(true);

        return escolhido;
    }

    public int getQuantPoliticos() {
        return quantPoliticos;
    }
}