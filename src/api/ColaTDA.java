package api;

public interface ColaTDA {
    void InicializarCola();

    void Desacolar();
    boolean ColaVacia();
    int Primero();
    void invertirCola();
	void Acolar(int x, int zona);
	int ZonaPrimero();
}
