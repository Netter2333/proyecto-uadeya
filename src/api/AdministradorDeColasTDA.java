package api;

public interface AdministradorDeColasTDA {
	void inicializar( int cantidad);
	// siempre que el TDA esté inicializado 
  // siempre que el TDA esté inicializado
	int desacolar();
  // la cola no esta vacia
	int cantPuestos();
	// siempre que el TDA esté inicializado 
	int proximo();
	// la cola no esta vacia
	int posicionXelemento(int idElemento);
	// la cola no esta vacia
	DiccionarioSimpleTDA elementos();
	// siempre que el TDA esté inicializado y cola no vacías
	ColaPrioridadTDA programacion();
	// siempre que el TDA esté inicializado y cola no vacías
	int Prioridad(); // devuelve la prioridad
	// siempre que el TDA esté inicializado y cola no vacías
	void acolar(int idPedido, int demora, int delivery);

}