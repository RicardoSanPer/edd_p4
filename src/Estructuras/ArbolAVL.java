package edd.src.Estructuras;

public class ArbolAVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T>
{
    public class VerticeAVL extends Vertice
    {
	/**Contructor por parametro*/
	public VerticeAVL(T elemento)
	{
	    super(elemento);
	}
	
	/**Metodo toString
	 *@return string con el elemento
	 */
	@Override
	public String toString()
	{
	    if(this==null)
	    {
		return "";
	    }
	    return elemento.toString() + " H: " +  String.valueOf(this.altura());
	}

	/**Compara el vertice a otro objeto
	 */
	public boolean equals(Object o)
	{
	    if(o==null || getClass() !=o.getClass())
	    {
		return false;
	    }
	    @SuppressWarnings("unchecked")
	    VerticeAVL vertice = (VerticeAVL) o;

	    //Compara el elemento del vertice
	    if(!this.elemento.equals(vertice.elemento))
	    {
		return false;
	    }

	    //Si ambos tienen hijo o si ambos no tienen hijo derecho
	    if(this.hayDerecho() == vertice.hayDerecho())
	    {
		//Si no es nulo el hijo derecho, comparar si son elementos de distinto valor
		if(this.derecho != null && this.derecho.elemento != vertice.derecho.elemento)
		{
		    return false;
		}
	    }
	    //Si ambos tienen hijo o si ambos no tienen hijo derecho
	    if(this.hayIzquierdo() == vertice.hayIzquierdo())
	    {
		//Si no es nulo el hijo derecho, comparar si son elementos de distinto valor
		if(this.izquierdo != null && this.izquierdo.elemento != vertice.izquierdo.elemento)
		{
		    return false;
		}
	    }

	    return true; 
	}

	/** Regresa la altura del vertice
	 *@return altura del vertice
	 */
	public int altura()
	{
	    int alturaI = 0;
	    int alturaD = 0;

	    if(hayIzquierdo())
	    {
		alturaI = izquierdo.altura();
	    }
	    if(hayDerecho())
	    {
		alturaD = derecho.altura();
	    }
	    int max = (alturaI > alturaD)? alturaI : alturaD;
	    return 1 + max;
	}
	
    }

    public ArbolAVL()
    {
	super();
    }

    /**Construye el arbol AVL recibiendo una colección, ordenandola y construyendo el BST correspondiente
     *@param coleccion - Coleccion de elementos a ordenad
     */
    public ArbolAVL(Collection<T> coleccion)
    {
	//super(coleccion);
	super();
	Lista<T> lista = new Lista<T>();
	for(T elemento : coleccion)
	    {
		lista.add(elemento);
	    }
	buildUnsorted(lista);
	//System.out.println("constructor");
	
    }

    /**Agrega un elemento de forma ordenada
     *@param elemento - elemento a agrgar
     */
    @Override
    public void add(T elemento)
    {
        insert(elemento);
    }

    /**Busca un elemento en el arbol, y si lo encuentra, regresa el vértice que lo contiene
     *@param elemento - elemento a buscar
     *@return vertice que contiene al elemento
     */
    private Vertice getVertice(T elemento)
    {
	if(elemento == null)
	{
	    return null;
	}
	Vertice temp = raiz;
	while(temp.hayDerecho() || temp.hayIzquierdo())
	{
	    //regresa el nodo actual si es el nodo que contiene al elemento
	    if(temp.elemento == elemento)
	    {
		return temp;
	    }
	    //buscar en el subarbol derecho
	    else if(elemento.compareTo(temp.elemento)>0 && temp.derecho != null)
	    {
		temp = temp.derecho;
	    }
	    //busca en el subarbol iazquierdo
	    else if(temp.izquierdo != null)
	    {
		temp = temp.izquierdo;
	    }
	}

	//Si no se encontró el elemento
	return null;
    }

}
