import java.io.LineNumberInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-10-12   <br>
 * Time: 13:42   <br>
 * Project: Sprint 2 Inlämningsuppgift <br>
 */
public class Menu {
    private Scanner in = new Scanner(System.in);
    private BestGymEver bge = new BestGymEver();


    public void mainMenu() {

        System.out.println("*-*-Best gym ever-*-*");
        while (true) {
            String input = "";
            System.out.print("Sök kund>");
            //System.out.println("Size: " + bge.getCustomerList().size());
            try {
                input = in.nextLine();
            } catch (Exception e) {
                System.out.println("Något gick fel, försök igen!");
                input = in.nextLine();
            }
            search(input, bge.getCustomerList());
        }
    }

    public void goToGym(List<Customer> list, String input) {
        for (var e : list){
            if (e.getNamn().equalsIgnoreCase(input) || e.getPersonnummer().equalsIgnoreCase(input))
                e.getAntalGångerPåGym().add(String.valueOf(LocalDate.now()));
        }
    }

    public void search(String input, List<Customer> list) {
        try{
            boolean isInList = false;
            for (var e : list){
                if (e.getNamn().equalsIgnoreCase(input) || e.getPersonnummer().equalsIgnoreCase(input)) {
                    System.out.println(e.getNamn() + "\n" + e.getPersonnummer() + "\nDatum" + "\n-------------"
                    + "\n" + e.getAntalGångerPåGym().toString());
                    isInList = true;
                }
            }
            if (!isInList)
                System.out.println("Kunde ej hitta " + input);

        } catch (Exception e) {
            System.out.println("Något gick fel, försök igen!");
            e.printStackTrace();
            mainMenu();
        }

    }

    public void mainProgram() {
        mainMenu();
    }
}
