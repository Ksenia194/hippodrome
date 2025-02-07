import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {

    @Nested
    class ConstructorTest {

        @Test
        void shouldThrowExceptionWhenNameIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.4, 0));
            assertEquals("Name cannot be null.", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "\t", "\n", "\r", "  \t  ", "\n\r", "\t\n"})
        void shouldThrowExceptionWhenNameIsBlank(String name) {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.4, 0));
            assertEquals("Name cannot be blank.", exception.getMessage());
        }

        @Test
        void shouldThrowExceptionWhenSpeedIsNegative() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -2.4, 0));
            assertEquals("Speed cannot be negative.", exception.getMessage());
        }

        @Test
        void shouldThrowExceptionWhenDistanceIsNegative() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 2.4, -1));
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }
    }

    @Test
    void getName() {
        assertEquals("Bucephalus", new Horse("Bucephalus", 2.4, 0).getName());
    }

    @Test
    void getSpeed() {
        assertEquals(2.4, new Horse("Bucephalus", 2.4, 0).getSpeed());
    }

    @Nested
    class DistanceTest {

        @Test
        void getDistance() {
            assertEquals(0, new Horse("Bucephalus", 2.4, 0).getDistance());
        }

        @Test
        void shouldNullWhenDistanceIsEmpty() {
            assertEquals(0, new Horse("Bucephalus", 2.4).getDistance());
        }
    }

    @Nested
    class MoveTest {

        @ParameterizedTest
        @CsvSource({
                "0.2, 2.4, 0.48,",
                "0.5, 2.4, 1.2,",
                "0.9, 2.4, 2.16"
        })
        void move(double randomValue, double speed, double expectedDistance) {
            try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
                mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);
                Horse horse = new Horse("Bucephalus", speed, 0);
                horse.move();

                mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
                assertEquals(expectedDistance, horse.getDistance());

            }
        }
    }
}