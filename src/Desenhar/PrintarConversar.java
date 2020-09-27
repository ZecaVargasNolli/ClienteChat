/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Desenhar;

import Model.ConversaTexto;
import Model.Texto;
import Singleton.Singleton;
import javax.swing.JTextArea;

/**
 *
 * @author José Vargas Nolli
 */
public class PrintarConversar extends Thread {

    private JTextArea textArea;
    private Boolean executa;
    private int id;

    public PrintarConversar(JTextArea textArea, Boolean executa, int id) {
        this.textArea = textArea;
        this.executa = executa;
        this.id = id;
    }

    public void pararExecucao() {
        this.executa = false;
    }

    /**
     * {@inheritdoc}
     */
    public void run() {
        while (executa) {
            ConversaTexto conversa = Singleton.getInstance().getConversaCorreta(this.id);
            String texto = "";
            for (Texto mensagem : conversa.getFalas()) {
                texto += mensagem.getMensagem() + "\n";
            }

            textArea.setText(texto);
            try {
                this.sleep(100);
            }
            catch (InterruptedException ex) {
            }
        }
    }

}
