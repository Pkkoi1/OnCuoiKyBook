package dao.Impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import dao.reviewDao;

import java.rmi.RemoteException;

class reviewImplTest {

    static reviewDao reviewDao;

    @BeforeAll
    static void setUp() throws Exception {
        reviewDao = new reviewImpl();
    }

    @AfterAll
    static void tearDown() {
        reviewDao = null;
    }

    @Test
    void testCauC1() throws RemoteException {
        assertTrue(reviewDao.updateReviews("978-0132350884", "reader1", 5, "This is a good book"));
    }
    @Test
    void testCauC2() throws RemoteException {
        assertFalse(reviewDao.updateReviews("978-03211274200", "1", 3, "This is a good book"));
    }
    @Test
    void testCauC3() throws RemoteException {
        assertFalse(reviewDao.updateReviews("978-0132350884", "1", 6, "This is a good book"));
    }

    @Test
    void testCauC4() throws RemoteException {
        assertFalse(reviewDao.updateReviews("978-0132350884", "1", 3, ""));
    }
    @Test
    void testCauC5() throws RemoteException {
        assertTrue(reviewDao.updateReviews("978-0132350884", "1", 3, "czxcxzcz"));
    }
}