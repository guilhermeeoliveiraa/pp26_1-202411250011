package usuario;

import java.util.Scanner;

import debate.ColaboradorPolitico;
import facade.FachadaDebate;

public class CLI {
    private FachadaDebate f;
    private Scanner scanner;

    public CLI() {
        f = FachadaDebate.getInstance();
        scanner = new Scanner(System.in);
    }

    public void realizarOperacao() {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            try {
                switch (opcao) {
                    case 1:
                        configurarTempos();
                        break;

                    case 2:
                        cadastrarPolitico();
                        break;

                    case 3:
                        cadastrarEleitor();
                        break;
                        
                    case 4:
                        sortearInquiridor();
                        break;

                    case 5:
                        escolherInquirido();
                        break;

                    case 6:
                        executarDebate();
                        break;

                    case 7:
                        mostrarInquiridor();
                        break;

                    case 8:
                        visualizarLog();
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println();
        } while (opcao != 0);

        scanner.close();
    }

    private void exibirMenu() {
        System.out.println("======================================");
        System.out.println("      SISTEMA DE DEBATE POLÍTICO");
        System.out.println("======================================");
        System.out.println("1 - Configurar tempos");
        System.out.println("2 - Cadastrar político");
        System.out.println("3 - Cadastrar eleitor");
        System.out.println("4 - Sortear inquiridor");
        System.out.println("5 - Escolher inquirido");
        System.out.println("6 - Executar debate");
        System.out.println("7 - Mostrar inquiridor atual");
        System.out.println("8 - Visualizar log");
        System.out.println("0 - Sair");
        System.out.println("======================================");
    }

    private void configurarTempos() {
        System.out.println("\n--- Configuração de Tempos ---");

        int pergunta = lerInteiro("Tempo da pergunta (segundos): ");
        int resposta = lerInteiro("Tempo da resposta (segundos): ");
        int replica = lerInteiro("Tempo da réplica (segundos): ");
        int treplica = lerInteiro("Tempo da tréplica (segundos): ");

        f.configuracao(pergunta, resposta, replica, treplica);

        System.out.println("Tempos configurados com sucesso.");
    }

    private void cadastrarPolitico() {
        System.out.println("\n--- Cadastro de Político ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Partido: ");
        String partido = scanner.nextLine();

        f.cadastrarPoliticos(nome, partido, f.getMediador());

        System.out.println("Político cadastrado com sucesso.");
    }

private void cadastrarEleitor() {
    System.out.println("\n--- Cadastro de Eleitor ---");

    System.out.print("Nome do eleitor: ");
    String nomeEleitor = scanner.nextLine();

    System.out.print("Email: ");
    String email = scanner.nextLine();

    System.out.print("Nome do político: ");
    String nomePolitico = scanner.nextLine();

    f.cadastrarEleitor(nomeEleitor, email, nomePolitico);

    System.out.println("Eleitor cadastrado com sucesso.");
}

    private void sortearInquiridor() {
        f.sorteioInquiridor();

        ColaboradorPolitico p = f.getPoliticoInquiridor();

        if (p != null) {
            System.out.println(
                "Inquiridor sorteado: " +
                p.getNome() +
                " (" +
                p.getPartido() +
                ")"
            );
        }
    }

    private void escolherInquirido() {
        System.out.println("\n--- Escolha do Inquirido ---");

        System.out.print("Nome do político: ");
        String nome = scanner.nextLine();

        f.escolherInquirido(nome);

        System.out.println("Inquirido selecionado com sucesso.");
    }

    private void executarDebate() {
        try {
            debate.MediarDebate mediador =
                (debate.MediarDebate) f.getMediador();

            debate.Inquiridor inquiridor = mediador.getInquiridor();
            debate.Inquirido inquirido = mediador.getInquirido();

            if (inquiridor == null || inquirido == null) {
                System.out.println(
                    "Erro: Inquiridor ou inquirido não foram definidos."
                );
                return;
            }

            String nomeInquiridor = inquiridor.getPolitico().getNome();
            String nomeInquirido = inquirido.getPolitico().getNome();

            System.out.println("\n========== INÍCIO DO DEBATE ==========\n");

            System.out.println(
                nomeInquiridor + " está fazendo a PERGUNTA."
            );
            inquiridor.perguntar(0, log.LogSistem.getInstance("log/debate.log"));

            System.out.println(
                nomeInquirido + " está fazendo a RESPOSTA."
            );
            inquirido.responder(0, log.LogSistem.getInstance("log/debate.log"));

            System.out.println(
                nomeInquiridor + " está fazendo a RÉPLICA."
            );
            inquiridor.replica(0, log.LogSistem.getInstance("log/debate.log"));

            System.out.println(
                nomeInquirido + " está fazendo a TRÉPLICA."
            );
            inquirido.treplica(0, log.LogSistem.getInstance("log/debate.log"));

            System.out.println("\n=========== FIM DO DEBATE ===========");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void mostrarInquiridor() {
        ColaboradorPolitico p = f.getPoliticoInquiridor();

        if (p == null) {
            System.out.println("Nenhum inquiridor foi sorteado.");
            return;
        }

        System.out.println(
            "Inquiridor atual: " +
            p.getNome() +
            " (" +
            p.getPartido() +
            ")"
        );
    }

    private void visualizarLog() {
        System.out.println("\n--- LOG DO SISTEMA ---");
        f.acessarLog();
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(scanner.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }
}