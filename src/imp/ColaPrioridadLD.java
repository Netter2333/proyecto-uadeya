package imp;

import api.ColaPrioridadTDA;

public class ColaPrioridadLD implements ColaPrioridadTDA {

    class NodoPrioridad {
        int info;
        int prioridad;
        NodoPrioridad sig;
    }
    NodoPrioridad primero;

    @Override
    public void InicializarCola() {
        primero = null;
    }

    @Override
    public void AcolarPrioridad(int x, int prioridad) {
        NodoPrioridad nuevo = new NodoPrioridad();
        nuevo.info = x;
        nuevo.prioridad = prioridad;
        if (primero == null || primero.prioridad < prioridad) {
            nuevo.sig = primero;
            primero = nuevo;

        } else {
            NodoPrioridad aux = primero;    //buscamos la posicion
            while (aux.sig != null && aux.sig.prioridad > prioridad) {
                aux = aux.sig;
            }
            nuevo.sig = aux.sig;
            aux.sig = nuevo;
        }
    }

    @Override
    public void Desacolar() {
        primero = primero.sig;

    }

    @Override
    public boolean ColaVacia() {
        return (primero == null);
    }

    @Override
    public int Primero() {
        return primero.info;
    }

    @Override
    public int Prioridad() {
        return primero.prioridad;
    }

    @Override
    public void mostrarCola() {

    }

    @Override
    public int getLargo() {
        return 0;
    }
}
