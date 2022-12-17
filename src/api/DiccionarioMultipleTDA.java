package api;

public interface DiccionarioMultipleTDA {
    void InicializarDiccionario(); // sin precondiciones
    void Agregar(int clave, int valor); // dicc inicializado
    void Eliminar(int clave); // dicc inicializado
    void EliminarValor(int clave, int valor); // dicc inicializado
    ConjuntoTDA Recuperar(int clave); // dicc inicializado
    ConjuntoTDA Claves(); // dicc inicializado
}
