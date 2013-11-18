package testeservidor;

import java.io.File;
import java.io.FileFilter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author leonardo
 */
public class TesteServidor {

    public static Map<String, Processador> processadores = new HashMap<String, Processador>();
    private static Redeploy redeploy = new Redeploy();

    public static void deploy() throws Exception {
        File f = new File("c:/webapp");

        final List<URL> urls = new ArrayList<URL>();

        File[] diretorios = f.listFiles(new FileFilter() {

            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    try {
                        ClassLoader cl2 = new URLClassLoader(new URL[] { pathname.toURI().toURL() });
                        urls.add(pathname.toURI().toURL());
                        System.out.println(pathname.getName());
                    } catch (Exception e) {
                    }
                }
                return pathname.isDirectory();
            }
        });

        ClassLoader cl = new URLClassLoader(urls.toArray(new URL[0]));

        processadores.clear();

        for (File d : diretorios) {
            for (String s : d.list()) {
                if (!s.endsWith(".class")) {
                    continue;
                }
                String contextPath = d.getName();
                System.out.println("D=" + contextPath + " / S=" + s);
                String nomeDaClasse = s.replace(".class", "");
                //Class p = cl.loadClass(nomeDaClasse);
                Class p = cl.loadClass(nomeDaClasse);

                if (p.getSuperclass().getSimpleName().equals("Processador")) {
                    Processador proc = (Processador) p.newInstance();
                    String urlPattern = "/" + contextPath + "/" + nomeDaClasse;
                    System.out.println("Url Pattern = " + urlPattern);
                    processadores.put(urlPattern, proc);
                }
            }
        }

        processadores.put("/redeploy", redeploy);
    }

    public static void main(String[] args) throws Exception {

        deploy();

        while (true) {
        ServerSocket ss = new ServerSocket(8000);
        Socket s = ss.accept();
        
        Request req = new Request(s.getInputStream());
        Response res = new Response(s.getOutputStream());
        
        req.processar();
        
        Processador proc = processadores.get(req.getContextPath() + req.getPathInfo());
        
        if (proc == null) {
            res.getWriter().println("HTTP/1.1 404 ERRO");
            res.getWriter().println("Content-Type: text/html");
            res.getWriter().println("");
            res.getWriter().println("<h1>Recurso nao encontrado !</h1>");
            
        } else if (req.getMetodo().equals("GET")) {
            res.getWriter().println("HTTP/1.1");
            res.getWriter().println("Content-Type: text/html");
            res.getWriter().println("");
            proc.doGet(req, res);
            
        } else if (req.getMetodo().equals("POST")) {
            proc.doPost(req, res);
        } 
        
        s.close();
        ss.close();
        }
        
    }
}
