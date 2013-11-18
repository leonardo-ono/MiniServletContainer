
import testeservidor.Processador;
import testeservidor.Request;
import testeservidor.Response;

/**
 *
 * @author leonardo
 */
public class Consultar extends Processador {

    @Override
    public void doGet(Request req, Response res) {
        res.getWriter().println("<h1>Consultar Pais</h1>");
        
        String id = req.getParameter("id");
        
        if (id == null) {
            res.getWriter().println("<h2>Informe o ID do pais !!!</h2>");
            return;
        }
        
        Pais pais = PaisDao.getInstance().carregar(Long.valueOf(id));
        
        if (pais == null) {
            res.getWriter().println("<h2>Pais inexistente!!!</h2>");
            return;
        }
        
        res.getWriter().println("ID: " + pais.getId() + " <br/>");
        res.getWriter().println("NOME: " + pais.getNome() + " <br/>");
        res.getWriter().println("SIGLA: " + pais.getSigla() + " <br/>");
        
        res.getWriter().println("<br/>");
        res.getWriter().println("<a href='" + req.getContextPath() + "/Listar'>Voltar para lista</a>");
    }
    
}
