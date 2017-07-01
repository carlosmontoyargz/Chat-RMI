package cliente;

import interfaces.Receptor;
import java.rmi.*;
import java.rmi.server.*;

/**
 *
 * @author carlosmontoya
 */
class ReceptorImpl extends UnicastRemoteObject implements Receptor
{
	private final String nombre;
	private final int key;
	
	ReceptorImpl(String nombre, int key) throws RemoteException
	{
		super();
		this.nombre = nombre;
		this.key = key;
	}
	
	@Override
	public String getNombre() throws RemoteException
	{
		return this.nombre;
	}
	
	@Override
	public void recibir(String remitente, String m) throws RemoteException
	{
		m = Encriptador.desencriptarCesar(m, key);
		System.out.print("\n" + remitente + "> " + m + "\n" + this.nombre + "> ");
	}
	
	@Override
	public void recibirEstado(String msg) throws RemoteException
	{
		System.out.print("\n> " + msg + "\n" + this.nombre + "> ");
	}
}
