package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface reviewDao extends Remote {
    public boolean updateReviews(String ibsn, String readerID, int rating, String review) throws RemoteException;
}
