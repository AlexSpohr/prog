package br.uffs.bibliotecapessoal.model;


import java.util.ArrayList;
import java.util.List;

public class Livro {
    private String titulo;
    private String autor;
    private int paginas;
    private String status;

    private List<Anotacao> anotacoes = new ArrayList<>();

    public Livro(String titulo, String autor, int paginas, String status) {
        this.titulo = titulo;
        this.autor = autor;
        this.paginas = paginas;
        this.status = status;
    }

    public Livro() {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void adicionarAnotacao(Anotacao anotacao) {
        anotacoes.add(anotacao);
    }

    public void removerAnotacao(int index) {
        anotacoes.remove(index);
    }

    public Anotacao getAnotacao(int index) {
        return anotacoes.get(index);
    }

    public boolean isListaAnotacoesVazia() {
        return anotacoes.isEmpty();
    }

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

}

