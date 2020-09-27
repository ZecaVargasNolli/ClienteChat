/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Desenhar.LeitorTexto;
import Model.ClienteRequest;
import Model.ServerRequest;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Giancarlo
 */
public class RunnableRecebeClieteRequest implements Runnable {

    private int porta;

    public RunnableRecebeClieteRequest(int porta) {
        this.porta = porta;
    }

    private ClienteRequest getRequest(Socket conexao) {
        ClienteRequest request = null;
        Gson gson = new Gson();
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

            String strQtdCarcters = buffer.readLine().trim();
            int qtdCaracteres = Integer.parseInt(strQtdCarcters);
            char caracteresRequisicao[] = new char[qtdCaracteres];

            buffer.read(caracteresRequisicao, 0, qtdCaracteres);
            String JsonRequisicao = String.valueOf(caracteresRequisicao);
            request = gson.fromJson(JsonRequisicao, ClienteRequest.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request;
    }

    private void fechaConexao(Socket conexao) {
        try {
            conexao.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(this.porta);
            server.setReuseAddress(true);
            while (true) {
                try {
                    Socket conn = server.accept();
                    ClienteRequest requisicao = this.getRequest(conn);
                    if (requisicao.isMensagemTexto()) {
                        Thread thTrataMsgTexto = new Thread(new LeitorTexto(requisicao));// implementar runnable para setar as mensagens
                        thTrataMsgTexto.start();
                    } else {
                        Thread thTrataMsgArquivo = new Thread();// implementar runnable para tratar o arquivo
                        thTrataMsgArquivo.start();
                    }
                    this.fechaConexao(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
