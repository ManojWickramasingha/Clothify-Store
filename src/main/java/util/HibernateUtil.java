package util;

import entity.EmployeeEntity;
import entity.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = createSession();

    private static  SessionFactory createSession(){
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metaDate = new MetadataSources(build)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metaDate.getSessionFactoryBuilder().build();
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}
