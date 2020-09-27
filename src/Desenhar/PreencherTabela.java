/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desenhar;

import Controller.ControllerIntegracaoServidor;
import Model.Usuario;
import Singleton.Singleton;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author José Vargas Nolli
 */
public class PreencherTabela extends Thread {

    private JTable table;
    private Boolean execucao;

    public PreencherTabela(JTable table, Boolean execucao) {
        this.table = table;
        this.execucao = execucao;
    }

    public void pararExecucao() {
        this.execucao = false;
    }

    @Override
    public void run() {
        //chamar método para buscar os dados da tela.
        while (execucao) {
            String[] nomesColunas = {"Amigo Online"};
            List<String[]> lista = new ArrayList<>();
            ControllerIntegracaoServidor servidor = new ControllerIntegracaoServidor(Singleton.getInstance().getUsu(), Singleton.getInstance().getIpServidor());
            List<Usuario> usuario = servidor.requestConsultaContatosOnline().getListaUsu();
            Singleton.getInstance().setUsus(usuario);
            for (int i = 0; i < usuario.size(); i++) {
                lista.add(new String[]{usuario.get(i).getApelido() + "(" + usuario.get(i).getId() + ")"});
            }
            DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), nomesColunas);
            table.setModel(model);
            try {
                this.sleep(15000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
