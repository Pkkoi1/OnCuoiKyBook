package dao;

import entity.Book;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface bookDao extends Remote {
    public List<Book> listRatedBooks(String author, int rating) throws RemoteException;
    public List<Book> listBooks() throws RemoteException;
    public Map<String, Long> countBooksByAuthor() throws RemoteException;
}
