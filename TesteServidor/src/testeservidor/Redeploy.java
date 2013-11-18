/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testeservidor;

import java.util.Date;

/**
 *
 * @author leonardo
 */
public class Redeploy extends Processador {

    @Override
    public void doGet(Request req, Response res) {
        String acao = req.getParameter("acao");
        
        if (acao != null && acao.equals("pararServidor")) {
            res.getWriter().println("<h1>Servidor finalizado com sucesso !</h1>");
            res.getWriter().close();
            System.exit(0);
        }
        
        try {
            TesteServidor.deploy();
            res.getWriter().println("<h1>Redeploy realizado com sucesso as " + new Date() + ".</h1>");
            res.getWriter().println("Componentes implantados: <br/>");
            
            res.getWriter().println("<ul>");
            for (String s : TesteServidor.processadores.keySet()) {
                res.getWriter().println("<li>" + s + "</li>");
            }
            res.getWriter().println("</ul>");
            
        } catch (Exception ex) {
            res.getWriter().println("<h1>Erro ao fazer Redeploy !</h1>");
            res.getWriter().println("<h2>" + ex.getMessage() + "</h2>");
        }
        
        res.getWriter().println("<a href='/redeploy'>Redeploy</a> <br/>");
        res.getWriter().println("<a href='/redeploy?acao=pararServidor'>Parar servidor</a> <br/>");
        
    }
    
}
