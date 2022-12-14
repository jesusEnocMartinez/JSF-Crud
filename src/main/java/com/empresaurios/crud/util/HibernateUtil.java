package com.empresaurios.crud.util;

import com.empresaurios.crud.model.Auto;
import com.empresaurios.crud.model.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/desarrollo-web");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "postgres");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL95Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.DEFAULT_SCHEMA, "public");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                settings.put(Environment.C3P0_MIN_SIZE, 2); // Minimum number of JDBC connections in the pool.
                settings.put(Environment.C3P0_MAX_SIZE, 10); // Maximum number of JDBC connections in the pool.
                settings.put(Environment.C3P0_TIMEOUT, 180); // When an idle connection is removed from the pool (in second). Hibernate default: 0, never expire.
                settings.put(Environment.C3P0_MAX_STATEMENTS, 20); // Number of prepared statements will be cached. Increase performance. Hibernate default: 0 , caching is disable.
                settings.put(Environment.C3P0_IDLE_TEST_PERIOD, 3000); // Idle time in seconds before a connection is automatically validated. Hibernate default: 0

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Auto.class);
                configuration.addAnnotatedClass(Usuario.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                System.out.println("Hibernate Java Config serviceRegistry created");
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
