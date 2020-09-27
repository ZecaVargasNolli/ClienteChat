/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desenhar;

import Controller.ControllerIntegracaoServidor;
import Singleton.Singleton;

/**
 *
 * @author José Vargas Nolli
 */
public class Online  extends Thread {
        
    public void EstouOnline() {
        ControllerIntegracaoServidor servidor = new ControllerIntegracaoServidor(Singleton.getInstance().getUsu(), Singleton.getInstance().getIpServidor());
        servidor.requestNotificaOnline();
    }

    @Override
    public void run() {
        while (true) {            
            this.EstouOnline();
        }
    }
    
    
    
    
    
    
}
