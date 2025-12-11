import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

class HorseTest {
    private Horse horse;

    @Test
    void shouldThrowWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 10.0, 100.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\t\n"})
    void shouldThrowWhenNameIsBlank(String input) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(input, 10.0, 100.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void shouldThrowWhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("TestName", 10.0, -Double.MAX_VALUE));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void shouldThrowWhenSpeedIsNegative() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("TestName", -Double.MAX_VALUE, 100.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @BeforeEach
    void setUp() {
        horse = new Horse("TestName", 10.0, 100.0);
    }

    @Test
    void getName() {
        assertEquals("TestName", horse.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    void getDistanceThreeParams() {
        assertEquals(100.0, horse.getDistance());
    }

    @Test
    void getDistanceTwoParams() {
        Horse horseTwoParams = new Horse("TestName", 10.0);
        assertEquals(0.0, horseTwoParams.getDistance());
    }

    @Test
    void move_getCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {

            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            horse.move();

            mockedHorse.verify(() -> Horse.getRandomDouble(eq(0.2), eq(0.9)), Mockito.times(1));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    void move_checkDistanceByFormula(double randomValue) {
        double initialDistance = horse.getDistance();

        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

            horse.move();

            double expectedDistance = initialDistance + horse.getSpeed() * randomValue;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}