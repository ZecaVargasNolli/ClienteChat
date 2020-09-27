/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desenhar;

import Controller.ControllerIntegracaoServidor;
import Model.Usuario;
import Singleton.Singleton;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Vargas Nolli
 */
public class CarregarUsuario {
    
    private JTable table;
    private String Email;

    public CarregarUsuario(JTable table, String email) {
        this.table = table;
        this.Email = email;
    }
    
    public void AdicionarUsuarioTela() {
        //registro de amigos
        String[] nomesColunas = {"Usuários"};
        List<String[]> lista = new ArrayList<>();
        ControllerIntegracaoServidor servidor = new ControllerIntegracaoServidor(Singleton.getInstance().getUsu(), Singleton.getInstance().getIpServidor());
        Usuario usuarioBuscaEmail = new Usuario();
        usuarioBuscaEmail.setEmail(Email);
        List<Usuario> usus = servidor.requestConsultaUsuarioPorEmail(usuarioBuscaEmail).getListaUsu();
        for (int i = 0; i < usus.size(); i++) {
            lista.add(new String[]{usus.get(i).getApelido() + "(" + usus.get(i).getId() + ")"});
        }
        DefaultTableModel model = new DefaultTableModel(
        lista.toArray(new String[lista.size()][]), nomesColunas);

        table.setModel(model);
    }
}
