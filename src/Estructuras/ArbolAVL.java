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
	 *@param o - Objeto a comparar
	 *@return boolean indicando si son equivalentes
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
	//super();
	for(T elemento : coleccion)
	    {
		add(elemento);
	    }	
	//System.out.println("constructor");
	
    }
    

 /**
     * Construye un nuevo vértice
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    @Override
    protected Vertice nuevoVertice(T elemento) {
        return new VerticeAVL(elemento);
    }
    
    /**Inserta un elemento de forma ordenada
     *@param elemento - elemento a agrgar
     */
    @Override
    public void add(T elemento)
    {
	//System.out.println("Añadiendo " + elemento);
        insert(elemento);
    }


    /**Inserta un elemento de forma ordenada
     *@param elemento - elemento a agregar
     */
    private void insert(T elemento){
	if(elemento == null)
	    return;
	super.add(elemento); //agregamos el elemento a un árbol AVL como un BST


	/**Algoritmo para agregar un elemento e a un árbol AVL:
	   /1  Insertamos e al árbol como un BST   
	   / cualquiera.
	   /2 Sea v el nodo donde se guarda e,
	   / actualizamos alturas y rebalanceamos
	   /  desde v hasta la raíz.
	*/

	/**
	   /OJO AQUÍ :D
	 */
	(VerticeAVL)elemento.balancearAVL();  //balanceamos desde el elemento agregado hasta la raiz.
	
	    

    }

    public void delete(VerticeAVL elemento){
	if(elemento == null)
	    return;
	VerticeAVL v = new VerticeAVL(elemento);

	//Caso1
	//Para eliminar una hoja del árbol. 
	if(v.hayDerecho == false || v.hayIzquierdo == false){
	    if(padre.izquierdo == v)
		padre.izquierdo == null;
	    if(padre.derecho == v)
		padre.derecho == null;
	}

	//Caso2
	//Eliminar vértice con un sólo hijo.
	if(v.hayIzquierdo == true & v.hayDerecho == false){
	    v.padre.izquierdo == v.izquierdo;
		//¿v == null?//
	}

	if(v.hayDerecho == true & v.hayIzquierdo == false){
	    v.padre.derecho == v.derecho;
		//¿v == null?//
	}	

	//Caso3
	//Eliminar vértice con 2 hijos.
	if(v.hayDerecho == true && v.hayIzquierdo == true){
	    /**/
	}
	
	
    }
    
    public void balancearAVL()
    {
        raiz = rotarIzquierda(raiz);
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

    /**Obtiene la diferencia de la altura maxima del subarbol izquierdo y derecho de v.
     *Regresa un numero menor a -1 si es pesado a la derecha, y mayor a 1 si es pesado a la izquierda.
     *@param v - raiz del subarbol cuya diferencia de balance se quiere saber
     *@return diferencia de altura de los subarboles hijos.
     */
    private int balance(Vertice v)
    {
	int d = 0;
	int i = 0;
	if(v.hayDerecho())
	{
	    d = v.derecho.altura();
	}
	if(v.hayIzquierdo())
	{
	    i = v.izquierdo.altura();
	}
        int diff = i - d;

	return diff;
    }

    /**Obtiene la diferencia de balance del arbol
     *@return diferencia del balance
     */
    public int balanceDiff()
    {
	return balance(raiz);
    }

    /**Rota un subarbol a la derecha
     *@param v - raiz del subarbol a rotar
     */
    private void rotarDerecha(Vertice v)
    {
	if(v == null || !v.hayIzquierdo())
	{
	    return;
	}

	/*
	 *    padre                padre
	 *      |                    |
	 *      a(v)                 b
	 *     /  \                 /  \
	 *    b   gamma   ->       alfa a(v)
	 *   /  \                      /  \
	 *  alfa beta                beta  gamma
	 *
	 *
	 */

	//Intercambio de beta
        Vertice tempi = v.izquierdo;
	v.izquierdo = tempi.derecho;
	tempd.derecho = v;

	//actualiza los nodos padres
	tempi.padre = v.padre;
	v.padre = tempi;
	if(v.izquierdo != null)
	    {
		v.izquierdo.padre = v;
	    }

	if(tempi.padre != null)
	{
	    if(tempi.padre.izquierdo == v.elemento)
	    {
		tempi.padre.izquierdo = tempi;
	    }
	    else
	    {
		tempi.padre.derecho = tempi;
	    }
	}
	return tempi;
    }
    /**Rota un subarbol a la izquierda
     *@param v - raiz del subarbol a rotar
     */
    private Vertice rotarIzquierda(Vertice v)
    {
	if(v == null || !v.hayDerecho())
	{
	    return v;
	}

	/*
	 *    padre                padre
	 *      |                    |
	 *      v                    b
	 *     /  \                 /  \
	 *   alfa  b   ->          v    gamma
	 *        /  \            /  \    
	 *      beta gamma      alfa  beta
	 *
	 *
	 */

	//intercambia beta
	Vertice tempd = v.derecho;
	v.derecho = tempd.izquierdo;
	tempd.izquierdo = v;

	//actualiza los padres
	tempd.padre = v.padre;
	v.padre = tempd;
	if(v.derecho != null)
	    {
		v.derecho.padre = v;
	    }

	if(tempd.padre != null)
	{
	    if(tempd.padre.izquierdo == v.elemento)
	    {
		tempd.padre.izquierdo = tempd;
	    }
	    else
	    {
		tempd.padre.derecho = tempd;
	    }
	}
	return tempd;

    }

}
