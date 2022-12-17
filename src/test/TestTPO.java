package test;

import api.AdministradorDeColasTDA;
import api.ColaPrioridadTDA;
import api.ColaTDA;
import api.ConjuntoTDA;
import api.DiccionarioMultipleTDA;
import api.DiccionarioSimpleSTDA;
import api.DiccionarioSimpleTDA;
import imp.AdministradorDeColas;
import imp.ConjuntoLD;
import imp.DicMultipleL;
import imp.DicSimpleL;
import imp.DicSimpleLS;
import libreria.Gral;
import imp.ColaLD;
import imp.ColaPrioridadLD;

import java.util.Random;

public class TestTPO {

  public static void main(String[] args) {
    
	Random rand = new Random();
	int contadorPedidosCargados = 0;
	  
	ConjuntoTDA deliverysLibres = new ConjuntoLD();
    DiccionarioSimpleSTDA vehiculos = new DicSimpleLS();
    DiccionarioMultipleTDA pedidosXvehiculo = new DicMultipleL();
    DiccionarioSimpleSTDA clientes = new DicSimpleLS();
    DiccionarioSimpleTDA demoraClientes = new DicSimpleL();
    AdministradorDeColasTDA admin = new AdministradorDeColas();
    ColaTDA colaEspera = (ColaTDA) new ColaLD();
    DiccionarioSimpleTDA vehiculoAsignado = new DicSimpleL(); // clave idPedido, valor patente delivery
    DiccionarioSimpleTDA clientePedido = new DicSimpleL(); // clave idPedido, valor idCliente
    ColaPrioridadTDA adminCopia = new ColaPrioridadLD();

    deliverysLibres.InicializarConjunto();
    vehiculos.InicializarDiccionario();
    pedidosXvehiculo.InicializarDiccionario();
    clientes.InicializarDiccionario();
    demoraClientes.InicializarDiccionario();
    admin.inicializar(30);
    colaEspera.InicializarCola(); // Contiene idPedido

    cargarDatos(vehiculos, clientes, deliverysLibres);
    
    /// Empieza la carga de pedidos 
    ConjuntoTDA clientesXpedir = clientes.Claves();
    for (int i=0; i<20;i++){ // Agregamos 20 pedidos
    	int idClienteAPedir = clientesXpedir.Elegir();
    	int zona = rand.nextInt(4) + 1;
//    	System.out.println(contadorPedidosCargados);
    	cargarPedido(idClienteAPedir, zona, deliverysLibres, admin, pedidosXvehiculo, colaEspera, clientes, vehiculos, vehiculoAsignado, clientePedido);
    	contadorPedidosCargados++;

    	
    }
    
    while (contadorPedidosCargados > 15) {    // Entregamos 5 pedidos
    	
//    	System.out.println(contadorPedidosCargados);
    	entregarPedido(deliverysLibres, admin, pedidosXvehiculo, colaEspera, clientes, vehiculos, vehiculoAsignado, clientePedido);
    	contadorPedidosCargados--;

    }
    
    for (int i=0; i<20;i++){ // Agregamos 20 pedidos mas, los cuales algunos quedaran en la colaEspera por falta de vehiculos disponibles.
//    	System.out.println(contadorPedidosCargados);
    	int idClienteAPedir = clientesXpedir.Elegir();
    	int zona = rand.nextInt(4) + 1;
    	cargarPedido(idClienteAPedir, zona, deliverysLibres, admin, pedidosXvehiculo, colaEspera, clientes, vehiculos, vehiculoAsignado, clientePedido);
    	contadorPedidosCargados++;

    }
    
    
    while (contadorPedidosCargados > 0) {    // Terminamos de entregar todos los pedidos que fueron asignados y que estaban en cola de espera. 
    	
    	adminCopia = admin.programacion();

    	Gral.mostrar(adminCopia);
//    	System.out.println(contadorPedidosCargados);
    	entregarPedido(deliverysLibres, admin, pedidosXvehiculo, colaEspera, clientes, vehiculos, vehiculoAsignado, clientePedido);
    	contadorPedidosCargados--;

    }

    

  }

  public static void cargarDatos(DiccionarioSimpleSTDA vehiculos, DiccionarioSimpleSTDA clientes,
      ConjuntoTDA deliverysLibres) {

    // Procede a cargar los 30 vehiculos con patente y nombre del deliveryboy
    vehiculos.Agregar(1014, "Juan Martin");
    vehiculos.Agregar(1022, "Pedro Juan");
    vehiculos.Agregar(1036, "Martin Pedro");
    vehiculos.Agregar(1048, "Pino Pinnola");
    vehiculos.Agregar(1053, "Bruno Donalds");
    vehiculos.Agregar(1063, "Rodrigo Pingnola");
    vehiculos.Agregar(1075, "Mr Golden Golden");
    vehiculos.Agregar(1082, "Aitor Tilla");
    vehiculos.Agregar(1092, "Andrés Trozado");
    vehiculos.Agregar(1000, "Armando Bronca Segura");
    vehiculos.Agregar(1100, "Andrés Toto");
    vehiculos.Agregar(1200, "Juan Juanito");
    vehiculos.Agregar(1300, "Pepepe Pepito");
    vehiculos.Agregar(1400, "Alberto Milei");
    vehiculos.Agregar(1500, "Leo Messi");
    vehiculos.Agregar(1600, "Otamendi");
    vehiculos.Agregar(1700, "Cristiano Ronaldo");
    vehiculos.Agregar(1800, "Maradona");
    vehiculos.Agregar(1900, "Leo Kennedy");
    vehiculos.Agregar(2000, "Ashley Granja Bell");
    vehiculos.Agregar(2100, "Jill Valentino");
    vehiculos.Agregar(2200, "Alberto Wesker");
    vehiculos.Agregar(2300, "Clara Camporojo");
    vehiculos.Agregar(2400, "Cristiano Camporojo");
    vehiculos.Agregar(2500, "Shiva Alo");
    vehiculos.Agregar(2600, "Todd Howard");
    vehiculos.Agregar(2700, "Ada Wong");
    vehiculos.Agregar(2800, "Miyazaki");
    vehiculos.Agregar(2900, "Spencer");
    vehiculos.Agregar(3000, "William Birkin");

    // cargamos al conjunto de vehiculos libres todas las patentes, que luego seran
    // asignados a cada pedido
    ConjuntoTDA clavesVehiculos = vehiculos.Claves();
    while (!clavesVehiculos.ConjuntoVacio()) {
      int elem = clavesVehiculos.Elegir();
      deliverysLibres.Agregar(elem);
      clavesVehiculos.Sacar(elem);
    }

    // Procede a cargar los 40 clientes
    clientes.Agregar(14612002, "Maria Martinez");
    clientes.Agregar(48941200, "Martin Juan");
    clientes.Agregar(28941240, "Marta Martina");
    clientes.Agregar(38941220, "Lopez Martinez");
    clientes.Agregar(58941200, "Juan Velasquez");
    clientes.Agregar(11111100, "Lorena Lore");
    clientes.Agregar(12312312, "Dark Darker");
    clientes.Agregar(12944200, "Merry Christmas");
    clientes.Agregar(16948901, "Sin Nombre");
    clientes.Agregar(35961200, "Hideo Koshima");
    clientes.Agregar(28941200, "Martin Merino");
    clientes.Agregar(58941200, "Martin Martinez");
    clientes.Agregar(21941200, "Juan Martinez");
    clientes.Agregar(18947210, "Don Barredora");
    clientes.Agregar(21441250, "Marto Martinez");
    clientes.Agregar(17141220, "Bruno Bruno");
    clientes.Agregar(37241360, "Lara Martinez");
    clientes.Agregar(28241261, "Juana Juanita");
    clientes.Agregar(48541225, "Mark Zuck");
    clientes.Agregar(38941200, "Toto Toto");
    clientes.Agregar(40999067, "Martin molina");
    clientes.Agregar(30976067, "Franco Iniesta");
    clientes.Agregar(23499069, "Eduardo Vazquez");
    clientes.Agregar(48463221, "Leonel Amarello");
    clientes.Agregar(30559767, "Besubio Mebajo");
    clientes.Agregar(40999087, "Amanda Lopez");
    clientes.Agregar(41889017, "Lucila Aunim");
    clientes.Agregar(42399027, "Marcos Acuña");
    clientes.Agregar(42312067, "Julieta Musu");
    clientes.Agregar(44999057, "Camila lapal");
    clientes.Agregar(65899067, "Gonzalo Gonzalez");
    clientes.Agregar(10349007, "Esteban Banco");
    clientes.Agregar(20899097, "Julio Iglesias");
    clientes.Agregar(56759097, "Carlos Carlota");
    clientes.Agregar(48590671, "Paola Rubio");
    clientes.Agregar(32959067, "Juan Cruz Perez");
    clientes.Agregar(33929387, "Ailen luas");
    clientes.Agregar(41969067, "Rodrigo Saas");
    clientes.Agregar(21999197, "Lucas Castel");
    clientes.Agregar(19999067, "Bruno Kupa");

    
  }

  public static void cargarPedido(int idCliente, int zona, ConjuntoTDA deliverysLibres,
      AdministradorDeColasTDA admin, DiccionarioMultipleTDA pedidosXvehiculo, ColaTDA colaEspera, DiccionarioSimpleSTDA clientes, DiccionarioSimpleSTDA vehiculos, 
      DiccionarioSimpleTDA vehiculoAsignado,DiccionarioSimpleTDA clientePedido) {
    
    int idPedido = (int) Math.floor(Math.random() * (9999 - 1000 + 1) + 1000); // generamos numero de ticket del pedido
    clientePedido.Agregar(idPedido, idCliente);
    
    // Calculamos si hay vehiculos disponibles, sino pasara a la cola de espera
    // hasta que se libere un vehiculo (cuando un pedido sea entregado)
    if (!deliverysLibres.ConjuntoVacio()) {
    	int demora = calcularDemora(zona);
    	int deliveryAsignado = deliverysLibres.Elegir(); // al haber vehiculo disponible, se lo asigna al pedido creado (con la patente)
    	admin.acolar(idPedido, demora, deliveryAsignado);
    	deliverysLibres.Sacar(deliveryAsignado); // lo saco del conjunto ya que fue asignado a un pedido y ya no se
                                               // encuentra liberad
    	pedidosXvehiculo.Agregar(deliveryAsignado, idPedido);
    	vehiculoAsignado.Agregar(idPedido, deliveryAsignado);
    	System.out.println("Pedido asignado para cliente: " + idCliente);
    	System.out.println("Numero de pedido: " + idPedido);
    	System.out.println("Demora aproximada: " + demora*-1);
    	System.out.println("El nombre de su repartidor es: " + vehiculos.Recuperar(deliveryAsignado));
    	System.out.println("-------------------------------------------------------------------------");

    }
    
    else {
    	// En caso de que no haya vehiculos disponibles se lo guarda en una cola de espera hasta que se libere un vehiculo (al entregar otro pedido).
    	colaEspera.Acolar(idPedido, zona);
    	System.out.println(clientes.Recuperar(idCliente) + ", en unos momentos le estaremos asignando un repartidor a su pedido");
    	System.out.println("-------------------------------------------------------------------------");
    }

  }
  
  public static void entregarPedido(ConjuntoTDA deliverysLibres,
	      AdministradorDeColasTDA admin, DiccionarioMultipleTDA pedidosXvehiculo, ColaTDA colaEspera, DiccionarioSimpleSTDA clientes, DiccionarioSimpleSTDA vehiculos, 
	      DiccionarioSimpleTDA vehiculoAsignado, DiccionarioSimpleTDA clientePedido) {
	  int idPedido = admin.desacolar();
	  System.out.println("Pedido " + idPedido + " ha sido entregado.");
	  System.out.println("-------------------------------------------------------------------------");
	  deliverysLibres.Agregar(vehiculoAsignado.Recuperar(idPedido));
	  
	  if (!colaEspera.ColaVacia()) {
		  // Nos fijamos si hay pedidos pendiente de asignacion en la colaEspera.
		  idPedido = colaEspera.Primero();
		  int zona = colaEspera.ZonaPrimero();
		  int idCliente = clientePedido.Recuperar(idPedido);
		  int demora = calcularDemora(zona);
		  int deliveryAsignado = deliverysLibres.Elegir(); // al haber vehiculo disponible, se lo asigna al pedido creado (con la patente)
		  admin.acolar(idPedido, demora, deliveryAsignado);
		  deliverysLibres.Sacar(deliveryAsignado); // lo saco del conjunto ya que fue asignado a un pedido y ya no se encuentra liberado
	                                               
		  pedidosXvehiculo.Agregar(deliveryAsignado, idPedido); // registramos que el vehiculo x se encargo del idPedido
		  vehiculoAsignado.Agregar(idPedido, deliveryAsignado); // registramos que el 
		  System.out.println("Pedido asignado para cliente: " + idCliente);
		  System.out.println("Numero de pedido: " + idPedido);
		  System.out.println("Demora aproximada: " + demora*-1);
		  System.out.println("El nombre de su repartidor es: " + vehiculos.Recuperar(deliveryAsignado));
		  System.out.println("-------------------------------------------------------------------------");
		  
		  
		  
		  colaEspera.Desacolar();
	  }
	  

  }

  public static int calcularDemora(int zona) {
    if (zona == 1) {
      return -30;
    } else if (zona == 2) {
      return -20;
    } else if (zona == 3) {
      return -10;
    } else if (zona == 4) {
      return -5;
    }
    else {
    	return 0;
    }

  }

}
