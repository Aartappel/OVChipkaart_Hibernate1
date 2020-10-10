import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private SessionFactory factory;

    public ProductDAOHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public boolean save(Product product) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.save(product);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Product product) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.update(product);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Product product) {
        Session session = factory.openSession();
        session.beginTransaction();

        session.delete(product);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Product findByNummer(int nummer) {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Product where productNummer = " + nummer);
        Product product = (Product) query.list().get(0);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
            Session session = factory.openSession();
            session.beginTransaction();

            Query query = session.createQuery("from Product");
            List<Product> producten = query.list();
            for (Product product : producten) {
                for (OVChipkaart ovChipkaart1 : product.getOvChipkaarten()) {
                    if (ovChipkaart1.getKaartNummer() == ovChipkaart.getKaartNummer()) {
                        producten.add(product);
                    }
                }
            }

            session.getTransaction().commit();
            session.close();
            return producten;
    }

    @Override
    public List<Product> findAll() {
        Session session = factory.openSession();
        session.beginTransaction();

        Query query = session.createQuery("from Product");
        List producten = query.list();
        session.getTransaction().commit();
        session.close();
        return producten;
    }
}
