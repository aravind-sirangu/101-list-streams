import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    List<String> list1 = List.of("Alice", "Bob", "Charlie", "David", "Eve", "Alice", "Charlie");
    List<Integer> list2 = List.of(1,2,5,7,9,2,29,66,46);

    public static void main(String[] args) {

    }

    
    // Creating streams
    public void createStreams(){

        List<String> list = List.of("a", "b", "c");
        Stream<String> listStream = list.stream();

        String[] array = {"a", "b", "c"};
        Stream<String> arrayStream = Arrays.stream(array);

        Stream<Integer> stream = Stream.iterate(0, n->n+2);

        Stream<String> stream1 = Stream.of("a", "b", "c");

        Stream<Double> stream2 = Stream.generate(Math::random);

    }


    // Intermediate Operations
    public void intermediateOperations(){
        //Map
        List<String> list1Map1 = list1.stream().map(s1->s1.concat("a"))
                .collect(Collectors.toList());
        List<Integer> list1Map2 = list1.stream().map(String::length).collect(Collectors.toList());
        List<String> list1Map3 = list1.stream().map(s-> new StringBuilder(s).reverse().toString()).collect(Collectors.toList());
        List<Integer> list2Map1 = list2.stream().map(i->i/2).collect(Collectors.toList());


    }








}
