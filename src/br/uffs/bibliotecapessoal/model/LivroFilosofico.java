package br.uffs.bibliotecapessoal.model;

public class LivroFilosofico extends Livro {
    private String filosofo;
    private String filosofia;

    public String getFilosofoAbordado() {
        return filosofo;
    }

    public void setFilosofoAbordado(String filosofoAbordado) {
        this.filosofo = filosofoAbordado;
    }

    public String getFilosofiaAbordada() {
        return filosofia;
    }

    public void setFilosofiaAbordada(String filosofiaAbordada) {
        this.filosofia = filosofiaAbordada;
    }


}