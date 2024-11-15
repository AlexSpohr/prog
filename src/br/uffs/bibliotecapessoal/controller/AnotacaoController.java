package br.uffs.bibliotecapessoal.controller;

import br.uffs.bibliotecapessoal.model.Livro;
import br.uffs.bibliotecapessoal.model.Anotacao;
import br.uffs.bibliotecapessoal.view.AnotacaoView;
import java.time.LocalDate;

public class AnotacaoController {
    private final Livro livro;
    private final AnotacaoView view;

    public AnotacaoController(Livro livro, AnotacaoView view) {
        this.livro = livro;
        this.view = view;
    }

    public void criarAnotacao() {
        view.mostrarMensagem("------ Criar Anotação ------");
        String conteudo = view.obterConteudoAnotacao();
        int capitulo = view.obterCapituloAnotacao();

        Anotacao novaAnotacao = new Anotacao();
        novaAnotacao.setConteudo(conteudo);
        novaAnotacao.setCapitulo(capitulo);
        novaAnotacao.setData(LocalDate.now().toString());

        livro.adicionarAnotacao(novaAnotacao);
        view.mostrarMensagem("Anotação criada com sucesso!");
    }

    public void visualizarAnotacoes() {
        view.mostrarMensagem("------ Anotações ------");
        view.exibirAnotacoes(livro.getAnotacoes());
    }

    public void editarAnotacao() {
        view.mostrarMensagem("------ Editar Anotação ------");
        visualizarAnotacoes();
        int indice = view.obterIndiceAnotacao();

        if (indice >= 0 && indice < livro.getAnotacoes().size()) {
            Anotacao anotacao = livro.getAnotacoes().get(indice);
            view.mostrarMensagem("Conteúdo atual: " + anotacao.getConteudo());

            String novoConteudo = view.obterConteudoAnotacao();
            int novoCapitulo = view.obterCapituloAnotacao();

            anotacao.setConteudo(novoConteudo);
            anotacao.setCapitulo(novoCapitulo);

            view.mostrarMensagem("Anotação editada com sucesso!");
        } else {
            view.mostrarMensagem("Anotação inexistente.");
        }
    }

    public void excluirAnotacao() {
        view.mostrarMensagem("------ Excluir Anotação ------");
        visualizarAnotacoes();
        int indice = view.obterIndiceAnotacao();

        if (indice >= 0 && indice < livro.getAnotacoes().size()) {
            livro.removerAnotacao(indice);
            view.mostrarMensagem("Anotação excluída com sucesso!");
        } else {
            view.mostrarMensagem("Anotação inexistente.");
        }
    }
}
