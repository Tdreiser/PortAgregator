import Domain.Port;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author Shlokov Andrey
 */
public class PortGroupTest {
    private static Port port;
    private static Integer[][] group;

    @BeforeAll
    public static void init() {
        port = new Port(new String[]{"1,2", "1-4"});
        group = new Integer[][]{new Integer[]{1,2},new Integer[]{1,2,3,4}};
    }

    @Test
    public void equals_to_another_group() {
        Assertions.assertArrayEquals(port.getGroups(),group);
    }
    @Test
    public void equals_itself() {
        Assertions.assertArrayEquals(port.getGroups(),port.getGroups());
    }

}
