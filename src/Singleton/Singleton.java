/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Model.ConversaTexto;
import Model.Usuario;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author José Vargas Nolli
 */
public class Singleton {
    
    private static Singleton Instance;
    private Usuario usu;
    private List<Usuario> usus =   new ArrayList<>();
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

    public List<Usuario> getUsus() {
        return usus;
    }

    public void setUsus(List<Usuario> usus) {
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
    
    public Usuario getConversaUsuario(int id) {
        for(Usuario usu: this.usus) {
            if(usu.getId() == id) {
                return usu;
            }
        }
        return null;
    }
    
    public ConversaTexto getConversaCorreta(int idContato) {
        for (ConversaTexto conversa : this.conversa) {
            if(conversa.getUsuarioConversando().getId() == idContato) {
                return conversa;
            }
        }
        ConversaTexto nova = new ConversaTexto();
        Usuario usu = new Usuario();
        usu.setId(idContato);
        nova.setUsuarioConversando(usu);
        Singleton.getInstance().addConversa(nova);
        return nova;
    } 
}