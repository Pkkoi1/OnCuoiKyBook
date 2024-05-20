package Application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class application {
    public static void main(String[] args) {
        EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("SQLdb");
        EntityManager em = emf.createEntityManager();
        em.close();
        emf.close();

    }
}
