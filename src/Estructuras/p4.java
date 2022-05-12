package edd.src.Estructuras;

import edd.src.Estructuras.Utilidad;

public class p4
{
    private static ArbolAVL<Integer> arbol;
    private static Lista<Integer> lista;

    private static int seleccion = 0;
    
    public static void main(String args[])
    {
	lista = new Lista<Integer>();
	lista.add(1);
	lista.add(2);
	lista.add(3);
	lista.add(4);
	lista.add(5);

	arbol = new ArbolAVL<Integer>(lista);

	mainMenu();
    }

    private static void mainMenu()
    {
	while(true)
	{
	    System.out.println("========== Practica 4 ==========");
	    System.out.println(arbol);
	    //System.out.println("Diferencia: " + arbol.balanceDiff());
	    System.out.println(arbol.toIO());
	    System.out.println("  1. Crear nuevo arbol");
	    System.out.println("  2. Agregar elemento");
	    System.out.println("  3. Eliminar elemento");
	    System.out.println("  4. Salir");

	    System.out.println("Ingresa una opcion");
	    seleccion = Utilidad.getRange(1,4);
	    
	    switch(seleccion)
	    {
	    case 1:
		crearNuevo();
		break;
	    case 2:
		System.out.println("Ingresa el elemento a agregar:");
		arbol.add(Utilidad.getUserInt());
		break;
	    case 3:
		System.out.println("Ingresa el elemento a eliminar:");
	        arbol.delete(Utilidad.getUserInt());
		break;
	    case 4:
		return;
	    default:
		break;
	    }
	}
    }

    private static void crearNuevo()
    {
	System.out.println("Esto eliminará el arbol existente. ¿Deseas continuar?");
	if(!Utilidad.getUserBool())
	{
	    return;
	}
	lista.empty();
	System.out.println("Ingresa el numero de elementos en el arbol:");
	int n = Utilidad.getPositive();
	for(int i = 0; i < n; i++)
	{
	    System.out.println("Ingresa un entero:");
	    lista.add(Utilidad.getUserInt());
	}
	arbol.construirAVL(lista);
    }
}
