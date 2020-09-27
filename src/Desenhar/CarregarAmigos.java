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
public class CarregarAmigos {
    
    private JTable table;

    public CarregarAmigos(JTable table) {
        this.table = table;
    }
    
    public void AdicionarAmigosTela() {
        ControllerIntegracaoServidor servidor =  new ControllerIntegracaoServidor(Singleton.getInstance().getUsu(), Singleton.getInstance().getIpServidor());
        
        List<Usuario> usus = servidor.requestConsultaContatos().getListaUsu();
        String[] nomesColunas = {"Amigos"};
        List<String[]> lista = new ArrayList<>();

        for (int i = 0; i < usus.size(); i++) {
            lista.add(new String[]{usus.get(i).getApelido() + "(" + usus.get(i).getId() + ")"});
        }
        DefaultTableModel model = new DefaultTableModel(
        lista.toArray(new String[lista.size()][]), nomesColunas);

        table.setModel(model);
    }
}
