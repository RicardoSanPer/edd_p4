package edd.src.Estructuras;

public class p4
{
    public static void main(String args[])
    {
	Lista<Integer> lista = new Lista<Integer>();
	lista.add(2);
	lista.add(3);
	lista.add(4);
	lista.add(5);
	lista.add(6);
	lista.add(7);
	
	ArbolBinarioBusqueda<Integer> v1 = new ArbolBinarioBusqueda<Integer>(lista);
	ArbolAVL<Integer> v2 = new ArbolAVL<Integer>(lista);

        
	System.out.println(v2);
	v2.add(8);
	System.out.println(v2);
    }
}
