
package ch.hearc.pokerface.tools;


public class Pair<T,U> implements Comparable<Pair<T,U>>
{
	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	private T t;
	private U u;

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Pair(T t,U u)
	{
		this.t = t;
		this.u = u;
	}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public int compareTo(Pair<T, U> o)
	{
		return this == null ? (o == null ? 0 : 1) : ((Comparable<T>)o.t).compareTo(t);
	}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	public T getKey()
	{
		return this.t;
	}

	public U getValue()
	{
		return this.u;
	}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void setKey(T t)
	{
		this.t = t;
	}

	public void setValue(U u)
	{
		this.u = u;
	}
}

