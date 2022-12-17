package imp;

import api.ConjuntoTDA;

public class ConjuntoLD implements ConjuntoTDA {

    class Nodo {    // la celula de la estructura
        int info;
        Nodo sig;
    }
    Nodo c;         // la referencia de la estructura

    @Override
    public void InicializarConjunto() {
        c = null;
    }

    @Override
    public void Agregar(int x) {
        if (!this.Pertenece(x)) {   // se verifica la pertenencia
            Nodo nuevo = new Nodo();    // el nuevo nodo que se agregara
            nuevo.info = x;
            nuevo.sig = c;
            c = nuevo;
        }
    }

    @Override
    public int Elegir() {
        return c.info;
    }

    @Override
    public boolean ConjuntoVacio() {
        return (c == null);
    }

    @Override
    public void Sacar(int x) {
        if (c != null) {
            if (c.info == x) {  // es el primero
                c = c.sig;
            } else {        // es algun otro, lo buscamos
                Nodo aux = c;
                while (aux.sig != null && aux.sig.info != x) {
                    aux = aux.sig;
                }
                if (aux.sig != null) {  // encontrado
                    aux.sig = aux.sig.sig;
                }
            }
        }

    }

    @Override
    public boolean Pertenece(int x) {
        Nodo aux = c;
        while (aux != null && aux.info != x) {
            aux = aux.sig;
        }
        return (aux != null);
    }
}
