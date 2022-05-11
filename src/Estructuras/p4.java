package edd.src.Estructuras;

public class p4
{
    public static void main(String args[])
    {
	Lista<Integer> lista = new Lista<Integer>();
	lista.add(1);
	lista.add(2);
	lista.add(3);
	//lista.add(5);
	//lista.add(6);
	//lista.add(7);
	
	ArbolBinarioBusqueda<Integer> v1 = new ArbolBinarioBusqueda<Integer>(lista);
	ArbolAVL<Integer> v2 = new ArbolAVL<Integer>(lista);

        
	System.out.println(v2);
	v2.balancearAVL();
	System.out.println(v2);
	//System.out.println(v2.balanceDiff());
	//v2.add(8);
	//v2.add(9);
	//v2.add(10);
	// v2.add(11);
	//System.out.println(v2);
	//System.out.println(v2.balanceDiff());
    }
}
