package Controller;

import Model.ClienteRequest;
import Model.ServerRequest;
import Model.ServerResponse;
import Model.Usuario;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Giancarlo
 */
public class ControllerIntegracaoCliente {
    
    private String ipDestino;
    private int portaDestino;
    private Usuario usuarioOrigem;

    public ControllerIntegracaoCliente(String ipDestino, int portaDestino, Usuario usuarioOrigem) {
        this.ipDestino = ipDestino;
        this.portaDestino = portaDestino;
        this.usuarioOrigem = usuarioOrigem;
    }
    
    private String converteParaFormatoEnviar(ClienteRequest requisicao) {
        Gson gson = new Gson();
        String strJsonReq = gson.toJson(requisicao);
        String strReq = strJsonReq.length()+"\n"+strJsonReq;
        return strReq;
    }
    
    private void doRequestServer(ClienteRequest requisicao) {
        try {
            Socket conn = new Socket(this.ipDestino, this.portaDestino);
            OutputStream out = conn.getOutputStream();
            String strReq = this.converteParaFormatoEnviar(requisicao);
            out.write(strReq.getBytes());
        }
        catch (Exception ex) {
           ex.printStackTrace();
        }
    }
    
    public void enviaMsgTexto(String msg) {
      ClienteRequest req = new ClienteRequest();
      req.setMensagemTexto(true);
      req.setMensageTexto(msg);
      req.setUsarioOrigem(this.usuarioOrigem);
      this.doRequestServer(req);
    }
    
}