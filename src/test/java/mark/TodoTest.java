package mark;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void todoTest() {
        Todo t = new Todo("meet to eat");
        assertEquals("[T][ ] meet to eat", t.toString());
    }
}
