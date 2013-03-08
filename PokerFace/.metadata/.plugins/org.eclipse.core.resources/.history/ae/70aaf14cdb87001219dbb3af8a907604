
package ch.hearc.coursjava.poo.derivation.times;



public class HmsmTimes extends HmsTimes
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public HmsmTimes(int heure, int minute, int seconde, int miliseconde)
		{
		super(heure, minute, seconde);
		this.miliseconde = miliseconde;
		}

	public HmsmTimes()
		{
		super(0,0,0);
		this.miliseconde = 0;
		}

	public HmsmTimes(HmsmTimes source)
		{
		super(source);
		this.miliseconde = source.miliseconde;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	public HmsmTimes cloneOf()
		{
		return new HmsmTimes(this);
		}

	public boolean equals(HmsmTimes ht)
		{
		return this.miliseconde == ht.miliseconde && this.toString() == ht.toString();
		}

	@Override
	public String toString()
		{
		StringBuilder builder = new StringBuilder();
		builder.append("HmsmTimes [miliseconde=");
		builder.append(this.miliseconde);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
		}

	/*------------------------------*\
	|*				Set				*|
	\*------------------------------*/

	public void set(int heure, int minute, int seconde, int miliseconde)
		{
		set(heure, minute, seconde);
		this.miliseconde = miliseconde;
		}

	/*------------------------------*\
	|*				Get				*|
	\*------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	// Input / OutPut
	private int miliseconde;

	}

