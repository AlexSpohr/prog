package br.uffs.bibliotecapessoal.view;

import br.uffs.bibliotecapessoal.controller.UsuarioController;
import br.uffs.bibliotecapessoal.model.Usuario;
import br.uffs.bibliotecapessoal.utils.Utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UsuarioView {
    private UsuarioController usuarioController;
    private BibliotecaView bibliotecaView = new BibliotecaView(null);
    private static Scanner scanner = new Scanner(System.in);

    public static void iniciarMenu() {
        UsuarioView view = new UsuarioView();
        view.inicializarController();
        view.exibirMenuLogin();
    }

    public UsuarioView() {
    }

    private void inicializarController() {
        LivroView livroView = new LivroView();
        this.usuarioController = new UsuarioController(this, livroView);
    }

    public void exibirMenuLogin() {
        int escolha = -1;

        while (escolha != 0) {
            System.out.println("------- Menu --------");
            System.out.println("1 - Logar");
            System.out.println("2 - Criar Usuario");
            System.out.println("0 - Sair");
            System.out.print("-- Escolha uma opção: \n");

            escolha = Utils.getInt();
            switch (escolha) {
                case 1:
                    usuarioController.logar();
                    if (usuarioController.isUsuarioAutenticado()) {
                        exibirHome();
                    }
                    break;
                case 2:
                    usuarioController.criarUsuario();
                    break;
                case 0:
                    System.out.println("-- Saindo...");
                    break;
                default:
                    System.out.println("-- Opção inválida, tente novamente.");
            }
        }
    }

    public void exibirHome() {
        int escolha = -1;

        while (escolha != 0) {
            Utils.limparTerminal();
            System.out.println("------- Home --------");
            System.out.println("1 - Excluir Usuario");
            System.out.println("2 - Editar Nome do Usuario");
            System.out.println("3 - Acessar Biblioteca");
            System.out.println("0 - Sair");
            System.out.print("-- Escolha uma opção: \n");

            escolha = Utils.getInt();
            switch (escolha) {
                case 1:
                    usuarioController.excluirUsuario();
                    return;
                case 2:
                    usuarioController.editarNomeUsuario();
                    break;
                case 3:
                    Usuario usuarioLogado = usuarioController.getUsuarioLogado();
                    if (usuarioLogado != null) {
                        bibliotecaView.exibirMenuBiblioteca(usuarioLogado);
                    } else {
                        System.out.println("Usuário não autenticado.");
                    }
                    break;
                case 0:
                    System.out.println("-- Saindo...");
                    break;
                default:
                    System.out.println("-- Opção inválida, tente novamente.");
            }
        }
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public String capturarTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int capturarNumero(String prompt) {
        System.out.print(prompt);
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextInt();
                scanner.nextLine();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
            }
        }

        return numero;
    }
}

