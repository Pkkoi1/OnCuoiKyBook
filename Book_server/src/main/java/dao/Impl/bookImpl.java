package dao.Impl;

import dao.bookDao;
import entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class bookImpl extends UnicastRemoteObject implements bookDao {

    EntityManager em;

    public bookImpl() throws Exception {
        super();
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }
    @Override
    public List<Book> listRatedBooks(String author, int rating) throws RemoteException {
        return em.createNamedQuery("Book.listRatedBooks", Book.class)
                .setParameter("rating", rating)
                .setParameter("author", author)
                .getResultList();
    }

    @Override
    public List<Book> listBooks() throws RemoteException {
        String query = "SELECT b FROM Book b";
        return em.createQuery(query, Book.class).getResultList();

    }

    @Override
    public Map<String, Long> countBooksByAuthor() throws RemoteException {
        Map<String, Long> result = new LinkedHashMap<>();
        String query = "SELECT a, COUNT(b) FROM BookTranslation b JOIN b.authors a GROUP BY a";
        List<Object[]> queryResult = em.createQuery(query).getResultList();
        queryResult.forEach(o -> {
            result.put((String) o[0], (Long) o[1]);
        });
        return result;
    }

}
