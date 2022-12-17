package imp;

import api.AdministradorDeColasTDA;
import api.ColaPrioridadTDA;
import api.DiccionarioSimpleTDA;

public class AdministradorDeColas implements AdministradorDeColasTDA {
  
    class NodoPrioridad {
        int info; //info = numero de ticket creado cuando acolamos el pedido
        int prioridad;
        int delivery;
        NodoPrioridad sig;
    }
    NodoPrioridad primero;
    int vehiculosdisponibles; // Cant de vehiculos disponibles para delivery
    int vehiculostotales;
  
  @Override
	public void inicializar(int cantidad) {
		/* recibe cant de puestos de atencion
		*  debe crear e inicializar todas las referencias y objetos utilizados por el TDA
    */
      vehiculosdisponibles = cantidad;
      vehiculostotales = cantidad;
      primero = null;
	}

	@Override
	public void acolar(int idPedido, int demora, int delivery) {
		/* recibe ID del elemento y la demora
		* no retorna valor, simplemente acola.
		* debe validar que TDA esta inicializado
		* 
		* */
		if (vehiculosdisponibles > 0) 
		{
	      	NodoPrioridad nuevo = new NodoPrioridad();
	        nuevo.info = idPedido;
	        nuevo.prioridad = demora;
	        nuevo.delivery = delivery;
		        if (primero == null || primero.prioridad < demora) {
		            nuevo.sig = primero;
		            primero = nuevo;
		
		        } else {
		            NodoPrioridad aux = primero;    //buscamos la posicion
		            while (aux.sig != null && aux.sig.prioridad > demora) {
		                aux = aux.sig;
		            }
		            nuevo.sig = aux.sig;
		            aux.sig = nuevo;
		            }
		        vehiculosdisponibles--;
		}

	
	}

	@Override
	public int desacolar() {
		vehiculosdisponibles++;
		int id_ticket_desacolado = primero.info;
		primero = primero.sig;
		
		return id_ticket_desacolado;
	}

	@Override
	public int cantPuestos() {
		/* Debe devolver la cantidad de puestos con que se inicializo el administrador de colas prioridad
		 * supone que el TDA esta inicializado
		 */
		
		return vehiculostotales;
	}

	@Override
	public int proximo() {
		/* Debe devolver el id del proximo elemento a ser llamado
		 * supone que TDA no esta vacio
		 */
		return primero.sig.info;
	}

	@Override
	public int posicionXelemento(int idElemento) {
		/* Recibe un identificador de elemento
		 * Debe devolver la posicion en la cola del elemento
		 */
		int pos = 0;
		NodoPrioridad aux = primero;    //buscamos la posicion
        while (aux.sig != null && aux.sig.info != idElemento) {
        	aux = aux.sig;
        	pos++;
        	}
         
		
		return pos;
	}

	@Override
	public DiccionarioSimpleTDA elementos() {
		/* Debe devolver un dicc simple con los elementos encolados y la posicion en la cola
		 * Debe armar dinamicamente este dicc al momento de ejecutar el metodo
		 */
		DiccionarioSimpleTDA encolados = new DicSimpleL();
		encolados.InicializarDiccionario();
		
		int pos = 0;
		NodoPrioridad aux = primero;    //buscamos la posicion
        while (aux.sig != null) {
        	encolados.Agregar(aux.info, pos);
        	aux = aux.sig;
        	pos++;
        	}
         
	
		return encolados;
	}

	@Override
	public ColaPrioridadTDA programacion() {
		// Debe retornar una copia de la cola con prioridad
		ColaPrioridadTDA copia = new ColaPrioridadLD();
		copia.InicializarCola();
		
		NodoPrioridad aux = primero;    //buscamos la posicion
        
		while (aux.sig != null) {
        	copia.AcolarPrioridad(aux.info, aux.prioridad);
        	aux = aux.sig;
        	}
        copia.AcolarPrioridad(aux.info, aux.prioridad);
		return copia;
	}

	@Override
	public int Prioridad() {
		return primero.sig.prioridad; // devuelve la prioridad del siguiente elemento
	}



}