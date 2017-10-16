package com.sda.jpa;

import com.sda.jpa.entity.Address;
import com.sda.jpa.entity.Book;
import com.sda.jpa.entity.Person;
import com.sda.jpa.entity.WorkPlace;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateConfiguration {

    private StandardServiceRegistry registry;
    private SessionFactory sessionFactory;

    private void init() {
        if (sessionFactory == null) {
            try {
                // Create registry
                registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(registry);


                // Create Metadata
                Metadata metadata = sources
                    .addPackage("com.sda.jpa.entity")
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(WorkPlace.class)
                    .getMetadataBuilder()
                    .build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
    }

    public SessionFactory createSessionFactory() {
        init();
        return sessionFactory;
    }

    public void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
