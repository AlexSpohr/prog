package br.uffs.bibliotecapessoal.view;

import br.uffs.bibliotecapessoal.controller.AnotacaoController;
import br.uffs.bibliotecapessoal.controller.LivroController;
import br.uffs.bibliotecapessoal.model.*;
import br.uffs.bibliotecapessoal.utils.Utils;

public class BibliotecaView {
    private LivroController livroController;
    private final AnotacaoController anotacaoController;
    private final LivroView livroView;

    public BibliotecaView(AnotacaoController anotacaoController) {
        this.anotacaoController = anotacaoController;
        this.livroView = new LivroView();
        this.livroController = new LivroController(new Livro(), this.livroView);
    }

    public void exibirMenuBiblioteca(Usuario usuario) {
        int escolha = -1;

        while (escolha != 0) {
            System.out.println("------- Menu Biblioteca --------");
            System.out.println("1 - Registar Livro");
            System.out.println("2 - Visualizar Livros");
            System.out.println("3 - Editar Livro");
            System.out.println("4 - Excluir Livro");
            System.out.println("5 - Mudar status do livro");
            System.out.println("6 - Gerenciar Anotações");
            System.out.println("0 - Voltar");
            System.out.print("-- Escolha uma opção: \n");

            escolha = Utils.getInt();
            switch (escolha) {
                case 1:
                    registrarLivro(usuario);
                    break;
                case 2:
                    visualizarLivros(usuario);
                    break;
                case 3:
                    livroController.editarLivro(usuario);
                    break;
                case 4:
                    livroController.excluirLivro(usuario);
                    break;
                case 5:
                    livroController.mudarStatusLivro(usuario);
                    break;
                case 6:
                    gerenciarAnotacoes(usuario);
                    break;
                case 0:
                    System.out.println("-- Voltando para o menu anterior...");
                    return;
                default:
                    System.out.println("-- Opção inválida, tente novamente.");
            }
        }
    }

    private void registrarLivro(Usuario usuario) {
        Utils.limparTerminal();
        System.out.println("-------Registro de livros-------");
        System.out.println("1 - Livro Genérico");
        System.out.println("2 - Livro Biográfico");
        System.out.println("3 - Livro de Ficção");
        System.out.println("4 - Livro Filosófico");
        System.out.println("5 - Livro Histórico");
        System.out.println("0 - Sair");
        System.out.println("-- Selecione o modelo do Livro:");

        int escolha = Utils.getInt();

        Livro livro = null;

        switch (escolha) {
            case 1:
                livro = livroController.criarLivro(new Livro());
                break;
            case 2:
                livro = livroController.criarLivro(new LivroBiografia());
                break;
            case 3:
                livro = livroController.criarLivro(new LivroFiccao());
                break;
            case 4:
                livro = livroController.criarLivro(new LivroFilosofico());
                break;
            case 5:
                livro = livroController.criarLivro(new LivroHistorico());
                break;
            case 0:
                System.out.println("-- Operação cancelada.");
                return;
            default:
                System.out.println("-- Opção inválida, operação cancelada.");
                return;
        }

        if (livro != null) {
            usuario.setLivro(livro);
            System.out.println("-- Livro registrado com sucesso!");
        }
    }


    public void visualizarLivros(Usuario usuario) {
        if (usuario.getEmptyLivros()) {
            System.out.println("-- Não há livros cadastrados");
            return;
        }

        Utils.limparTerminal();
        System.out.println("------- Visualização de livros -------");
        System.out.println("1 - Todos os livros");
        System.out.println("2 - Livros para ler");
        System.out.println("3 - Livros que está lendo");
        System.out.println("4 - Livros lidos");
        System.out.println("0 - Voltar");
        System.out.println("-- Selecione a opção de visualização de livros:");
        int escolha = Utils.getInt();
        if (escolha == 0) {
            return;
        }
        livroController.visualizarLivros(escolha, usuario);
    }

    private void gerenciarAnotacoes(Usuario usuario) {
        System.out.println("------- Seleção de Livro para Anotação -------");
        Livro livroSelecionado = selecionarLivroParaAnotacao(usuario);

        if (livroSelecionado == null) {
            System.out.println("-- Nenhum livro selecionado ou não há livros disponíveis.");
            return;
        }

        AnotacaoView anotacaoView = new AnotacaoView();
        AnotacaoController anotacaoController = new AnotacaoController(livroSelecionado, anotacaoView);

        int escolha = -1;
        while (escolha != 0) {
            System.out.println("------- Gerenciar Anotações -------");
            System.out.println("1 - Criar Anotação");
            System.out.println("2 - Visualizar Anotações");
            System.out.println("3 - Editar Anotação");
            System.out.println("4 - Excluir Anotação");
            System.out.println("0 - Voltar");
            System.out.print("-- Escolha uma opção: \n");

            escolha = Utils.getInt();

            switch (escolha) {
                case 1:
                    anotacaoController.criarAnotacao();
                    break;
                case 2:
                    anotacaoController.visualizarAnotacoes();
                    break;
                case 3:
                    anotacaoController.editarAnotacao();
                    break;
                case 4:
                    anotacaoController.excluirAnotacao();
                    break;
                case 0:
                    System.out.println("-- Voltando para o menu da biblioteca...");
                    return;
                default:
                    System.out.println("-- Opção inválida, tente novamente.");
            }
        }
    }

    private Livro selecionarLivroParaAnotacao(Usuario usuario) {
        System.out.println("------- Lista de Livros -------");
        if (usuario.getEmptyLivros()) {
            System.out.println("-- Não há livros cadastrados.");
            return null;
        }

        int indice = 1;
        for (Livro livro : usuario.getListaLivros()) {
            System.out.println(indice + " - " + livro.getTitulo());
            indice++;
        }

        System.out.print("-- Escolha um livro pelo número ou 0 para voltar: ");
        int escolha = Utils.getInt();

        if (escolha <= 0 || escolha > usuario.getSizeLista()) {
            System.out.println("-- Opção inválida ou retorno solicitado.");
            return null;
        }

        return usuario.getListaLivros().get(escolha - 1);
    }
}
