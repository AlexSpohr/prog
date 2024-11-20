package br.uffs.bibliotecapessoal.controller;

import br.uffs.bibliotecapessoal.model.Usuario;
import br.uffs.bibliotecapessoal.view.LivroView;
import br.uffs.bibliotecapessoal.view.UsuarioView;

import java.util.HashMap;
import java.util.Map;

public class UsuarioController {
    private Usuario usuarioLogado;
    private Usuario usuario;
    private UsuarioView usuarioView;
    private LivroView livroView;

    private boolean usuarioAutenticado = false;
    private static Map<String, Usuario> usuarios = new HashMap<>();

    public UsuarioController(UsuarioView usuarioView, LivroView livroView) {
        this.usuarioView = usuarioView;
        this.livroView = livroView;
    }

    public void logar() {
        String nome = usuarioView.capturarTexto("Digite o nome do usuário: ");
        String senha = usuarioView.capturarTexto("Digite a senha do usuário: ");

        Usuario usuario = usuarios.get(nome);

        if (autenticarUsuario(nome, senha)) {
            usuarioAutenticado = true;
            usuarioLogado = usuario;
            usuarioView.mostrarMensagem("Usuário autenticado com sucesso.");
            usuarioLogado = usuarios.get(nome);
        } else {
            usuarioAutenticado = false;
            usuarioView.mostrarMensagem("Falha na autenticação. Nome ou senha incorretos.");
        }
    }

    public void criarUsuario() {
        String nome = usuarioView.capturarTexto("Digite o nome do novo usuário: ");

        if (usuarios.containsKey(nome)) {
            usuarioView.mostrarMensagem("Erro: Já existe um usuário com esse nome.");
            return;
        }

        String senha = usuarioView.capturarTexto("Digite a senha do novo usuário: ");
        Usuario novoUsuario = new Usuario(nome, senha);
        usuarios.put(nome, novoUsuario);
        usuarioView.mostrarMensagem("Usuário criado com sucesso.");
    }

    public void excluirUsuario() {
        if (usuarioLogado != null) {
            usuarios.remove(usuarioLogado.getNome());
            usuarioLogado = null;
            usuarioAutenticado = false;
            usuarioView.mostrarMensagem("Usuário excluído com sucesso.");
        } else {
            usuarioView.mostrarMensagem("Nenhum usuário logado para excluir.");
        }
    }

    public void editarNomeUsuario() {
        if (usuarioLogado != null) {
            String novoNome = usuarioView.capturarTexto("Digite o novo nome do usuário: ");

            if (usuarios.containsKey(novoNome)) {
                usuarioView.mostrarMensagem("Erro: Já existe um usuário com esse nome.");
                return;
            }

            usuarios.remove(usuarioLogado.getNome());
            usuarioLogado.setNome(novoNome);
            usuarios.put(novoNome, usuarioLogado);
            usuarioView.mostrarMensagem("Nome do usuário atualizado para: " + novoNome);
        } else {
            usuarioView.mostrarMensagem("Nenhum usuário logado.");
        }
    }

    public Usuario getUsuarioLogado() {
        if (usuarioAutenticado) {
            return usuarioLogado;
        }
        return null;
    }


    public boolean autenticarUsuario(String nome, String senha) {
        Usuario usuario = usuarios.get(nome);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }


    public boolean isUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
