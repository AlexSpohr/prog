package br.uffs.bibliotecapessoal.controller;

import br.uffs.bibliotecapessoal.model.*;
import br.uffs.bibliotecapessoal.view.BibliotecaView;
import br.uffs.bibliotecapessoal.view.LivroView;

import java.util.List;

public class BibliotecaController {
    private final BibliotecaView bibliotecaView;
    private final LivroView livroView;
    private final Usuario usuario;

    public BibliotecaController(BibliotecaView bibliotecaView, LivroView livroView, Usuario usuario) {
        this.bibliotecaView = bibliotecaView;
        this.livroView = livroView;
        this.usuario = usuario;
    }

}
