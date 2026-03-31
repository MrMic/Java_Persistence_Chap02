package fr.michaelchlon.javapersistence.ch02;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldJPATest {

    @Test
    public void storeLoadMessage() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02");

        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            Message message = new Message();
            message.setText("Hello World!");

            em.persist(message);

            em.getTransaction().commit();
            // INFO: INSERT INTO Message (text) VALUES (1, 'Hello World!')

            em.getTransaction().begin();

            List<Message> messages =
                    em.createQuery("SELECT m FROM Message m", Message.class).getResultList();
            // INFO: SELECT * FROM Message

            messages.get(messages.size() - 1).setText("Hello World from JPA!");

            em.getTransaction().commit();
            // INFO: UPDATE Message SET text = 'Hello World from JPA!' WHERE id = 1

            assertAll(
                    () -> assertEquals(1, messages.size()),
                    () -> assertEquals("Hello World from JPA!", messages.get(0).getText()));

            em.close();
        } finally {
            emf.close();
        }
    }
}
