package mark;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parserTest() throws InvalidFormatException {
        assertThrows(InvalidFormatException.class, () -> Parser.parse("deadline task /bye"));
    }
}