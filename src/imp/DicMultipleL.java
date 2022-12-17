package imp;

import api.ConjuntoTDA;
import api.DiccionarioMultipleTDA;

public class DicMultipleL implements DiccionarioMultipleTDA {

    class NodoClave {
        int clave;
        NodoValor valores;
        NodoClave sigClave;
    }

    class NodoValor {
        int valor;
        NodoValor sigValor;
    }
    NodoClave origen;

    @Override
    public void InicializarDiccionario() {
        origen = null;
    }

    private NodoClave Clave2NodoClave (int clave) {
        NodoClave aux = origen;     // el nodo viajero
        while (aux != null && aux.clave != clave) {
            aux = aux.sigClave;
        }
        return aux;
    }


    @Override
    public void Agregar(int clave, int valor) {
        NodoClave nc = Clave2NodoClave(clave);
        if (nc == null) {       // la clave no existe
            nc = new NodoClave();
            nc.clave = clave;
            nc.sigClave = origen;
            origen = nc;
        }
        NodoValor aux = nc.valores;
        while (aux != null && aux.valor != valor) {
            aux = aux.sigValor;
        }
        if (aux == null) {      // el valor no existe
            NodoValor nv = new NodoValor();
            nv.valor = valor;
            nv.sigValor = nc.valores;
            nc.valores = nv;        // nuevo origen de valores
        }

    }

    @Override
    public void Eliminar(int clave) {
        if (origen != null) {
            if (origen.clave == clave) {    // es el primero
                origen = origen.sigClave;
            } else {                        // es algun otro
                NodoClave aux = origen;
                while (aux.sigClave != null && aux.sigClave.clave != clave) {
                    aux = aux.sigClave;
                }
                if (aux.sigClave != null) {
                    aux.sigClave = aux.sigClave.sigClave;
                }
            }
        }

    }

    private void EliminarValorEnNodo (NodoClave nodo, int valor) {
        if (nodo.valores != null) {
            if (nodo.valores.valor == valor) {  // es el primero
                nodo.valores = nodo.valores.sigValor;
            } else {
                NodoValor aux = nodo.valores;
                while (aux.sigValor != null && aux.sigValor.valor != valor) {
                    aux = aux.sigValor;
                }
                if (aux.sigValor != null) {
                    aux.sigValor = aux.sigValor.sigValor;
                }
            }
        }
    }

    @Override
    public void EliminarValor(int clave, int valor) {
        if (origen != null) {
            if (origen.clave == clave) {        // es en el primero
                EliminarValorEnNodo(origen, valor);
                if (origen.valores == null) {   // quedo vacio
                    origen = origen.sigClave;
                }
            }
            else {                              // es en algun otro
                NodoClave aux = origen;
                while (aux.sigClave != null && aux.sigClave.clave != clave) {
                    aux = aux.sigClave;
                }
                if (aux.sigClave != null) {
                    EliminarValorEnNodo(aux.sigClave, valor);
                    if (aux.sigClave.valores == null) {     // quedo vacio
                        aux.sigClave = aux.sigClave.sigClave;
                    }
                }
            }
        }

    }

    @Override
    public ConjuntoTDA Recuperar(int clave) {
        NodoClave nc = Clave2NodoClave(clave);
        ConjuntoTDA c = new ConjuntoLD();
        c.InicializarConjunto();
        if (nc != null) {
            NodoValor aux = nc.valores;
            while (aux != null) {
                c.Agregar(aux.valor);
                aux = aux.sigValor;
            }
        }
        return c;
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
