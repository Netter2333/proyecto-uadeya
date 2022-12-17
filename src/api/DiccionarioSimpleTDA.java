package api;

public interface DiccionarioSimpleTDA {
    void InicializarDiccionario();      // sin precondiciones
    void Agregar(int clave, int valor); // diccionario inicializado
    void Eliminar(int clave);           // diccionario inicializado
    int Recuperar(int clave);           // diccionario inicializado y clave existente
    ConjuntoTDA Claves();               // diccionario inicializado
}
