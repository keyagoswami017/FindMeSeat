package com.neu.csye6220.libseatmgmt.config;

import com.neu.csye6220.libseatmgmt.model.User;
import com.neu.csye6220.libseatmgmt.model.Admin;
import com.neu.csye6220.libseatmgmt.model.Seat;
import com.neu.csye6220.libseatmgmt.model.Reservation;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory getSessionFactory() {
        Map<String, Object> settings = new HashMap<>();
        settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        settings.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/libraryseatdb");
        settings.put("hibernate.connection.username", "postgres");
        settings.put("hibernate.connection.password", "postgres");

        settings.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        settings.put("hibernate.hbm2ddl.auto", "update");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");

        //settings.put("hibernate.current_session_context_class", "thread");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        // Add your entity classes here
        metadataSources.addPackage("com.neu.csye6220.libseatmgmt.model");
        metadataSources.addAnnotatedClass(User.class);
        metadataSources.addAnnotatedClass(Admin.class);
        metadataSources.addAnnotatedClass(Seat.class);
        metadataSources.addAnnotatedClass(Reservation.class);

        Metadata metadata = metadataSources.buildMetadata();

        //System.out.println("Adding entity: " + User.class.getName());
       /* metadata.getEntityBindings().forEach(persistentClass -> {
            System.out.println("Mapped entity: " + persistentClass.getMappedClass().getName() + " to table: " + persistentClass.getMappedClass().getClassLoader());
        });*/

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        return sessionFactory;
    }

}
