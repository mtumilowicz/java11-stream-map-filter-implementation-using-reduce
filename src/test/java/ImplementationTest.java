import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * Created by mtumilowicz on 2018-12-10.
 */
public class ImplementationTest {

    @Test
    public void map_double() {
        var transformed = Implementation.map(Stream.of(1, 2, 3), number -> 2 * number)
                .collect(Collectors.toList());
        
        assertThat(transformed, hasSize(3));
        assertThat(transformed, contains(2, 4, 6));
    }

    @Test
    public void map_toString() {
        var transformed = Implementation.map(Stream.of(1, 2, 3), String::valueOf)
                .collect(Collectors.toList());

        assertThat(transformed, hasSize(3));
        assertThat(transformed, contains("1", "2", "3"));
    }

    @Test
    public void filter_odd() {
        var transformed = Implementation.filter(Stream.of(1, 2, 3, 4), number -> number % 2 != 0)
                .collect(Collectors.toList());

        assertThat(transformed, hasSize(2));
        assertThat(transformed, contains(1, 3));
    }

    @Test
    public void filter_even() {
        var transformed = Implementation.filter(Stream.of(1, 2, 3, 4), number -> number % 2 == 0)
                .collect(Collectors.toList());

        assertThat(transformed, hasSize(2));
        assertThat(transformed, contains(2, 4));
    }
}