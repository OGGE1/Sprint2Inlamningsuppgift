import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Oscar Norman <br>
 * Date: 2020-10-12   <br>
 * Time: 13:42   <br>
 * Project: Sprint 2 Inlämningsuppgift <br>
 */
public class MainProgram {
    private BestGymEver bge = new BestGymEver();
    private List<Customer> customerList = bge.getCustomerList(bge.inFile);


    public void mainMenu() {
        Scanner ch = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        System.out.println("*-*-Best gym ever-*-*");
        while (true) {
            int choice;
            String input;
            try {
                System.out.println("1. Sök kund");
                System.out.println("2. Registrera besök");
                System.out.println("3. Skriv till fil");
                System.out.println("4. Avsluta");
                choice = ch.nextInt();

                if (choice < 1 || choice > 4) {
                    throw new InputMismatchException();
                }
                if (choice == 1) {
                    System.out.print("Sök kund>");
                    input = in.nextLine();
                    search(input, this.customerList);
                } else if (choice == 2) {
                    System.out.println("Ange namn eller personnummer");
                    input = in.nextLine();
                    goToGym(input, this.customerList);
                } else if (choice == 3) {
                    skrivTillFil(this.customerList, bge.outFile);
                } else {
                    System.out.println("Hej då!");
                    System.exit(0);
                }

            } catch (InputMismatchException e) {
                System.out.println("Du angav ett felaktigt värde, försök igen!");
                mainMenu();
            } catch (Exception e) {
                System.out.println("Något gick fel, försök igen!");
                e.printStackTrace();
                mainMenu();
            }
        }
    }

    public void goToGym(String input, List<Customer> list) {
        LocalDate datum = LocalDate.now();
        boolean isInList = false;
        for (var e : list) {
            if (valideraInput(input, e)) {
                isInList = true;
                if (e.harBetalatAvgift()) {
                    e.läggTillAntalGångerPåGym(datum);
                    System.out.println("Sucess!");
                } else
                    System.out.println("Denna person har ej betalat sin årsavgift!");
            }
        }
        if (!isInList)
            System.out.println("Kunde ej hitta " + input);
    }

    public void search(String input, List<Customer> list) {
        try {
            boolean isInList = false;
            for (var e : list) {
                if (valideraInput(input, e)) {
                    System.out.println(e.toString());
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

    public void skrivTillFil(List<Customer> list, Path outFile) {

        try (PrintWriter w = new PrintWriter(
                Files.newBufferedWriter(outFile))) {
            for (var e : list) {
                w.write(e.toString() + "\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde ej skapas!");
            System.exit(0);

        } catch (IOException e) {
            System.out.println("Det gick ej att skriva till fil!");
            e.printStackTrace();
            System.exit(0);

        } catch (Exception e) {
            System.out.println("Något gick fel!");
            e.printStackTrace();
            System.exit(0);
        }
    }


    public boolean valideraInput(String input, Customer customer) {
        return customer.getNamn().equalsIgnoreCase(input) || customer.getPersonnummer().equalsIgnoreCase(input);
    }

    public void runProgram() {
        mainMenu();
    }
}
