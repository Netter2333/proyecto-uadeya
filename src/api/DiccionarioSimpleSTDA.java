package api;

public interface DiccionarioSimpleSTDA {
    void InicializarDiccionario();      // sin precondiciones
    void Agregar(int clave, String valor); // diccionario inicializado
    void Eliminar(int clave);           // diccionario inicializado          // diccionario inicializado y clave existente
    ConjuntoTDA Claves();               // diccionario inicializado
	String Recuperar(int clave);
}
