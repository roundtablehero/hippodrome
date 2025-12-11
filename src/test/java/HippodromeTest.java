import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Test
    void shouldThrowWhenHorsesAreNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowWhenHorsesAreEmpty() {
        List<Horse> emptyList = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(emptyList));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> expectedHorses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            expectedHorses.add(new Horse("Horse" + i, 10.0 + i, 100.0 + i));
        }
        Hippodrome hippodrome = new Hippodrome(expectedHorses);
        List<Horse> actualHorses = hippodrome.getHorses();
        assertIterableEquals(expectedHorses, actualHorses);

        for (int i = 0; i < 30; i++) {
            assertSame(expectedHorses.get(i), actualHorses.get(i));
        }
    }

    @Test
    void move() {

        List<Horse> horseMocks = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseMocks.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horseMocks);

        hippodrome.move();

        for (Horse horseMock : horseMocks) {
            verify(horseMock, times(1)).move();
        }
    }

    @Test
    void getWinner() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse1", 10.0, 100.0));
        horses.add(new Horse("Horse2", 10.0, 200.0));
        horses.add(new Horse("Horse3", 10.0, 300.0));
        horses.add(new Horse("Horse4", 10.0, 400.0));
        horses.add(new Horse("Horse5", 10.0, 500.0));
        horses.add(new Horse("Horse6", 10.0, 600.0));

        Horse expectedWinner = horses.getFirst();
        for (Horse horse : horses) {
            if (horse.getDistance() > expectedWinner.getDistance()) {
                expectedWinner = horse;
            }
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        Horse actualWinner = hippodrome.getWinner();

        assertSame(expectedWinner, actualWinner);
    }
}