
import testeservidor.Processador;
import testeservidor.Request;
import testeservidor.Response;

/**
 *
 * @author leonardo
 */
public class Alterar extends Processador {

    @Override
    public void doGet(Request req, Response res) {
        res.getWriter().println("<h1>Alterar Pais</h1>");

        String acao = req.getParameter("acao");

        String id = req.getParameter("id");
        if (id == null) {
            res.getWriter().println("<h2>Informe o ID do pais !!!</h2>");
            return;
        }

        Pais pais = PaisDao.getInstance().carregar(Long.valueOf(id));
        
        if (acao == null) {

            res.getWriter().println("<form action='" + req.getContextPath() + "/Alterar' method='GET'>");
            res.getWriter().println("ID: " + pais.getId() + " <br/>");
            res.getWriter().println("NOME: <input type='text' name='nome' value='" + pais.getNome() + "'/> <br/>");
            res.getWriter().println("SIGLA: <input type='text' name='sigla' value='" + pais.getSigla() + "'/> <br/>");
            res.getWriter().println("<input type='hidden' name='id' value='" + pais.getId() + "'/> <br/>");
            res.getWriter().println("<input type='hidden' name='acao' value='confirmarAlteracao'/> <br/>");
            res.getWriter().println("<input type='submit' value='Confirmar'/> <br/>");
            res.getWriter().println("</form>");

            res.getWriter().println("<br/>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Listar'>Voltar para lista</a>");
            
        }
        else if (acao.equals("confirmarAlteracao")) {
            
            pais.setNome(req.getParameter("nome"));
            pais.setSigla(req.getParameter("sigla"));
            PaisDao.getInstance().salvarOuAtualizar(pais);
            
            res.getWriter().println("<h2>Pais id=" + pais.getId() + " atualizado com sucesso !<h2/>");
            res.getWriter().println("<br/>");
            res.getWriter().println("<a href='" + req.getContextPath() + "/Listar'>Voltar para lista</a>");
            
        }
        
    }
}
