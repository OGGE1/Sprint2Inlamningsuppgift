import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    Path inFile = Paths.get("Customers.txt");
    Path outFile = Paths.get("outCustomers");

    public List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        try (Scanner sc = new Scanner(inFile);) {
            String personnummer = "";
            String namn = "";
            String datum = "";
            if (sc.hasNext()) {
                personnummer = sc.next();
            }
            if (sc.hasNext()) {
                namn = sc.nextLine();
            }
            if (sc.hasNext())
                datum = sc.nextLine();

            customerList.add(new Customer(disectPersonnummer(personnummer), disectNamm(namn),
                    disectDatum(datum)));
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
