/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desenhar;

import Model.ClienteRequest;
import Model.Texto;
import Singleton.Singleton;

/**
 *
 * @author José Vargas Nolli
 */
public class LeitorTexto implements Runnable {
    
    private ClienteRequest cliente;

    public LeitorTexto(ClienteRequest cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public void run() {
        Singleton.getInstance().getConversaCorreta(cliente.getUsarioOrigem().getId()).adicionarMensagem(new Texto(cliente.getUsarioOrigem().getApelido(), cliente.getMensageTexto()));
    }
    
}
