package br.uffs.bibliotecapessoal.view;

import br.uffs.bibliotecapessoal.model.Livro;
import br.uffs.bibliotecapessoal.model.LivroBiografia;
import br.uffs.bibliotecapessoal.model.LivroFiccao;
import br.uffs.bibliotecapessoal.model.LivroFilosofico;
import br.uffs.bibliotecapessoal.model.LivroHistorico;

import java.util.Scanner;

public class LivroView {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public String obterEntrada(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int obterEntradaInt(String prompt) {
        mostrarMensagem(prompt);
        while (!scanner.hasNextInt()) {
            mostrarMensagem("Entrada inválida. Digite um número inteiro:");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    public void printLivro(Livro livro, int opcao) {
        boolean opcaoValidaLer = (opcao == 2 && !livro.getStatus().equals("Para Ler"));
        boolean opcaoValidaLendo = (opcao == 3 && !livro.getStatus().equals("Lendo"));
        boolean opcaoValidaLido = (opcao == 4 && !livro.getStatus().equals("Lido"));

        if (opcaoValidaLer || opcaoValidaLendo || opcaoValidaLido) {
            mostrarMensagem("Livros não encontrados!");
            return;
        }

        mostrarMensagem("=================================");
        mostrarMensagem("-- Título: " + livro.getTitulo());
        mostrarMensagem("-- Autor: " + livro.getAutor());
        mostrarMensagem("-- Páginas: " + livro.getPaginas());

        if (livro instanceof LivroBiografia) {
            LivroBiografia biografia = (LivroBiografia) livro;
            mostrarMensagem("-- Biografado: " + biografia.getBiografado());
            mostrarMensagem("-- Período Coberto: " + biografia.getPerildoCoberto());
        }

        if (livro instanceof LivroFiccao) {
            LivroFiccao ficcao = (LivroFiccao) livro;
            mostrarMensagem("-- IdadeRecomendado: " + ficcao.getIdadeRecomendada());
            mostrarMensagem("-- Tema: " + ficcao.getTema());
        }

        if (livro instanceof LivroFilosofico) {
            LivroFilosofico filosofico = (LivroFilosofico) livro;
            mostrarMensagem("-- Filosofia abordada: " + filosofico.getFilosofiaAbordada());
            mostrarMensagem("-- Filosofo: " + filosofico.getFilosofoAbordado());
        }

        if (livro instanceof LivroHistorico) {
            LivroHistorico historico = (LivroHistorico) livro;
            mostrarMensagem("-- Ano do acontecimento histórico: " + historico.getAnoPerildoHistorico());
            mostrarMensagem("-- Período histórico: " + historico.getPerildoHistorico());
        }

        mostrarMensagem("-- Status: " + livro.getStatus());
        mostrarMensagem("=================================");
    }

    public String obterBiografado() {
        return obterEntrada("-- Biografado:");
    }

    public int obterPeriodoCoberto() {
        return obterEntradaInt("-- Período Coberto:");
    }
}