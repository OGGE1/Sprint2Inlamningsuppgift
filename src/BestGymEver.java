import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-10-11   <br>
 * Time: 13:15   <br>
 * Project: Sprint 2 Inlämningsuppgift <br>
 */
public class BestGymEver {

    protected Path inFile = Paths.get("KunderIn.txt");
    protected Path outFile = Paths.get("KunderUt.txt");

    public List<Customer> getCustomerList(Path inFile) {
        List<Customer> customerList = new ArrayList<>();
        try (Scanner sc = new Scanner(inFile)) {
            while (sc.hasNext()) {
                String personnummer = sc.next();
                String namn = sc.nextLine();
                String datum = sc.nextLine();

                LocalDate date = LocalDate.parse(disectDatum(datum));
                customerList.add(new Customer(disectPersonnummer(personnummer), disectNamm(namn),
                        date));
            }
        } catch (Exception e) {
            System.out.println("Något gick fel.");
            e.printStackTrace();
        }
        return customerList;
    }

    public String disectNamm(String namn) {
        return namn.trim();
    }

    public String disectPersonnummer(String personnummer) {
        return personnummer.replace(',', ' ').trim();
    }

    public String disectDatum(String datum) {
        return datum.trim();
    }
}
