
import java.util.List;
import testeservidor.Processador;
import testeservidor.Request;
import testeservidor.Response;

/**
 *
 * @author leonardo
 */
public class Listar extends Processador {

    @Override
    public void doGet(Request req, Response res) {
        res.getWriter().println("<h1>Lista de Paises</h1>");

        List<Pais> paises = PaisDao.getInstance().listar();

        res.getWriter().println("<a href='" + req.getContextPath() + "/Novo'>Cadastrar pais NOVO</a> <br/><br/>");
        
        res.getWriter().println("<table border=1 width=200>");
        res.getWriter().println("<tr>");
        res.getWriter().println("<td>ID</td>");
        res.getWriter().println("<td>NOME</td>");
        res.getWriter().println("<td>SIGLA</td>");
        res.getWriter().println("<td>OPCOES</td>");
        res.getWriter().println("</tr>");

        for (Pais pais : paises) {
            res.getWriter().println("<tr>");
            res.getWriter().println("<td>" + pais.getId() + "</td>");
            res.getWriter().println("<td>" + pais.getNome() + "</td>");
            res.getWriter().println("<td>" + pais.getSigla() + "</td>");
            res.getWriter().println("<td>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Consultar?id=" + pais.getId() + "'>Consultar</a>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Alterar?id=" + pais.getId() + "'>Alterar</a>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Excluir?id=" + pais.getId() + "'>Excluir</a>");
            res.getWriter().println("</td>");
            res.getWriter().println("</tr>");
        }
        res.getWriter().println("</table>");
        
        res.getWriter().println("<h3>" + req.getContextPath() + "</h3>");
    }
}
