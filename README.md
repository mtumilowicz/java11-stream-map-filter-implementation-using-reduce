[![Build Status](https://travis-ci.com/mtumilowicz/java11-stream-map-filter-implementation-using-reduce.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-stream-map-filter-implementation-using-reduce)

# java11-stream-map-filter-implementation-using-reduce
We will show how to implement stream's `map` / `filter` 
using only `reduce`.

# project description
In the `Stream API` we have three reduce methods:
1. `Optional<T> reduce(BinaryOperator<T> accumulator)`
1. `T reduce(T identity, BinaryOperator<T> accumulator)`
1. `U reduce(U identity,
            BiFunction<U, ? super T, U> accumulator,
            BinaryOperator<U> combiner)`
                    
First two are inadequate as they return `Optional<T>` or
return `T`, which is the same type as declared in the given stream - but as an output we want a new type `U ~ Stream<T>`).

So we take third method and provide requested implementations:
* map
    ```
    static <T, R> Stream<R> map(Stream<T> stream, Function<T, R> mapper) {
        return stream
                .reduce(Stream.empty(),
                        (acc, element) -> Stream.concat(acc, Stream.of(mapper.apply(element))),
                        Stream::concat
                );
    }
    ```
* filter
    ```
    static <T> Stream<T> filter(Stream<T> stream, Predicate<T> filter) {
        return stream
                .reduce(Stream.empty(),
                        (acc, element) -> Stream.concat(acc, filter.test(element) ? Stream.of(element) : Stream.empty()),
                        Stream::concat
                );
    }
    ```
# tests
* map
    ```
    var transformed = Implementation.map(Stream.of(1, 2, 3), number -> 2 * number)
            .collect(Collectors.toList());
    
    assertThat(transformed, hasSize(3));
    assertThat(transformed, contains(2, 4, 6));
    ```
* filter
    ```
    var transformed = Implementation.filter(Stream.of(1, 2, 3, 4), number -> number % 2 == 0)
            .collect(Collectors.toList());
    
    assertThat(transformed, hasSize(2));
    assertThat(transformed, contains(2, 4));
    ```
