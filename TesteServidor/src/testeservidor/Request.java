package testeservidor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author leonardo
 */
public class Request {
    
    // METODO XXX PARAMETROS
    // =============================
    // somar xxx?a=10&b=30
    // subtrair xxx?a=50&b=34
    
    private String metodo = "";
    private String pathInfo = "";
    private String contextPath = "";
    
    private BufferedReader br;
    private Map<String, String> parametros = new HashMap<String, String>();

    public Request(InputStream is) {
        this.br = new BufferedReader(new InputStreamReader(is));
    }
    
    public void processar() throws Exception {
        String linha = br.readLine();
        System.out.println("Servidor recebeu: " + linha);
        metodo = linha.split(" ")[0];
        pathInfo = linha.split(" ")[1];

        // Separa os parametros
        if (pathInfo.contains("?")) {
            pathInfo = pathInfo.split("\\?")[0];
            String[] parametrosProv = linha.split(" ")[1].split("\\?")[1].split("&");
            for (String parametroProv : parametrosProv) {
                parametros.put(parametroProv.split("=")[0], parametroProv.split("=")[1]);
            }
        }
        
        contextPath = pathInfo;
        pathInfo = "";
        
        int i = contextPath.indexOf("/", 2);
        if (i > 0) {
            pathInfo = contextPath.substring(i, contextPath.length());
            contextPath = contextPath.substring(0, i);
        }
        
    }

    public String getParameter(String nome) {
        String valor = parametros.get(nome);
        if (valor == null) return null;
        try {
            valor = URLDecoder.decode(valor, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
        return valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public String getContextPath() {
        return contextPath;
    }
    
}
