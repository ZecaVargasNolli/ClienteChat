/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Model.ConversaTexto;
import Model.Usuario;
import java.util.ArrayList;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author José Vargas Nolli
 */
public class Singleton {
    
    private static Singleton Instance;
    private Usuario usu;
    private ArrayList<Usuario> usus =   new ArrayList<>();
    private ArrayList<ConversaTexto> conversa = new ArrayList<>();
    private String ipServidor;

    public Singleton() {
        Instance = this;
    }
    
    public static Singleton getInstance() {
        if(Instance == null) {
            Instance = new Singleton();
        }
        return Instance;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }

    public ArrayList<Usuario> getUsus() {
        return usus;
    }

    public void setUsus(ArrayList<Usuario> usus) {
        this.usus = usus;
    }
    
    public void setContato(Usuario usuario) {
        this.usus.add(usuario);
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public ArrayList<ConversaTexto> getConversa() {
        return conversa;
    }

    public void setConversa(ArrayList<ConversaTexto> conversa) {
        this.conversa = conversa;
    }
    
    public void addConversa(ConversaTexto conversa) {
        this.conversa.add(conversa);
    }
    
    public ConversaTexto getConversaCorreta(int idContato) {
        for (ConversaTexto conversa : this.conversa) {
            if(conversa.getUsuarioConversando().getId() == idContato) {
                return conversa;
            }
        }
        
        return new ConversaTexto();
    } 
}