import Domain.Port;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Shlokov Andrey
 */
public class PortCombinationTest {
    private static Port port;
    private static Integer[][] combinations;

    @BeforeAll
    public static void init() {
        port = new Port(new String[]{"1,2", "1-4"});
        combinations = new Integer[][]{new Integer[]{1,1},new Integer[]{1,2},new Integer[]{1,3},
                new Integer[]{1,4},new Integer[]{2,1},new Integer[]{2,2},new Integer[]{2,3}, new Integer[]{2,4}};
    }

    @Test
    public void equals_to_another_combination() {
        Assertions.assertArrayEquals(port.getCombinations(), combinations);
    }
    @Test
    public void equals_itself() {
        Assertions.assertArrayEquals(port.getCombinations(),port.getCombinations());
    }
}
