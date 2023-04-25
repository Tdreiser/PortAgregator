import Domain.Port;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * @author Shlokov Andrey
 */
public class PortInitTest {
    @Test
    public void with_incorrect_range() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Port(new String[]{"1-,2,3", "1-4"}));
    }

    @Test
    public void with_empty_port() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Port(new String[]{"", "1-4"}));
    }

    @Test
    public void correct_init() {
        Assertions.assertDoesNotThrow(() -> new Port(new String[]{"1,3-5", "2", "1-4"}));
    }

    @Test
    public void correct_init_with_reverse_range() {
        Assertions.assertDoesNotThrow(() -> new Port(new String[]{"1,3-5", "2", "4-1"}));
    }

}
