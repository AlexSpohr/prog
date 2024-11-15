package br.uffs.bibliotecapessoal.model;


import java.util.ArrayList;

public class Usuario {
    private String nome;
    private int senha;
    private ArrayList<Livro> listaLivros;

    public Usuario(String nome, int senha) {
        this.nome = nome;
        this.senha = senha;
        this.listaLivros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public void setLivro(Livro livro) {
        listaLivros.add(livro);
    }

    public Livro getLivro(int index) {
        if (index >= 0 && index < listaLivros.size()) {
            return listaLivros.get(index);
        }
        return null;
    }

    public Livro getLivroPorTitulo(String titulo) {
        for (Livro livro : listaLivros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public boolean getEmptyLivros() {
        return listaLivros.isEmpty();
    }

    public void excluirLivro(Livro livro) {
        listaLivros.remove(livro);
    }

    public int getSizeLista() {
        return listaLivros.size();
    }

    public ArrayList<Livro> getListaLivros() {
        return new ArrayList<>(listaLivros);
    }

    public Livro getLivroNome(String nome) {
        for (Livro livro : listaLivros) {
            if (livro.getTitulo().equalsIgnoreCase(nome)) {
                return livro;
            }
        }
        return null;
    }

}




