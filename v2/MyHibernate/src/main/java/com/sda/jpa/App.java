package com.sda.jpa;


import org.hibernate.SessionFactory;

public class App {

    private HibernateConfiguration hibernateConfiguration = new HibernateConfiguration();

    public static void main(String[] args) {

        App app = new App();
        SessionFactory sessionFactory = app.hibernateConfiguration.createSessionFactory();

        System.out.println("hello");

        Examples examples = new Examples(sessionFactory);

        examples.execute();

        app.hibernateConfiguration.shutdown();
    }
}
