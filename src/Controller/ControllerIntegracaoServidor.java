package Controller;

import Model.ServerRequest;
import Model.ServerResponse;
import Model.Usuario;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Giancarlo
 */
public class ControllerIntegracaoServidor {
    
    private Usuario usuario;
    private String ipServidor;

    public ControllerIntegracaoServidor(Usuario usuario, String ipServidor) {
        this.usuario = usuario;
        this.ipServidor = ipServidor;
    }
    
    private String getIpMaquinaAtual() {
        String ipAtual = "";
        try {
            ipAtual = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return ipAtual;
    }
    
    private ServerRequest criaServerRequest(int tipoRequest) {
        ServerRequest request = new ServerRequest();
        request.setUsuRequest(this.usuario);
        request.setIpAtual(this.getIpMaquinaAtual());
        request.setPortaAtual(this.usuario.getPortaAtual());
        request.setTipoRequest(tipoRequest);
        return request;
    }
    
    public Usuario requestAutenticaUsuario() {
        ServerRequest request = this.criaServerRequest(1); //autentica usuario
        Usuario usuario = this.doRequestServerCliente(request);
        return usuario;   
    }
    
    public ServerResponse requestAlteraUsuario() {
        ServerRequest request = this.criaServerRequest(2); //atualiza usuario
        ServerResponse resposta = this.doRequestServer(request);
        return resposta;
    }
    
    public ServerResponse requestInsereUsuario() {
        ServerRequest request = this.criaServerRequest(3); //Insere Usuario
        ServerResponse resposta = this.doRequestServer(request);
        return resposta;
    }
    
    public ServerResponse requestAddContato(Usuario usuAdd) {
        ServerRequest request = this.criaServerRequest(4); //adiciona contato
        request.setUsuAlteracao(usuAdd);
        ServerResponse resposta = this.doRequestServer(request);
        return resposta;
    }
    
    public ServerResponse requestConsultaContatos() {
        ServerRequest request = this.criaServerRequest(7); //consulta contatos do usuario
        ServerResponse resposta = this.doRequestServer(request);
        return resposta;
    }
    
    public ServerResponse requestConsultaContatosOnline() {
        ServerRequest request = this.criaServerRequest(8); //consulta contatos do usuario
        ServerResponse resposta = this.doRequestServer(request);
        return resposta;
    }
    
    public ServerResponse requestNotificaOnline() {
        ServerRequest request = this.criaServerRequest(9); //notificar online
        ServerResponse resposta = this.doRequestServer(request);
        return resposta;
    }

    public ServerResponse requestRemoveContato(Usuario usuarioRemover) {
        ServerRequest request = this.criaServerRequest(5); //remove contato do usuario
        request.setUsuAlteracao(usuarioRemover);
        ServerResponse resposta = this.doRequestServer(request);
        return resposta;
    }
        
    public ServerResponse requestConsultaUsuarioPorEmail(Usuario usuarioBuscaEmail) {
       ServerRequest request = this.criaServerRequest(6); //consulta usuario por email
       request.setUsuAlteracao(usuarioBuscaEmail); 
       ServerResponse resposta = this.doRequestServer(request);
       return resposta;
    }
        
    
    private Usuario doRequestServerCliente(ServerRequest requisicao) {
        Usuario resp = null;
        try {
            Socket conn = new Socket(this.ipServidor, 56000);
            OutputStream out = conn.getOutputStream();
            String strReq = this.converteParaFormatoEnviar(requisicao);
            out.write(strReq.getBytes());
            
            /* lendo o retorno do servidor */
            BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String strQtdCarcters = buffer.readLine().trim();
            int qtdCaracteres = Integer.parseInt(strQtdCarcters);
            char caracteresRequisicao[] =  new char[qtdCaracteres];

            buffer.read(caracteresRequisicao, 0, qtdCaracteres);
            String JsonRequisicao = String.valueOf(caracteresRequisicao);
            
            Gson gson = new Gson();
            resp = gson.fromJson(JsonRequisicao, Usuario.class);
        }
        catch (Exception ex) {
           ex.printStackTrace();
        }

        return resp;
    }
    
    private ServerResponse doRequestServer(ServerRequest requisicao) {
        ServerResponse resp = null;
        try {
            Socket conn = new Socket(this.ipServidor, 56000);
            OutputStream out = conn.getOutputStream();
            String strReq = this.converteParaFormatoEnviar(requisicao);
            out.write(strReq.getBytes());
            
            /* lendo o retorno do servidor */
            BufferedReader buffer = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String strQtdCarcters = buffer.readLine().trim();
            int qtdCaracteres = Integer.parseInt(strQtdCarcters);
            char caracteresRequisicao[] =  new char[qtdCaracteres];

            buffer.read(caracteresRequisicao, 0, qtdCaracteres);
            String JsonRequisicao = String.valueOf(caracteresRequisicao);
            resp = this.converteParaFormatoResposta(JsonRequisicao);
        }
        catch (Exception ex) {
           ex.printStackTrace();
        }

        return resp;
    }
    
    private ServerResponse converteParaFormatoResposta(String json) {
         Gson gson = new Gson();
         ServerResponse resp = gson.fromJson(json, ServerResponse.class);
         return resp;
    }
    
    private String converteParaFormatoEnviar(ServerRequest requisicao) {
        Gson gson = new Gson();
        String strJsonReq = gson.toJson(requisicao);
        String strReq = strJsonReq.length()+"\n"+strJsonReq;
        return strReq;
    }
    
}