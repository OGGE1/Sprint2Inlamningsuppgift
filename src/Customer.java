import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-10-11   <br>
 * Time: 13:18   <br>
 * Project: Sprint 2 Inlämningsuppgift <br>
 */
public class Customer {
    private String personnummer;
    private String namn;
    private LocalDate betalningsDatum = LocalDate.now();
    private List<LocalDate> antalGångerPåGym = new ArrayList<>();

    Customer() {}

    Customer(String personnummer, String namn, LocalDate datum) {
        setPersonnummer(personnummer);
        setNamn(namn);
        setBetalningsDatum(datum);
    }

    public void läggTillAntalGångerPåGym(LocalDate datum) {
        this.antalGångerPåGym.add(datum);
    }

    public String printAntalGångerPåGym() {
        String dates = "";
        for (var e: antalGångerPåGym){
            dates += e + "\n";
        }
        return dates;
    }

    public void setBetalningsDatum(LocalDate betalningsDatum) {
        this.betalningsDatum = betalningsDatum;
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

    public boolean harBetalatAvgift() {
        LocalDate today = LocalDate.now();
        return getBetalningsDatum().isAfter(today.minusYears(1));
    }

    public String printHarBetalatAvgift() {
        if (harBetalatAvgift())
            return "Ja";
        else
            return "Nej";
    }

    public LocalDate getBetalningsDatum() {
        return betalningsDatum;
    }

    @Override
    public String toString() {
        return getNamn().toUpperCase() + "\nPersonnummer: " + getPersonnummer() + "\nBetalat avgiften? " +
               printHarBetalatAvgift() +"\n\u2193Besök på gym\u2193" + "\n" + printAntalGångerPåGym();

    }
}
