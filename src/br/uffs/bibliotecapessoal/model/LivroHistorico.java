package br.uffs.bibliotecapessoal.model;

import java.util.Scanner;

public class LivroHistorico extends Livro {
    private Scanner scanner = new Scanner(System.in);
    private String perildoHistorico;
    private int anoCoberto;

    public String getPerildoHistorico() {
        return perildoHistorico;
    }

    public void setPerildoHistorico(String perildoHistorico) {
        this.perildoHistorico = perildoHistorico;
    }

    public int getAnoPerildoHistorico() {
        return anoCoberto;
    }

    public void setAnoPerildoHistorico(int anoCoberto) {
        this.anoCoberto = anoCoberto;
    }


}
