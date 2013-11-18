
import testeservidor.Processador;
import testeservidor.Request;
import testeservidor.Response;

/**
 *
 * @author leonardo
 */
public class Novo extends Processador {

    @Override
    public void doGet(Request req, Response res) {
        res.getWriter().println("<h1>Novo Pais</h1>");
        
        String acao = req.getParameter("acao");

        if (acao == null) {

            res.getWriter().println("<form action='" + req.getContextPath() + "/Novo' method='GET'>");
            res.getWriter().println("ID: --- <br/>");
            res.getWriter().println("NOME: <input type='text' name='nome' value=''/> <br/>");
            res.getWriter().println("SIGLA: <input type='text' name='sigla' value=''/> <br/>");
            res.getWriter().println("<input type='hidden' name='acao' value='confirmarNovo'/> <br/>");
            res.getWriter().println("<input type='submit' value='Confirmar'/> <br/>");
            res.getWriter().println("</form>");

            res.getWriter().println("<br/>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Listar'>Voltar para lista</a>");
            
        }
        else if (acao.equals("confirmarNovo")) {
            
            Pais pais = new Pais(System.currentTimeMillis(), req.getParameter("nome"), req.getParameter("sigla"));
            PaisDao.getInstance().salvarOuAtualizar(pais);
            
            res.getWriter().println("<h2>Pais id=" + pais.getId() + " salvo com sucesso !<h2/>");
            res.getWriter().println("<br/>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Listar'>Voltar para lista</a>");
            
        }
        
    }
    
}
