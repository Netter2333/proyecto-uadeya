package libreria;

import java.util.Random;

import api.ColaPrioridadTDA;
import imp.ColaPrioridadLD;


public class Gral {
	
	

	public static void mostrar(ColaPrioridadTDA cola) {
		if (cola.ColaVacia()) {
			System.out.println("La cola se encuentra vacia, imposible mostrarla.");
			return;
		}
		
		ColaPrioridadTDA aux = new ColaPrioridadLD();
		aux.InicializarCola();		
		copiar(cola, aux);
		System.out.print("Estado actual cola prioridad: ");
		while (!aux.ColaVacia()) {
			System.out.print("[ Valor: " + aux.Primero() + " - Prioridad: " + aux.Prioridad() + " ] / ");
			aux.Desacolar();
		}
		System.out.println();
	}


	public static void pasar(ColaPrioridadTDA cola, ColaPrioridadTDA c) {
		while (!cola.ColaVacia()) {
			c.AcolarPrioridad(cola.Primero(), cola.Prioridad());				
			cola.Desacolar();
		}
	}


	public static void copiar(ColaPrioridadTDA c1, ColaPrioridadTDA c2) {
		ColaPrioridadTDA aux = new ColaPrioridadLD();
		aux.InicializarCola();
		pasar(c1, aux);
		while(!aux.ColaVacia()) {
			c1.AcolarPrioridad(aux.Primero(), aux.Prioridad());
			c2.AcolarPrioridad(aux.Primero(), aux.Prioridad());
			aux.Desacolar();
		}
		
	}
	
}