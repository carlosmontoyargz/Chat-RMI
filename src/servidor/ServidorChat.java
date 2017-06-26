package servidor;

import java.rmi.RemoteException;
import java.rmi.registry.*;

/**
 *
 * @author carlosmontoya
 */
public class ServidorChat
{
	public static void main(String[] args)
	{
		try
		{
			Registry reg = LocateRegistry.createRegistry(3456);
			reg.rebind("chat", new ServicioChatImpl());
			
			System.out.println("Servidor iniciado correctamente\n");
		}
		catch (RemoteException e)
		{
			System.out.println("El registro ya existe\n");
		}
	}
}
