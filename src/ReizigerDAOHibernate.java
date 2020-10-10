import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Date;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private SessionFactory factory;

    public ReizigerDAOHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(reiziger);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.update(reiziger);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.delete(reiziger);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Reiziger findById(int ID) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Reiziger where reizigerID = " + ID);
        Reiziger reiziger = (Reiziger) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return reiziger;
    }

    @Override
    public List<Reiziger> findByGbdatum(String datum) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Reiziger");
        List<Reiziger> reizigers = query.list();
        for (Reiziger reiziger : reizigers) {
            if (reiziger.getGeboortedatum() == Date.valueOf(datum)) {
                reizigers.add(reiziger);
            }
        }

        session.getTransaction().commit();
        session.close();
        return reizigers;
    }

    @Override
    public List<Reiziger> findAll() {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Reiziger");
        List reizigers = query.list();
        session.getTransaction().commit();
        session.close();
        return reizigers;
    }

    @Override
    public Adres findAdresByReizigerId(Reiziger reiziger) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Adres where reiziger.reizigerID = " + reiziger.getReizigerID());
        Adres adres = (Adres) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return adres;
    }
}
