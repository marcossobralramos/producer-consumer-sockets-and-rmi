package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteMethodInterface extends Remote
{
	void produce() throws RemoteException;
    void consume() throws RemoteException;
}
