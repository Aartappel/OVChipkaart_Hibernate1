import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    @Column(name = "kaart_nummer")
    private double kaartNummer;
    @Column(name = "geldig_tot")
    private Date geldigTotDatum;
    private int klasse;
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "reiziger_id", nullable = false)
    private Reiziger reiziger;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ov_chipkaart_product",
            joinColumns = {@JoinColumn(name = "kaart_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "product_nummer")})
    private List<Product> producten;

    public OVChipkaart(double kaartNummer, Date geldigTotDatum, int klasse, double saldo, Reiziger reiziger,
                       List<Product> producten) {
        this.kaartNummer = kaartNummer;
        this.geldigTotDatum = geldigTotDatum;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger = reiziger;
        this.producten = producten;
    }

    public OVChipkaart() {

    }

    public double getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(double kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTotDatum() {
        return geldigTotDatum;
    }

    public void setGeldigTotDatum(Date geldigTotDatum) {
        this.geldigTotDatum = geldigTotDatum;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    public boolean addProduct(Product product) {
        return this.producten.add(product);
    }

    public boolean removeProduct(Product product) {
        return producten.remove(product);
    }

    @Override
    public String toString() {
        return "OVChipkaart: " +
                "kaartNummer: " + kaartNummer +
                ", geldigTotDatum: " + geldigTotDatum +
                ", klasse: " + klasse +
                ", saldo: " + saldo +
                ", reiziger: " + reiziger +
                ", producten: " + producten.toString();
    }
}
