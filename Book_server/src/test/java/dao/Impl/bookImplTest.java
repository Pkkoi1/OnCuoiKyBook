package dao.Impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import dao.bookDao;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class bookImplTest {

    static bookDao bookDao;

    @BeforeAll
    static void setUp() throws Exception {
        bookDao = new bookImpl();
    }

    @AfterAll
    static void tearDown() {
        bookDao = null;
    }

    @Test
    void testListRatedBooks() throws RemoteException {
//        assertFalse(bookDao.listRatedBooks().isEmpty());
////        bookDao.listBooks().forEach(System.out::println);
        bookDao.listRatedBooks("Richard Helm", 0).forEach(System.out::println);
//        System.out.println(bookDao.listRatedBooks("Richard Helm", 0));
//        assertFalse(bookDao.listRatedBooks("Richard Helm", 0).isEmpty());
    }
    @Test
    void cauA() throws RemoteException {
        assertFalse(bookDao.listRatedBooks("Richard Helm", 0).size() == 0);
    }

    @Test
    void testCauB() throws RemoteException {
        bookDao.countBooksByAuthor().forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
    }
    @Test
    void testCauB1() throws RemoteException {
        assertFalse(bookDao.countBooksByAuthor().isEmpty());
    }
    @Test
    void testCauB2() throws RemoteException {
        assertTrue(bookDao.countBooksByAuthor().size() == 0);
    }


}