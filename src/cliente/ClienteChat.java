package cliente;

import interfaces.*;
import java.net.MalformedURLException;
import java.util.*;
import java.rmi.*;

/**
 *
 * @author carlosmontoya
 */
class ClienteChat
{
	public static void main(String args[])
	{
		try
		{
			new ClienteChat().ejecutar();
		}
		catch (RemoteException e)
		{
			System.out.println("> El servidor esta desconectado");
		}
		catch (NotBoundException e)
		{
			System.err.println("Servidor no encontrado" + e.toString());
		}
		catch (MalformedURLException e)
		{}
		finally
		{
			System.exit(0);
		}
	}
	
	private void ejecutar()
			throws RemoteException, NotBoundException, MalformedURLException
	{
		Scanner ent = new Scanner(System.in);
			
		ServicioChat srv = (ServicioChat) Naming.lookup("//localhost:3456/chat");

		System.out.print("Ingresar la clave de cifrado: ");
		String key = ent.nextLine();
		System.out.print("¿Cual es tu nombre? ");
		String nombre = ent.nextLine();

		Receptor c = new ReceptorImpl(nombre, key);
		srv.agregar(c);

		String msg;
		do
		{
			msg = ent.nextLine();
			srv.enviar(c, Encriptador.Encriptar(msg, key));
			
			System.out.print(nombre + "> ");
		} while (!msg.isEmpty());

		srv.remover(c);
	}
}