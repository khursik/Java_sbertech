import org.json.simple.parser.ParseException;
import sbertech.model.Client;
import sbertech.model.impl.Holding;
import sbertech.model.impl.Individual;
import sbertech.model.impl.LegalEntity;
import sbertech.utils.DataProvider;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;


class Test {
    @org.junit.jupiter.api.Test
    void testHoldingSwitch() throws IOException, ParseException {
        Client client = DataProvider.getClientFromFileSwitch("C://test/input_holding.txt");
        assertEquals(Holding.class, client.getClass());
    }
    @org.junit.jupiter.api.Test
    void testLegalSwitch() throws IOException, ParseException {
        Client client = DataProvider.getClientFromFileSwitch("C://test/input_legal.txt");
        assertEquals(LegalEntity.class, client.getClass());
    }
    @org.junit.jupiter.api.Test
    void testIndividualSwitch() throws IOException, ParseException {
        Client client = DataProvider.getClientFromFileSwitch("C://test/input_individual.txt");
        assertEquals(Individual.class, client.getClass());
    }
    @org.junit.jupiter.api.Test
    void testHoldingEnum() throws IOException, ParseException {
        Client client = DataProvider.getClientFromFileEnum("C://test/input_holding.txt");
        assertEquals(Holding.class, client.getClass());
    }
    @org.junit.jupiter.api.Test
    void testLegalEnum() throws IOException, ParseException {
        Client client = DataProvider.getClientFromFileEnum("C://test/input_legal.txt");
        assertEquals(LegalEntity.class, client.getClass());
    }
    @org.junit.jupiter.api.Test
    void testIndividualEnum() throws IOException, ParseException {
        Client client = DataProvider.getClientFromFileEnum("C://test/input_individual.txt");
        assertEquals(Individual.class, client.getClass());
    }

}