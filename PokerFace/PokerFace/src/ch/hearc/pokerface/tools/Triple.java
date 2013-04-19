
package ch.hearc.pokerface.tools;


public class Triple<T,U,V> implements Comparable<Triple<T,U,V>>
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private T t;
	private U u;
	private V v;
	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Triple(T t,U u,V v)
	{
		this.t = t;
		this.u = u;
		this.v = v;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public int compareTo(Triple<T, U, V> o)
	{
		return this == null ? (o == null ? 0 : 1) : ((Comparable<T>)t).compareTo(o.t);
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public T getKey()
	{
		return this.t;
	}

	public U getValue1()
	{
		return this.u;
	}

	public V getValue2()
	{
		return this.v;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setKey(T t)
	{
		this.t = t;
	}

	public void setValue1(U u)
	{
		this.u = u;
	}

	public void setValue2(V v)
	{
		this.v = v;
	}

}

