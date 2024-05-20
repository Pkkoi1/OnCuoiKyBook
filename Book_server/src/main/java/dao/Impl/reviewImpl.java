package dao.Impl;

import dao.reviewDao;
import entity.Book;
import entity.Person;
import entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class reviewImpl extends UnicastRemoteObject implements reviewDao {
    EntityManager em;

    public reviewImpl() throws RemoteException {
        super();
        em = Persistence.createEntityManagerFactory("SQLdb").createEntityManager();
    }
    @Override
    public boolean updateReviews(String ibsn, String readerID, int rating, String review) throws RemoteException {
        try {
            if(rating < 1 || rating > 5) {
                return false;
            }
            if(review.length() < 1 || review.length() > 500) {
                return false;
            }
            em.getTransaction().begin();
            Book book = em.find(Book.class, ibsn);
            Person person = em.find(Person.class, readerID);
            if (book != null && person != null) {
                Review newReview = new Review(rating, review, book, person);
                em.persist(newReview);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }
}
