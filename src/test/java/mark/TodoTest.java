package mark;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest(){
        Todo t = new Todo("meet to eat");
        assertEquals("[T][ ] meet to eat", t.toString());
    }
}