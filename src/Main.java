import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 * <p>
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testFetchAll();
        testDAOHibernate(new AdresDAOHibernate(factory), new ReizigerDAOHibernate(factory),
                new OVChipkaartDAOHibernate(factory), new ProductDAOHibernate(factory));
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    private static void testDAOHibernate(AdresDAOHibernate adaoh, ReizigerDAOHibernate rdaoh,
                                         OVChipkaartDAOHibernate odaoh, ProductDAOHibernate pdaoh) {
        // Maak een nieuw adres aan en persisteer deze in de database
        List adressen = adaoh.findAll();
        Adres Utrecht = new Adres(21, "3718EH", "17", "Utrechtsestraat",
                "Utrecht",  new Reiziger(12, "S", "van", "Hoek",
                Date.valueOf("1982-12-09")));
        System.out.print("[Test] Eerst " + adressen.size() + " adressen, na AdresDAOHibernate.save() ");
        adaoh.save(Utrecht);
        adressen = adaoh.findAll();
        System.out.println(adressen.size() + " adressen\n");

        // Reiziger ophalen met id
        System.out.println("[Test] Reiziger met id 3: " + rdaoh.findById(3));

        // Reiziger ophalen met geboortedatum
        System.out.println("[Test] Reiziger(s) met geboortedatum 2002-12-03: " + rdaoh.findByGbdatum("2002-12-03"));

        // Update achternaam van een reiziger
        Reiziger reiziger = rdaoh.findById(1);
        System.out.print("[Test] Eerst: " + reiziger.getAchternaam() + ", ");
        reiziger.setAchternaam("Maas");
        rdaoh.update(reiziger);
        System.out.println("na ReizigerDAO.update(): " + rdaoh.findById(1).getAchternaam());

        // Update ongedaan maken
        reiziger.setAchternaam("Rijn");
        rdaoh.update(reiziger);
    }
}
