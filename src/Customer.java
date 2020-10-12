/**
 * Created by Oscar Norman <br>
 * Date: 2020-10-11   <br>
 * Time: 13:18   <br>
 * Project: Sprint 2 Inlämningsuppgift <br>
 */
public class Customer {
    private String personnummer;
    private String namn;
    private String datum;

    Customer(String personnummer, String namn, String datum) {
        this.personnummer = personnummer;
        this.namn = namn;
        this.datum = datum;
    }

    public String getPersonnummer() {
        return personnummer;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }
}
