package br.uffs.bibliotecapessoal.view;

import br.uffs.bibliotecapessoal.model.Anotacao;
import java.util.List;
import java.util.Scanner;

public class AnotacaoView {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public String obterConteudoAnotacao() {
        System.out.println("-- Anotação:");
        return scanner.nextLine();
    }

    public int obterCapituloAnotacao() {
        System.out.println("-- Capítulo:");
        int capitulo = scanner.nextInt();
        scanner.nextLine();
        return capitulo;
    }

    public int obterIndiceAnotacao() {
        System.out.println("-- Digite o número da anotação:");
        return scanner.nextInt() - 1;
    }

    public void exibirAnotacoes(List<Anotacao> anotacoes) {
        if (anotacoes.isEmpty()) {
            mostrarMensagem("-- Nenhuma anotação cadastrada.");
        } else {
            for (int i = 0; i < anotacoes.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + anotacoes.get(i).getConteudo());
                System.out.println("---------------------------------");
            }
        }
    }

    public void exibirAnotacoesDetalhadas(List<Anotacao> anotacoes) {
        if (anotacoes.isEmpty()) {
            mostrarMensagem("-- Nenhuma anotação cadastrada.");
        } else {
            for (int i = 0; i < anotacoes.size(); i++) {
                System.out.println("[" + (i + 1) + "] Conteúdo: " + anotacoes.get(i).getConteudo());
                System.out.println("   Capítulo: " + anotacoes.get(i).getCapitulo());
                System.out.println("   Data: " + anotacoes.get(i).getData());
                System.out.println("---------------------------------");
            }
        }
    }

    public void limparBufferScanner() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}
