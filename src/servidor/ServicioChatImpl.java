package servidor;

import interfaces.Receptor;
import interfaces.ServicioChat;
import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

/**
 *
 * @author carlosmontoya
 */
class ServicioChatImpl extends UnicastRemoteObject implements ServicioChat
{
	private final List<Receptor> usuarios;

	ServicioChatImpl() throws RemoteException
	{
		super();
		usuarios = new LinkedList<>();
	}

	@Override
	public synchronized void agregar(Receptor c) throws RemoteException
	{
		usuarios.add(c);
		String msg = c.getNombre() + " se ha unido al chat";
		System.out.println("> " + msg);
		enviarEstado(msg);
	}
	
	@Override
	public synchronized void remover(Receptor c) throws RemoteException
	{
		usuarios.remove(usuarios.indexOf(c));
		String msg = c.getNombre() + " ha dejado el chat";
		System.out.println("> " + msg);
		enviarEstado(msg);
	}

	@Override
	public synchronized void enviar(Receptor esc, String msg)
			throws RemoteException
	{
		String nombre = esc.getNombre();
		System.out.println("> Mensaje enviado por " + nombre + ": " + msg);
		
		Iterator<Receptor> it = usuarios.listIterator();
		while (it.hasNext())
		{
			Receptor r = it.next();
			if (!r.equals(esc))
			{
				try
				{
					r.recibir(nombre, msg);
				}
				catch (RemoteException e)
				{
					it.remove();
				}
			}
		}
	}
	
	private synchronized void enviarEstado(String msg)
	{
		Iterator<Receptor> it = usuarios.listIterator();
		while (it.hasNext())
		{
			Receptor c = it.next();
			try
			{ c.recibirEstado(msg); }
			catch (RemoteException e)
			{ it.remove(); }
		}
	}
}
