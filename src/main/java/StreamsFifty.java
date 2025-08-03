import entities.Employee;
import entities.EmployeeDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsFifty {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1L, "Alice", "HR", 50000, new Date(120, 0, 10)),
                new Employee(2L, "Bob", "IT", 60000, new Date(119, 2, 15)),
                new Employee(3L, "Charlie", "Finance", 55000, new Date(121, 4, 20)),
                new Employee(4L, "David", "IT", 62000, new Date(118, 6, 25)),
                new Employee(5L, "Eve", "HR", 51000, new Date(122, 1, 5)),
                new Employee(6L, "Frank", "Finance", 53000, new Date(120, 7, 12)),
                new Employee(7L, "Grace", "IT", 61000, new Date(119, 10, 30)),
                new Employee(8L, "Heidi", "HR", 52000, new Date(121, 3, 18)),
                new Employee(9L, "Ivan", "Finance", 54000, new Date(122, 5, 22)),
                new Employee(10L, "Judy", "IT", 63000, new Date(118, 8, 14)),
                new Employee(11L, "Kevin", "HR", 50000, new Date(120, 0, 10)),
                new Employee(12L, "Laura", "Finance", 60000, new Date(119, 2, 15)),
                new Employee(13L, "Mike", "IT", 55000, new Date(121, 4, 20)),
                new Employee(14L, "Nina", "Finance", 62000, new Date(118, 6, 25)),
                new Employee(15L, "Oscar", "HR", 51000, new Date(122, 1, 5)),
                new Employee(16L, "Paul", "IT", 53000, new Date(120, 7, 12)),
                new Employee(17L, "Quinn", "Finance", 61000, new Date(119, 10, 30)),
                new Employee(18L, "Rita", "HR", 52000, new Date(121, 3, 18)),
                new Employee(19L, "Sam", "IT", 54000, new Date(122, 5, 22)),
                new Employee(20L, "Tina", "Finance", 63000, new Date(118, 8, 14)));

        // employees.forEach(System.out::println);
        List<Integer> randomNumbers = new Random().ints(1, 51).distinct().limit(5).boxed().collect(Collectors.toList());
        System.out.println(randomNumbers);
    }

    // 1. Find all employees whose name starts with A
    public static List<Employee> nameStartsWithA(List<Employee> list){
        List<Employee> res = new ArrayList<>();
        res = list.stream().filter(e -> e.getName().startsWith(("A"))).toList();
        return res;
    }

    // 2. Convert Strings to Uppercase
    public static List<Employee> convertToUpperCase(List<Employee> list){
        List<Employee> res = new ArrayList<>();
        res = list.stream().map(e ->{
            e.setName(e.getName().toUpperCase());
            return e;
        }).toList();
        list.stream().map(e -> e.getName().toUpperCase()).toList();
        res = list.stream().map(e -> new Employee(e.getId(), e.getName().toUpperCase(), e.getDepartment(), e.getSalary(), e.getJoiningDate()) ).toList();
        return res;
    }

    // 3. sort list of employees in descending order of their salary
    public static List<Employee> descendingOrderOfSalary(List<Employee> list){
        List<Employee> res = new ArrayList<>();
        res = list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).toList();
        res = list.stream().sorted((e1, e2) -> e2.getSalary() - e1.getSalary()).toList();
        res = list.stream().sorted((e1, e2) -> Integer.compare(e2.getSalary(), e1.getSalary())).toList();
        return res;
    }

    // 4. remove duplicates
    public static List<Employee> removeDuplicates(List<Employee> list){
        return list.stream().filter(Objects::nonNull).distinct().toList();

    }

    // 5. sum of all salaries
    public static Integer sumOfSalaries(List<Employee> list){
      return list.stream().map(Employee::getSalary).reduce(0, Integer::sum);
    }

    // 6. Find Employee with max salary
    public static Employee employeeWithMaxSalary(List<Employee> list){
        Employee res = null;
        list.stream().max((e1, e2) -> e2.getSalary() -e1.getSalary()).orElse(null);
        return list.stream().max(Comparator.comparingInt(Employee::getSalary)).orElse(null);

    }

    // 7. Find EMployee with min salary
    public static Employee employeeWithMinSalary(List<Employee> list){
        Employee emp = null;
        emp = list.stream().min((e1, e2) -> e1.getSalary()-e2.getSalary()).orElse(null);
        int minSal = list.stream().map(Employee::getSalary).filter(Objects::nonNull).max(Comparator.comparingInt(Integer::intValue)).orElse(0);
        emp = list.stream().min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
        int minSalary = list.stream().mapToInt(Employee::getSalary).min().orElse(0);
        return emp;
    }

    // 8. Find average salary of employees
    public static Double avgSalaryofEmployees(List<Employee> list){
        Double res = list.stream().filter(Objects::nonNull).mapToInt(Employee::getSalary).average().orElse(0);
        return res;
    }

    // 9. Skip first three employees
    public static List<Employee> skipFirstThree(List<Employee> list){
        List<Employee> res = new ArrayList<>();
        res = list.stream().skip(3).toList();
        return list;
    }

    // 10. Limit to the top 5 employees based on salary.
    public static List<Employee> topFiveEmployees(List<Employee> list){
        List<Employee> res = new ArrayList<>();
        res = list.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).limit(5).toList();
        return res;
    }

    // 11. Find second Highest Salary
    public static List<Employee> secondHighestSalary(List<Employee> list){
        List<Integer> salaries = list.stream().map(Employee::getSalary).filter(Objects::nonNull).distinct().sorted().toList();
        Collections.reverse(salaries);
        return list.stream().filter(e -> e.getSalary() == salaries.get(1)).toList();
    }

    // 12. Find Nth Highest Salary.
    public static Integer nthHighestSalary(List<Employee> list, Integer n){
        Integer res = 0;
        List<Integer> resList = list.stream().map(Employee::getSalary).filter(Objects::nonNull).sorted(Comparator.comparingInt(Integer::intValue).reversed()).toList();

        List<Integer> sortedSalaries = list.stream()
                .map(Employee::getSalary)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(() -> new TreeSet<Integer>(Comparator.reverseOrder())))
                .stream().toList();
        return resList.get(n);
    }

    // 13. Group Employees By Department
    public static Map<String, List<Employee>> groupByDept(List<Employee> list){
        return list.stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    // 14. Compute how many employees in each Department.
    public static Map<String, Integer> noOfEmployeesinEachDept(List<Employee> list){
        return list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    // 15. Partition Employees by salary > 50000
    public static Map<Boolean, List<Employee>> partitionEmployees(List<Employee> list){
        Map<Boolean, List<Employee>> res = list.stream().collect(Collectors.groupingBy(e->e.getSalary()>50000));
        return list.stream().collect(Collectors.partitioningBy(e -> e.getSalary()>50000));
    }

    // 16. Convert a list of employees to a Map<name, salary>
    public static Map<String, Integer> mapOfNameSalary(List<Employee> list){
        return list.stream().collect(Collectors.toMap(e -> e.getName(), e-> e.getSalary(), (e1,e2) -> e2));
    }

    // 17. Convert list of Employee to List<String> of names.
    public static List<String> listOfNames(List<Employee> list){
        return list.stream().map(Employee::getName).filter(Objects::nonNull).toList();
    }

    // 18. Filter a list of salaries to keep only even numbers.
    public static List<Integer> evenSalaries(List<Employee> list){
        return list.stream().map(Employee::getSalary).filter(Objects::nonNull).filter(n -> n%2==0).toList();
    }

    // 19. Check if any employee has salary > 100,000.
    public static Boolean checkSalary(List<Employee> list){
        return list.stream().map(Employee::getSalary).anyMatch(s -> s > 100000);
    }

    // 20. Check if all employees have salary > minimum wage.
    public static Boolean checkForMinWage(List<Employee> list){
        Boolean res = list.stream().allMatch(e -> e.getSalary() > 50000);
        return list.stream().filter(e -> e.getSalary() < 50000).findAny().isPresent();
    }

    // 21. Flattening the list of list of Integers
    public static List<Integer> flattenList(List<Employee> list){
        return list.stream().collect(Collectors.groupingBy(Employee::getSalary, Collectors.collectingAndThen(Collectors.counting(), Long::intValue))).values().stream().toList();
    }

    // 22. Count how many times each word appears in a sentence.
    public static Map<String, Integer> countWordOccurences(){
        String str = "Hello Word Hello Giva Hello Aravind";
        return List.of(str.split(" ")).stream().collect(Collectors.groupingBy(word -> word, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
    }

    // 23. Convert a comma-separated string into a list of strings.
    public static List<String> listOfStrings(List<Employee> list){
        return List.of(list.stream().map(Employee::getName).collect(Collectors.joining(",")).split(","));

    }

    // 24. Join employee names into a single string separated by commas.
    public static String joinIntoSingleString(List<Employee> list){
        return list.stream().map(Employee::getName).collect(Collectors.joining(","));
    }

    // 25. Remove nulls from a list of strings using streams.
    public static List<String> removeNullStrings(List<Employee> list){
        return list.stream().map(Employee::getName).filter(Objects::nonNull).toList();
    }

    // 26. Group employees by department and find the highest paid in each.
    public static Map<String, Employee> highestSalaryinDepts(List<Employee> list){
        return list.stream().collect(Collectors.toMap(Employee::getDepartment, Function.identity(), (e1, e2) -> e2.getSalary() >= e2.getSalary() ? e2 : e1 ));
    }

    // 27.  Group employees by joining year (based on Date field).
    public static Map<Integer, List<Employee>> groupByJoiningYear(List<Employee> list){
        Map<Integer, List<Employee>> map = list.stream().collect(
                Collectors.groupingBy((e ->{
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(e.getJoiningDate());
                    return cal.get(Calendar.YEAR);
                }))
        );
        return list.stream().collect(Collectors.groupingBy(e -> e.getJoiningDate().getYear()));
    }

    // 28. Sort employees by name, and if equal, by salary.
    public static List<Employee> sortByNameandSalary(List<Employee> list){
        list.stream().max(Comparator.comparing(Employee::getSalary).thenComparing(e -> e.getJoiningDate().getTime())).orElse(null);
        return list.stream().sorted(Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary).reversed()).toList();
    }

    // 29. Convert a list of strings to a map of word frequency.
    public Map<String, Integer> wordFrequencymap(List<Employee> list){
        Map<String, Integer> freqMap = list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
        return freqMap;
    }

    // 30. Find all employees who joined in the last 6 months.
    public static List<Employee> lastJoinedEmployees(List<Employee> list){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -6);
        Date sixMonthsAgo = cal.getTime();
        return list.stream().filter(e -> e.getJoiningDate().after(sixMonthsAgo)).toList();
    }

    // 31. Count characters in a string and store as Map<Character, Long>.
    public static Map<Character, Long> countCharacters(List<Employee> list){
        return List.of(list.stream().map(Employee::getName).collect(Collectors.joining("")).split(""))
                .stream().map(s -> s.charAt(0)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    // 32. Find duplicate elements in a list.
    public static List<String> findDuplicateNames(List<Employee> list){
        Set<String> set = new HashSet<>();
        return list.stream().map(Employee::getName).filter(Objects::nonNull).filter(s -> !set.add(s)).toList();
    }

    // 33. Count duplicates in a list and return Map<value, count>.
    public static Map<String, Long> duplicatesCount(List<Employee> list){
        Set<String> names = new HashSet<>();
        return list.stream().filter(e -> !names.add(e.getName())).map(Employee::getName).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    // 34. Get a distinct list of salaries in sorted order.
    public static List<Integer> distinctSalariesSorted(List<Employee> list){
        return list.stream().map(Employee::getSalary).distinct().sorted().toList();
    }

    // 35. Custom comparator to sort strings by length.
    public static List<String> sortStringsByLength(List<Employee> list){
        List<String> res = list.stream().map(Employee::getName).sorted((e1, e2) -> (e1.length() -e2.length())).toList();
        return list.stream().map(Employee::getName).sorted(Comparator.comparingInt(String::length)).toList();
    }


    // 36. Handle a list of employees where some salaries are null.
    public static List<Employee> handleNulls(List<Employee> list){
        return list.stream().filter(Objects::nonNull).toList();
    }

    // 37. Remove blank or empty strings from a list.
    public static List<String> removeNullString(List<Employee> list){
        return list.stream().map(Employee::getDepartment).filter(Objects::nonNull).toList();
    }

    // 38. Replace nulls with default values using map().
    public static List<Employee> mapNullsNamesWithDefault(List<Employee> list){
        return list.stream().map(e -> {
            if(e.getName() == null){
                e.setName("Default");
            }
            return e;
        }).toList();
    }

    // 39. Safely find the max from an empty list.
    public static Integer safelyFindMax(List<Employee> list){
        Optional<Integer> res =  list.stream().map(Employee::getSalary).filter(Objects::nonNull).max(Integer::compareTo) ;
        return res.orElse(0);
    }

    // 40. Filter and collect to an Optional<List<Employee>> if non-empty.
    public static List<Employee> filterToOptional(List<Employee> list){
        List<Employee> opt = list.stream().filter(e -> e.getName().equals("ANI")).toList();
        return opt;
    }

    // 41. Collect employees into a TreeSet by name.
    public static TreeSet<Employee> collectToTreeSet(List<Employee> list){
        return list.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Employee::getName))));
    }

    // 42. Collect salaries into an unmodifiable list.
    public static List<Integer> collectToUnModifiableList(List<Employee> list){
        return Collections.unmodifiableList(list.stream().map(Employee::getSalary).toList());
    }

    // 43. Convert a list of strings into a Set.
    public static Set<String> setOfStrings(List<Employee> list){
        return list.stream().map(Employee::getName).collect(Collectors.toSet());
    }

    // 44. Convert a stream of key-value pairs into a Map.
    public static Map<String, Integer> keyValuesToMap(List<Employee> list){
        return list.stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary));
    }

    // 45. Find top 3 highest-paid employees per department.
    public static Map<String, List<Employee>> topThreeEmployeesEachDept(List<Employee> list){
       return list.stream().collect((Collectors.groupingBy(
               Employee::getName, Collectors.collectingAndThen(
                       Collectors.toList(), empList -> empList.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).limit(3).toList()
                       )
       )));
    }

    // 46. Group employees by salary range (e.g. 20–30, 31–40, etc.).
    public static Map<Integer, List<Employee>> groupByrange(List<Employee> list){
        return list.stream().collect(Collectors.groupingBy(e -> {
            int salary = e.getSalary();
            if(salary>=30000 && salary<40000){
                return 30;
            } else if(salary >= 40000 && salary <50000){
                return 40;
            }
            return 50;
        }));
    }
//    list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
//            .entrySet().stream().max(Map.Entry.comparingByValue()) // max by avg salary
//            .map(Map.Entry::getKey)
//                .orElse("No Department");

    // 47. Count number of characters in each employee’s name.
    public static Map<String, Integer> charactersInName(List<Employee> list){
        return list.stream().map(Employee::getName).collect(Collectors.toMap(Function.identity(), e -> e.length(), (e1, e2) -> e2));
    }

    // 48. Sort employees by last character of their name.
    public static List<Employee> sortByLastNameCharacter(List<Employee> list){
        return list.stream().sorted((e1, e2) -> e1.getName().charAt(e1.getName().length()-1) - e2.getName().charAt(e2.getName().length()-1)).toList();
    }

    // 49. Map employees to DTOs (e.g. EmployeeDTO) using map().
    public static List<EmployeeDTO> mapToDTOS(List<Employee> list){
        return list.stream().map(e -> new EmployeeDTO(e.getName())).toList();
    }

    // 50. Find the department with the highest average salary.
    public static String DeptwithHigehestAvgSalary(List<Employee> list){
        return list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("NO DEPT");
    }

}