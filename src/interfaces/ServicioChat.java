package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author carlosmontoya
 */
public interface ServicioChat extends Remote
{
	void agregar(Receptor c) throws RemoteException;
	
    void remover(Receptor c) throws RemoteException;
	
    void enviar(Receptor c, String msg) throws RemoteException;
}
