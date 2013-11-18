package testeservidor;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author leonardo
 */
public class Response {
    
    private PrintWriter pw;
    private OutputStream os;

    public Response(OutputStream os) {
        this.os = os;
        this.pw = new PrintWriter(os, true);
    }

    public PrintWriter getWriter() {
        return pw;
    }

    public OutputStream getOutputStream() {
        return os;
    }

}
