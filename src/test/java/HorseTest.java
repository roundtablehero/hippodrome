import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void shouldThrowWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10.0, 100.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\t\n"})
    void shouldThrowWhenNameIsBlank(String input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(input, 10.0, 100.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void shouldThrowWhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("TestName", 10.0, -Double.MAX_VALUE));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldThrowWhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("TestName", -Double.MAX_VALUE, 100.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
    }

    @Test
    void getSpeed() {
    }

    @Test
    void getDistance() {
    }

    @Test
    void move() {
    }

    @Test
    void getRandomDouble() {
    }
}