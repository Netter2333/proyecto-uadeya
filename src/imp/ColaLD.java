package imp;

import api.ColaTDA;

public class ColaLD implements ColaTDA {

    class Nodo {
        int info;
        int zona;
        Nodo sig;
    }
    Nodo primero;
    Nodo ultimo;

    @Override
    public void InicializarCola() {
        primero = null;
        ultimo = null;

    }

    @Override
    public void Acolar(int x, int zona) {
        Nodo nuevo = new Nodo();
        nuevo.info = x;
        nuevo.zona = zona;
        nuevo.sig = null;
        if (ultimo != null) {   // cola no vacia
            ultimo.sig = nuevo;
        }
        ultimo = nuevo;
        if (primero == null) {  // la cola estaba vacia
            primero = ultimo;
        }

    }


    @Override
    public void Desacolar() {
        primero = primero.sig;
        if (primero == null) {  // la cola quedo vacia
            ultimo = null;
        }

    }

    @Override
    public boolean ColaVacia() {
        return (ultimo == null);
    }

    @Override
    public int Primero() {
        return primero.info;
    }

    @Override
    public void invertirCola() {

    }
    
    public int ZonaPrimero() {
    	return primero.zona;
    }
}
