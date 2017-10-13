package com.sda.jpa;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;

import com.sda.jpa.entity.Book;
import com.sda.jpa.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class Examples {

    private final SessionFactory sessionFactory;

    public Examples(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private void example2() {
        Session session = sessionFactory.openSession();

        Query<Person> selectAllQuery = session.createQuery("from Person", Person.class);

        List<Person> persons = selectAllQuery.list();

        for (Person p : persons) {
            System.out.println(p);
        }

        session.close();
    }

    private void example1() {

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Person p = new Person();
        p.setName("long name");

        Book book = new Book();
        book.setName("asd");

        p.setBooks(Collections.singletonList(book));

        session.save(p);

        session.getTransaction().commit();

        session.close();
    }

    private void example3() {

        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();

        Person p2 = new Person();
        p2.setName("short");

        em.persist(p2);

        em.getTransaction().commit();
        em.close();
    }

    public void execute() {
        example2();
        example1();
    }
}
