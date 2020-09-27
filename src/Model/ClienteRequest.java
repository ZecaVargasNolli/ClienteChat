package Model;

/**
 *
 * @author Giancarlo
 */
public class ClienteRequest {
    
    private Usuario usarioOrigem;
    private String mensageTexto;
    private boolean ehMsgTexto;
    private boolean continuaLerArquivo; // usado somente no arquivo
    private byte[] bytesDoArquivo; // aki vai o arquivo

    public Usuario getUsarioOrigem() {
        return usarioOrigem;
    }

    public void setUsarioOrigem(Usuario usarioOrigem) {
        this.usarioOrigem = usarioOrigem;
    }

    public String getMensageTexto() {
        return mensageTexto;
    }

    public void setMensageTexto(String mensageTexto) {
        this.mensageTexto = mensageTexto;
    }

    public boolean isMensagemTexto() {
        return ehMsgTexto;
    }

    public void setMensagemTexto(boolean mensagemTexto) {
        this.ehMsgTexto = mensagemTexto;
    }

    public boolean isContinuaLerArquivo() {
        return continuaLerArquivo;
    }

    public void setContinuaLerArquivo(boolean continuaLerArquivo) {
        this.continuaLerArquivo = continuaLerArquivo;
    }

    public byte[] getBytesDoArquivo() {
        return bytesDoArquivo;
    }

    public void setBytesDoArquivo(byte[] bytesDoArquivo) {
        this.bytesDoArquivo = bytesDoArquivo;
    }
}