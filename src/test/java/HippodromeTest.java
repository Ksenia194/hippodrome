import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @Nested
    class ConstructorTest {

        @Test
        void shouldThrowExceptionWhenIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
            assertEquals("Horses cannot be null.", exception.getMessage());
        }

        @Test
        void shouldThrowExceptionWhenIsEmpty() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
            assertEquals("Horses cannot be empty.", exception.getMessage());
        }
    }

    @Test
    void getHorses() {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3),
                new Horse("Archer", 3.1),
                new Horse("Bolt", 3.2),
                new Horse("Comet", 3.3),
                new Horse("Dancer", 3.4),
                new Horse("Eclipse", 3.5),
                new Horse("Falcon", 3.6),
                new Horse("Gale", 3.7),
                new Horse("Hawk", 3.8),
                new Horse("Icarus", 3.9),
                new Horse("Jupiter", 4),
                new Horse("Kite", 4.1),
                new Horse("Lightning", 4.2),
                new Horse("Mars", 4.3),
                new Horse("Neptune", 4.4),
                new Horse("Orion", 4.5),
                new Horse("Polaris", 4.6),
                new Horse("Quasar", 4.7),
                new Horse("Rigel", 4.8),
                new Horse("Sirius", 4.9),
                new Horse("Titan", 5),
                new Horse("Uranus", 5.1)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = IntStream.range(0,50)
                .mapToObj(i -> mock(Horse.class))
                .collect(Collectors.toList());

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        horses.forEach(horse -> verify(horse).move());
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Sirius", 4.9, 39);
        Horse horse2 = new Horse("Titan", 5, 38);
        Horse horse3 = new Horse("Uranus", 5.1, 28);
        List<Horse> horses = List.of(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horse1, hippodrome.getWinner());
    }
}