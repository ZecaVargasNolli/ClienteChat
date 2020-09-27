/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author José Vargas Nolli
 */
public class Texto {
    
    private String nomeUsuario;
    private String texto;

    public Texto(String nomeUsuario, String texto) {
        this.nomeUsuario = nomeUsuario;
        this.texto = texto;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String getMensagem() {
        return this.getTexto() + "<" + this.getNomeUsuario() + ">";
    }
}