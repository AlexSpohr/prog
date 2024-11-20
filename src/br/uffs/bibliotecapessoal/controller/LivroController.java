package br.uffs.bibliotecapessoal.controller;

import br.uffs.bibliotecapessoal.model.Livro;
import br.uffs.bibliotecapessoal.model.LivroBiografia;
import br.uffs.bibliotecapessoal.model.LivroFiccao;
import br.uffs.bibliotecapessoal.model.LivroFilosofico;
import br.uffs.bibliotecapessoal.model.LivroHistorico;
import br.uffs.bibliotecapessoal.model.Usuario;
import br.uffs.bibliotecapessoal.utils.Utils;
import br.uffs.bibliotecapessoal.view.LivroView;

import java.util.List;

public class LivroController {
    private final LivroView view;
    private Livro livro;


    public LivroController(Livro livro, LivroView view) {
        this.livro = livro;
        this.view = view;
    }

    public Livro criarLivro(Livro livro) {
        view.mostrarMensagem("------ Registro de Livro ------");

        String titulo = view.obterEntrada("-- Título:");
        String autor = view.obterEntrada("-- Autor:");
        int paginas = view.obterEntradaInt("-- Páginas:");

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setPaginas(paginas);
        livro.setStatus("Para Ler");

        if (livro instanceof LivroBiografia biografia) {
            biografia.setBiografado(view.obterEntrada("-- Nome do biografado:"));
            biografia.setPeriodoCoberto(view.obterEntradaInt("-- Período coberto:"));
        }
        if (livro instanceof LivroFiccao ficcao) {
            ficcao.setIdadeRecomendada(view.obterEntradaInt("-- Idade Recomendada:"));
            ficcao.setTema(view.obterEntrada("-- Tema:"));
        }
        if (livro instanceof LivroFilosofico filosofico) {
            filosofico.setFilosofiaAbordada(view.obterEntrada("-- Filosofia abordada:"));
            filosofico.setFilosofoAbordado(view.obterEntrada("-- Filosofo:"));
        }
        if (livro instanceof LivroHistorico historico) {
            historico.setAnoPerildoHistorico(view.obterEntradaInt("-- Ano acontecimento Histórico:"));
            historico.setPerildoHistorico(view.obterEntrada("-- Período Histórico:"));
        }

        view.mostrarMensagem("Livro registrado com sucesso!");
        return livro;
    }

    public void visualizarLivros(int opcao, Usuario usuario) {
        var listaLivros = usuario.getListaLivros();
        for (Livro livro : listaLivros) {
            view.printLivro(livro, opcao);
        }
    }

    public void mudarStatusLivro(Usuario usuario) {
        if (usuario.getEmptyLivros()) {
            view.mostrarMensagem("-- Não há livros cadastrados");
            return;
        }
        List<Livro> livros = usuario.getListaLivros();

        view.mostrarMensagem("-- Livros Para Ler:");
        livros.stream()
                .filter(livro -> "Para Ler".equals(livro.getStatus()))
                .forEach(livro -> view.mostrarMensagem("- " + livro.getTitulo()));

        view.mostrarMensagem("-- Livros Lendo:");
        livros.stream()
                .filter(livro -> "Lendo".equals(livro.getStatus()))
                .forEach(livro -> view.mostrarMensagem("- " + livro.getTitulo()));

        view.mostrarMensagem("-- Livros Lidos:");
        livros.stream()
                .filter(livro -> "Lido".equals(livro.getStatus()))
                .forEach(livro -> view.mostrarMensagem("- " + livro.getTitulo()));

        String titulo = view.obterEntrada("-- Digite o título do livro para alterar o status: ");
        Livro livroEscolhido = livros.stream()
                .filter(livro -> titulo.equalsIgnoreCase(livro.getTitulo()))
                .findFirst()
                .orElse(null);

        if (livroEscolhido == null) {
            view.mostrarMensagem("-- Livro não encontrado.");
            return;
        }

        view.mostrarMensagem("-- Para qual status deseja mudar o livro '" + livroEscolhido.getTitulo() + "' ?");
        int novoStatus = view.obterEntradaInt(
                "(1) Para Ler\n" +
                        "(2) Lendo\n" +
                        "(3) Lido\n" +
                        "(0) Sair\n" +
                        "-- Escolha uma opção: ");

        switch (novoStatus) {
            case 1 -> livroEscolhido.setStatus("Para Ler");
            case 2 -> livroEscolhido.setStatus("Lendo");
            case 3 -> livroEscolhido.setStatus("Lido");
            case 0 -> {
                view.mostrarMensagem("-- Operação cancelada.");
                return;
            }
            default -> {
                view.mostrarMensagem("-- Opção inválida.");
                return;
            }
        }
        view.mostrarMensagem("-- Status do livro atualizado com sucesso!");
    }

    public void excluirLivro(Usuario usuario) {
        if (usuario.getEmptyLivros()) {
            view.mostrarMensagem("-- Não há livros cadastrados para excluir.");
            return;
        }

        List<Livro> livros = usuario.getListaLivros();

        view.mostrarMensagem("-- Lista de Livros Cadastrados:");
        livros.forEach(livro -> view.mostrarMensagem("- " + livro.getTitulo()));

        String titulo = view.obterEntrada("-- Digite o título do livro que deseja excluir: ");
        Livro livroEscolhido = livros.stream()
                .filter(livro -> titulo.equalsIgnoreCase(livro.getTitulo()))
                .findFirst()
                .orElse(null);

        if (livroEscolhido == null) {
            view.mostrarMensagem("-- Livro não encontrado.");
            return;
        }

        boolean removido = usuario.removerLivroPorTitulo(titulo);

        if (removido) {
            view.mostrarMensagem("-- Livro '" + livroEscolhido.getTitulo() + "' excluído com sucesso!");
        } else {
            view.mostrarMensagem("-- Não foi possível excluir o livro.");
        }
    }


    public void editarLivro(Usuario usuario) {
        if (usuario.getEmptyLivros()) {
            view.mostrarMensagem("-- Não há livros cadastrados para editar.");
            return;
        }

        List<Livro> livros = usuario.getListaLivros();

        view.mostrarMensagem("-- Lista de Livros Cadastrados:");
        livros.forEach(livro -> view.mostrarMensagem("- " + livro.getTitulo()));

        String titulo = view.obterEntrada("-- Digite o título do livro que deseja editar: ");
        Livro livroEscolhido = livros.stream()
                .filter(livro -> titulo.equalsIgnoreCase(livro.getTitulo()))
                .findFirst()
                .orElse(null);

        if (livroEscolhido == null) {
            view.mostrarMensagem("-- Livro não encontrado.");
            return;
        }

        view.mostrarMensagem("------ Edição de Livro ------");
        livroEscolhido.setTitulo(view.obterEntrada("-- Novo Título:"));
        livroEscolhido.setAutor(view.obterEntrada("-- Novo Autor:"));
        livroEscolhido.setPaginas(view.obterEntradaInt("-- Número de Páginas:"));
        Utils.consumirLinha();

        if (livroEscolhido instanceof LivroBiografia biografia) {
            biografia.setBiografado(view.obterEntrada("-- Novo Nome do Biografado:"));
            biografia.setPeriodoCoberto(view.obterEntradaInt("-- Novo Período Coberto:"));
            Utils.consumirLinha();
        }
        if (livro instanceof LivroFiccao ficcao) {
            ficcao.setIdadeRecomendada(view.obterEntradaInt("-- Novo Idade Recomendada:"));
            ficcao.setTema(view.obterEntrada("-- Novo Tema:"));
            Utils.consumirLinha();
        }
        if (livro instanceof LivroFilosofico filosofico) {
            filosofico.setFilosofiaAbordada(view.obterEntrada("-- Novo Filosofia abordada:"));
            filosofico.setFilosofoAbordado(view.obterEntrada("-- Novo Filosofo:"));
            Utils.consumirLinha();
        }
        if (livro instanceof LivroHistorico historico) {
            historico.setAnoPerildoHistorico(view.obterEntradaInt("-- Novo Ano acontecimento Histórico:"));
            historico.setPerildoHistorico(view.obterEntrada("-- Novo Período Histórico:"));
            Utils.consumirLinha();
        }

        view.mostrarMensagem("-- Livro editado com sucesso!");
    }

}