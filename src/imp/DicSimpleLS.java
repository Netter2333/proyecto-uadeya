package imp;

import api.ConjuntoTDA;
import api.DiccionarioSimpleSTDA;

public class DicSimpleLS implements DiccionarioSimpleSTDA {

    class NodoClave {
        int clave;
        String valor;
        NodoClave sigClave;
    }
    NodoClave origen;

    @Override
    public void InicializarDiccionario() {
        origen = null;
    }

    @Override
    public void Agregar(int clave, String valor) {
        NodoClave nc = Clave2NodoClave(clave);
        if (nc == null) {       // la clave no existe
            nc = new NodoClave();
            nc.clave = clave;
            nc.sigClave = origen;
            origen = nc;    // nuevo origen
        }
        nc.valor = valor;

    }

    private NodoClave Clave2NodoClave (int clave) {
        NodoClave aux = origen;
        while (aux != null && aux.clave != clave) {
            aux = aux.sigClave;
        }
        return aux;
    }

    @Override
    public void Eliminar(int clave) {
        if (origen != null) {
            if (origen.clave == clave) {    // es el primero
                origen = origen.sigClave;
            } else {                        // es algun otro
                NodoClave aux = origen;     // el nodo viajero
                while (aux.sigClave != null && aux.sigClave.clave != clave) {
                    aux = aux.sigClave;
                }
                if (aux.sigClave != null) {
                    aux.sigClave = aux.sigClave.sigClave; // el siguiente pasa a ser el siguiente del eliminado
                }
            }
        }

    }

    @Override
    public String Recuperar(int clave) {
        NodoClave nc = Clave2NodoClave(clave);
        return nc.valor;
    }

    @Override
    public ConjuntoTDA Claves() {
        ConjuntoTDA c = new ConjuntoLD();
        c.InicializarConjunto();
        NodoClave aux = origen;
        while (aux != null) {
            c.Agregar(aux.clave);
            aux = aux.sigClave;
        }
        return c;
    }

}
