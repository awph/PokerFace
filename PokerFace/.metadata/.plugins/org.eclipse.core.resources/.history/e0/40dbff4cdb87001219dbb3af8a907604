
package ch.hearc.coursjava.poo.derivation.animal;

public class Chat extends Mamifere
	{

	/*------------------------------------------------------------------*\
	|*							Constructeurs							*|
	\*------------------------------------------------------------------*/

	public Chat(String nom)
		{
		super(nom, TEMPERATURE_SANG);
		}

	public Chat(Chat source)
		{
		super(source);

		// Rien d'autre à cloner car pas d'attributs spécifiques à la classe chat.
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Public							*|
	\*------------------------------------------------------------------*/

	@Override
	public String manger()
		{
		return "Souris";
		}

	@Override
	public String nomClasse()
		{
		return super.nomClasse() + " " + Chat.class.getSimpleName();
		}

	@Override
	public Chat cloneOf()
		{
		return new Chat(this);
		}

	/*------------------------------*\
	|*				Is				*|
	\*------------------------------*/

	public boolean isEgal(Chat chat2)
		{
		return equals(chat2);
		}

	@Override
	public boolean equals(Object objet)
		{
		if (objet instanceof Chat)
			{
			return super.equals(objet);
			}
		else
			{
			return false;
			}
		}

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	/**
	 * Tous les chats instanciés après l'appel de cette méthode auront la nouvelle température.
	 * Les chats instanciés avant l'appel de cette méthode auront l'ancienne température.
	 */
	public static void setTemperatureSang(int temperature)
		{
		Chat.TEMPERATURE_SANG = temperature;
		}

	/*------------------------------------------------------------------*\
	|*							Methodes Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------------------------------------------*\
	|*							Attributs Private						*|
	\*------------------------------------------------------------------*/

	/*------------------------------*\
	|*			  Static			*|
	\*------------------------------*/

	private static int TEMPERATURE_SANG = 36;

	}
