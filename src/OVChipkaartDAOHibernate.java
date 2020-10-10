import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private SessionFactory factory;

    public OVChipkaartDAOHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(ovChipkaart);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.update(ovChipkaart);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.delete(ovChipkaart);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from OVChipkaart where reiziger = " + reiziger);
        List ovChipkaarten = query.list();
        session.getTransaction().commit();
        session.close();
        return ovChipkaarten;
    }

    @Override
    public OVChipkaart findByNummer(double ID) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from OVChipkaart where kaartNummer = " + ID);
        OVChipkaart ovChipkaart = (OVChipkaart) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return ovChipkaart;
    }

    @Override
    public List<OVChipkaart> findByProductNummer(int nummer) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from OVChipkaart");
        List<OVChipkaart> ovChipkaarten = query.list();
        for (OVChipkaart ovChipkaart : ovChipkaarten) {
            for (Product product : ovChipkaart.getProducten()) {
                if (product.getProductNummer() == nummer) {
                    ovChipkaarten.add(ovChipkaart);
                }
            }
        }

        session.getTransaction().commit();
        session.close();
        return ovChipkaarten;
    }

    @Override
    public List<OVChipkaart> findAll() {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from OVChipkaart");
        List ovChipkaarten = query.list();
        session.getTransaction().commit();
        session.close();
        return ovChipkaarten;
    }
}
