package br.uffs.bibliotecapessoal.model;

import java.util.Scanner;

public class LivroBiografia extends Livro {
    private Scanner scanner = new Scanner(System.in);
    private String biografado;
    private int perildoCoberto;

    public String getBiografado() {
        return biografado;
    }

    public void setBiografado(String biografado) {
        this.biografado = biografado;
    }

    public int getPerildoCoberto() {
        return perildoCoberto;
    }

    public void setPeriodoCoberto(int perildoCoberto) {
        this.perildoCoberto = perildoCoberto;
    }
}

