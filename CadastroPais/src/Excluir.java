
import java.net.URL;
import testeservidor.Processador;
import testeservidor.Request;
import testeservidor.Response;

/**
 *
 * @author leonardo
 */
public class Excluir extends Processador {

    @Override
    public void doGet(Request req, Response res) {
        res.getWriter().println("<h1>Excluir Pais</h1>");
        
        String acao = req.getParameter("acao");
        String id = req.getParameter("id");
        
        if (id == null) {
            res.getWriter().println("<h2>Informe o ID do pais !!!</h2>");
            return;
        }
        
        Pais pais = PaisDao.getInstance().carregar(Long.valueOf(id));
        
        if (pais == null) {
            res.getWriter().println("<h2>Pais inexistente !!!</h2>");
            return;
        }
        
        if (acao == null) {
            res.getWriter().println("Tem certeza que deseja excluir o pais de id=" + pais.getId() + " ?<br/>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Excluir?acao=confirmarExcluir&id=" + id + "'>Confirmar</a> <br/>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Listar'>Voltar para lista</a>");
            res.getWriter().println("<br/>");
        }
        else if (acao.equals("confirmarExcluir")) {
            PaisDao.getInstance().excluir(pais);
            res.getWriter().println("<h2>Pais de id=" + pais.getId() + " foi excluido com sucesso !<br/>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Listar'>Voltar para lista</a>");
            res.getWriter().println("<br/>");
        }        
        
    }
    
}
