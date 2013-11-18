
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */
public class PaisDao {

    Map<Long, Pais> paises = new HashMap<Long, Pais>();
    private static PaisDao paisDao = new PaisDao();

    private PaisDao() {
        paises.put(1L, new Pais(1, "BRASIL", "BR"));
        paises.put(2L, new Pais(2, "JAPAO", "JP"));
    }
    
    public void salvarOuAtualizar(Pais pais) {
        paises.put(pais.getId(), pais);
    }
    
    public void excluir(Pais pais) {
        paises.remove(pais.getId());
    }
    
    public Pais carregar(long id) {
        return paises.get(id);
    }
    
    public List<Pais> listar() {
        return Arrays.asList(paises.values().toArray(new Pais[0]));
    }

    public static PaisDao getInstance() {
        return paisDao;
    }
    
}
