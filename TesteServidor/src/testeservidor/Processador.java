package testeservidor;

/**
 * Processador de requisicoes.
 * 
 * @author leonardo
 */
public abstract class Processador {

    public void processRequest(final Request req, final Response res) {
    }

    // <editor-fold desc="Clique para expandir"> 
    public void doGet(Request req, Response res) {
    }

    public void doPost(Request req, Response res) {
        processRequest(req, res);
    }
    // </editor-fold>

}
