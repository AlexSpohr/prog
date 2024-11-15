package br.uffs.bibliotecapessoal.model;

import java.util.Scanner;

public class LivroFiccao extends Livro {
    private Scanner scanner = new Scanner(System.in);
    private String tema;
    private int idadeRecomendada;

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getIdadeRecomendada() {
        return idadeRecomendada;
    }

    public void setIdadeRecomendada(int idadeRecomendada) {
        this.idadeRecomendada = idadeRecomendada;
    }

}
