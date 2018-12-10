import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by mtumilowicz on 2018-12-10.
 */
public class Implementation {

    static <T, R> Stream<R> map(Stream<T> stream, Function<T, R> mapper) {
        return stream
                .reduce(Stream.empty(),
                        (acc, element) -> Stream.concat(acc, Stream.of(mapper.apply(element))),
                        Stream::concat
                );
    }

    static <T> Stream<T> filter(Stream<T> stream, Predicate<T> filter) {
        return stream
                .reduce(Stream.empty(),
                        (acc, element) -> Stream.concat(acc, filter.test(element) ? Stream.of(element) : Stream.empty()),
                        Stream::concat
                );
    }
}
