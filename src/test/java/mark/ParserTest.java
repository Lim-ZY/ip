package mark;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parserTest() throws InvalidFormatException {
        assertThrows(InvalidFormatException.class, () -> Parser.parse("deadline task /bye"));
    }
}
