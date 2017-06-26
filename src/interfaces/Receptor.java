package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author carlosmontoya
 */
public interface Receptor extends Remote
{
	public String getNombre() throws RemoteException;
	
	public void recibir(String remitente, String m) throws RemoteException;
	
	public void recibirEstado(String msg) throws RemoteException;
}
