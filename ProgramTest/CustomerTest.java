import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by Oscar Norman <br>
 * Date: 2020-10-16   <br>
 * Time: 09:51   <br>
 * Project: Sprint 2 Inlämningsuppgift <br>
 */
public class CustomerTest {

    BestGymEver bge = new BestGymEver();
    MainProgram program = new MainProgram();
    Path inFileTest = Paths.get("KunderInTest.txt");
    LocalDate idag = LocalDate.now();
    LocalDate tvåÅrSedan = idag.minusYears(2);

    @Test
    public final void harBetalatTest() {
        Customer a = new Customer("9509081234", "Oscar Norman", idag);

        assertTrue(a.harBetalatAvgift());
        a.setBetalningsDatum(tvåÅrSedan);
        assertFalse(a.harBetalatAvgift());
    }

    @Test
    public final void searchTest() {
        Customer a = new Customer("9509081234", "Oscar", idag);
        LocalDate date = LocalDate.parse("2019-07-01");
        List<Customer> list = bge.getCustomerList(inFileTest);

        assertEquals(list.get(0).getNamn(), "Oscar Norman");
        assertEquals(list.get(0).getPersonnummer(), "9509081234");
        assertEquals(list.get(0).getBetalningsDatum(), date);

        assertEquals(list.get(1).getNamn(), "Therese Stålhandske");
        assertEquals(list.get(2).getNamn(), "Lars Norman");
    }

    @Test
    public final void skrivTillFilTest() {
        Path outFile = Paths.get("KunderUtTest");
        List<Customer> list = bge.getCustomerList(inFileTest);

        File f = new File("KunderUtTest");
        program.skrivTillFil(list, outFile);

        assertTrue(f.exists());
        assertTrue(f.canWrite());
        assertTrue(f.canRead());
    }

    @Test
    public final void valideraInputTest() {
        LocalDate idag = LocalDate.now();

        Customer a = new Customer("9509081234", "Oscar Norman", idag);
        String input = "Oscar Norman";
        assertTrue(program.valideraInput(input, a));
        assertFalse(program.valideraInput("Gustav Gustavsson", a));
    }
}
