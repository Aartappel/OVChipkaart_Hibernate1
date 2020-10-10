import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private SessionFactory factory;

    public AdresDAOHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean save(Adres adres) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(adres);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Adres adres) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.update(adres);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Adres adres) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.delete(adres);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Adres where reiziger = " + reiziger);
        Adres adres = (Adres) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return adres;
    }

    @Override
    public Adres findById(int ID) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Adres where adresID = " + ID);
        Adres adres = (Adres) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return adres;
    }

    @Override
    public List findAll() {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Adres");
        List adressen = query.list();
        session.getTransaction().commit();
        session.close();
        return adressen;
    }
}
