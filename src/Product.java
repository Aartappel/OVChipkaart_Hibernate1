import javax.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @Column(name = "product_nummer")
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;
    @ManyToMany(mappedBy = "producten")
    private List<OVChipkaart> ovChipkaarten;

    public Product(int productNummer, String naam, String beschrijving, double prijs, List<OVChipkaart> ovChipkaarten) {
        this.productNummer = productNummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipkaarten = ovChipkaarten;
    }

    public Product() {

    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int productNummer) {
        this.productNummer = productNummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOVChipkaarten(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten.addAll(ovChipkaarten);
    }

    public boolean addOVChipkaart(OVChipkaart ovChipkaart) {
        return this.ovChipkaarten.add(ovChipkaart);
    }

    public boolean removeOVChipkaart(OVChipkaart ovChipkaart) {
        return ovChipkaarten.remove(ovChipkaart);
    }

    @Override
    public String toString() {
        return "Product: " +
                "productNummer: " + productNummer +
                ", naam: '" + naam + '\'' +
                ", beschrijving: '" + beschrijving + '\'' +
                ", prijs: " + prijs;
    }
}
