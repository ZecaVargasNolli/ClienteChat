/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author José Vargas Nolli
 */
public class ConversaTexto {
    
    private ArrayList<String> falas = new ArrayList<>();
    private Usuario usuarioConversando;

    public ArrayList<String> getFalas() {
        return falas;
    }

    public void setFalas(ArrayList<String> falas) {
        this.falas = falas;
    }
    
    public void adicionarMensagem(String texto) {
        this.falas.add(texto);
    }

    public Usuario getUsuarioConversando() {
        return usuarioConversando;
    }

    public void setUsuarioConversando(Usuario usuarioConversando) {
        this.usuarioConversando = usuarioConversando;
    }
    
    
    
}
