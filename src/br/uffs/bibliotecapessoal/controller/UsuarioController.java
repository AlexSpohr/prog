package br.uffs.bibliotecapessoal.controller;

import br.uffs.bibliotecapessoal.model.Usuario;
import br.uffs.bibliotecapessoal.view.LivroView;
import br.uffs.bibliotecapessoal.view.UsuarioView;

public class UsuarioController {
    private Usuario usuario;
    private UsuarioView usuarioView;
    private LivroView livroView;

    private boolean usuarioAutenticado = false;

    public UsuarioController(UsuarioView usuarioView, LivroView livroView) {
        this.usuarioView = usuarioView;
        this.livroView = livroView;
    }

    public void logar() {
        String nome = usuarioView.capturarTexto("Digite o nome do usuário: ");
        int senha = usuarioView.capturarNumero("Digite a senha do usuário: ");

        if (autenticarUsuario(nome, senha)) {
            usuarioAutenticado = true;
            usuarioView.mostrarMensagem("Usuário autenticado com sucesso.");
        } else {
            usuarioAutenticado = false;
            usuarioView.mostrarMensagem("Falha na autenticação. Nome ou senha incorretos.");
        }
    }

    public void criarUsuario() {
        String nome = usuarioView.capturarTexto("Digite o nome do novo usuário: ");
        int senha = usuarioView.capturarNumero("Digite a senha do novo usuário: ");
        usuario = new Usuario(nome, senha);
        usuarioView.mostrarMensagem("Usuário criado com sucesso.");
    }

    public void excluirUsuario() {
        if (usuario != null) {
            usuario = null;
            usuarioAutenticado = false;
            usuarioView.mostrarMensagem("Usuário excluído com sucesso.");
        } else {
            usuarioView.mostrarMensagem("Nenhum usuário para excluir.");
        }
    }

    public void editarNomeUsuario() {
        if (usuario != null) {
            String novoNome = usuarioView.capturarTexto("Digite o novo nome do usuário: ");
            usuario.setNome(novoNome);
            usuarioView.mostrarMensagem("Nome do usuário atualizado para: " + novoNome);
        } else {
            usuarioView.mostrarMensagem("Nenhum usuário selecionado.");
        }
    }

    public Usuario getUsuarioLogado() {
        if (usuarioAutenticado) {
            return usuario;
        }
        return null;
    }

    public boolean autenticarUsuario(String nome, int senha) {
        if (usuario != null && usuario.getNome().equals(nome) && usuario.getSenha() == senha) {
            return true;
        }
        return false;
    }

    public boolean isUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
