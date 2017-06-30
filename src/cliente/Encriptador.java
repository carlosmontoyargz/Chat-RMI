package cliente;

/**
 * 
 * @author carlosmontoya
 */
public class Encriptador
{
	public static String encriptarCesar(String msg, int clave)
	{
		StringBuilder msgCifrado = new StringBuilder(msg.length());

		for (int i = 0; i < msg.length(); i++)
			msgCifrado.append((char) ((int) (msg.charAt(i)) + clave % 255));

		return msgCifrado.toString();
	}

	public static String desencriptarCesar(String msg, int clave)
	{
		return encriptarCesar(msg, -clave);
	}
}
