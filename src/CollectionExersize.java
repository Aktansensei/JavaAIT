import java.util.*;
import java.util.Collections;
import java.util.function.*;

public class CollectionExersize {
    public static void main(String[] args) {
        // ArrayList
        // 1. Swap two elements in an array list
        List<String> al1= new ArrayList<>(Arrays.asList("red", "green", "black", "white", "pink"));

        System.out.println("Array list before Swap:");
        for(String a: al1){
            System.out.println(a);
        }
        Collections.swap(al1, 1,3);
        System.out.println("Array list after swap:");
        for(String b: al1){
            System.out.println(b);
        }

        // 2. Write a Java program to extract a portion of an array list
        List<String> al2 = new ArrayList<>(Arrays.asList("apple", "banana", "potato", "cucumber", "grape"));
        al2.subList(2,3); //potato, cucumber
        for(String s: al2) {
            System.out.println(s);
        }

        // 3. Write a Java program to empty an array list
        List<Integer> al3 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        System.out.println(al3);
        al3.clear();
        System.out.println(al3);

        // 4. Write a Java program to replace the second element of an ArrayList with the specified element.
        List<Integer> al4 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Consumer<Integer> fal4 = i -> Collections.swap(al4, 1, i);
        fal4.accept(3);
        System.out.println(al4);

        // 5. Write a Java program to increase an array list size
        ArrayList<Integer> al5 = new ArrayList<>(3);
        Consumer<Integer> fal5 = al5::ensureCapacity;
        fal5.accept(6);


        //LinkedList
        // 1.Write a Java program to remove the first and last elements from a linked list
        LinkedList<Integer> ll1 = new LinkedList<>(Arrays.asList(1,2,3,4,5,6,1));
        Function<LinkedList<Integer>, LinkedList<Integer>> fll1 = list -> {
            list.removeFirst();
            list.removeLast();
            return list;
        };
        ll1 = fll1.apply(ll1);
        System.out.println(ll1);

        // 2.Write a Java program to join two linked lists
        BiFunction<LinkedList<Integer>, LinkedList<Integer>, LinkedList<Integer>> fll2 = (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
        LinkedList<Integer> ll2 = fll2.apply(new LinkedList<>(Arrays.asList(1,1,1,1,1)), new LinkedList<>(Arrays.asList(0,0,0,0,0)));
        System.out.println(ll2);

        // 3. Write a Java program to remove and return the first element of a linked list
        LinkedList<String> ll3 = new LinkedList<>(Arrays.asList("first", "second", "third"));
        System.out.println(ll3.poll());
        System.out.println(ll3);

        // 4. Write a Java program to retrieve, but not remove, the first element of a linked list
        LinkedList<String> ll4 = new LinkedList<>(Arrays.asList("first", "second", "third"));
        System.out.println(ll4.peek());
        System.out.println(ll4);

        // 5. Write a Java program to check if a particular element exists in a linked list
        LinkedList<String> ll5 = new LinkedList<>(Arrays.asList("first", "second", "third"));
        System.out.println(ll5.contains("second"));

        // 6. Write a Java program to compare two linked lists
        BiPredicate<LinkedList<Integer>, LinkedList<Integer>> fll6 = (l1, l2) -> {
            if(l1.size() == l2.size()) {
                for (int i = 0; i < l1.size(); i++) {
                    if(l1.get(i) != l2.get(i)) return false;
                }
                return true;
            }
            return false;
        };
        boolean compare = fll6.test(new LinkedList<>(Arrays.asList(1,1,1,1,1,1)), new LinkedList<>(Arrays.asList(1,1,1,1,1)));
        System.out.println(compare);


        // HashSet
        // 1. Write a Java program to clone a hash set to another hash set
        HashSet<Integer> hs1 = new HashSet<>(Arrays.asList(1,2,2,3));
        HashSet<Integer> hs1_copy = (HashSet<Integer>) hs1.clone();
        hs1_copy.add(5);
        System.out.println(hs1);
        System.out.println(hs1_copy);

        // 2. Write a Java program to convert a hash set to an array
        HashSet<Integer> hs2 = new HashSet<>(Arrays.asList(5,1,2,2,3));
        Integer[] hs2_array = new Integer[hs2.size()];
        hs2.toArray(hs2_array);
        System.out.println(hs2_array);

        // 3. Write a Java program to convert a hash set to a tree set
        HashSet<Integer> hs3 = new HashSet<>(Arrays.asList(5,1,2,2,3));
        Function<HashSet<Integer>, TreeSet<Integer>> fhs3 = set -> new TreeSet<>(set);
        System.out.println(hs3);
        System.out.println(fhs3.apply(hs3));

        // 4. Write a Java program to compare two hash set
        HashSet<Integer> hs41 = new HashSet<>(Arrays.asList(5,1,2,2,3));
        HashSet<Integer> hs42 = new HashSet<>(Arrays.asList(5,1,2,5,3));
        System.out.println(hs41.equals(hs42));

        // 5. Write a Java program to compare two sets and retain elements that are the same
        HashSet<Integer> hs51 = new HashSet<>(Arrays.asList(5,1,2,6,7));
        HashSet<Integer> hs52 = new HashSet<>(Arrays.asList(2,5,3,4));
        BiFunction<HashSet<Integer>, HashSet<Integer>, HashSet<Integer>> fhs5 = (set1, set2) -> {
            HashSet<Integer> result = new HashSet<>();
            for(Integer i: set1) {
                if(set2.contains(i)) {
                    result.add(i);
                }
            }
            return result;
        };
        System.out.println(fhs5.apply(hs51, hs52));
        System.out.println(hs51.retainAll(hs52));
        System.out.println(hs51);

        // 6. Write a Java program to remove all elements from a hash set
        HashSet<Integer> hs6 = new HashSet<>(Arrays.asList(5,1,2,6,7));
        hs6.clear();


        // TreeSet
        // 1. Write a Java program to get the element in a tree set less than or equal to the given element
        TreeSet<Integer> ts1 = new TreeSet<>(Arrays.asList(5,8,9,4,1,2,6,7));
        Function<Integer, Integer> fts1 = num -> {
            for(Integer el: ts1) {
                if(el <= num) return el;
            }
            return null;
        };
        System.out.println(fts1.apply(3));
        System.out.println(ts1.ceiling(3));

        // 2. Write a Java program to get the element in a tree set strictly greater than or equal to the given element.
        TreeSet<Integer> ts2 = new TreeSet<>();
        ts2.add(5);
        ts2.add(8);
        ts2.add(4);
        ts2.add(1);
        System.out.println(ts2.floor(4));

        // 3. Write a Java program to get an element in a tree set that has a lower value than the given element
        TreeSet<Integer> ts3 = new TreeSet<>(Arrays.asList(5,8,9,4,1,2,6,7));
        System.out.println(ts1.lower(2));

        // 4. Write a Java program to retrieve and remove the first element of a tree set
        TreeSet<Integer> ts4 = new TreeSet<>(Arrays.asList(5,8,9,4,1,2,6,7));
        System.out.println(ts4.removeFirst());

        // 5. Write a Java program to retrieve and remove the last element of a tree set.
        TreeSet<Integer> ts5 = new TreeSet<>(Arrays.asList(5,8,9,4,1,2,6,7));
        System.out.println(ts4.removeLast());

        // 6. Write a Java program to remove a given element from a tree set.
        TreeSet<Integer> ts6 = new TreeSet<>(Arrays.asList(5,8,9,4,1,2,6,7));
        System.out.println(ts4.remove(9));


        // PriorityQueue
        // 1. Write a Java program to compare two priority queues
        PriorityQueue<String> pq11 = new PriorityQueue<>(Arrays.asList("task1", "task3", "task2"));
        PriorityQueue<String> pq12 = new PriorityQueue<>(Arrays.asList("task1", "task2", "task3"));

        System.out.println("pq");
        if (pq11.size() == pq12.size()) {
            if (pq11.containsAll(pq12)) System.out.println(true);
            else System.out.println(false);
        }

        // 2. Write a Java program to retrieve the first element of the priority queue
        PriorityQueue<String> pq2 = new PriorityQueue<>(Arrays.asList("task1", "task3", "task2"));
        System.out.println(pq2.peek());

        // 3. Write a Java program to retrieve and remove the first element.
        PriorityQueue<String> pq3 = new PriorityQueue<>(Arrays.asList("task1", "task3", "task2"));
        System.out.println(pq2.poll());

        // 4. Write a Java program to convert a priority queue to an array containing all its elements.
        PriorityQueue<String> pq4 = new PriorityQueue<>(Arrays.asList("task1", "task3", "task2"));
        String[] pq4_array = new String[pq4.size()];
        pq4.toArray(pq4_array);
        System.out.println(pq4_array);

        // 5. Write a Java program to count the number of elements in a priority queue
        PriorityQueue<String> pq5 = new PriorityQueue<>(Arrays.asList("task1", "task3", "task2"));
        System.out.println(pq5.size());

        // 6. Write a Java program to remove all elements from a priority queue.
        PriorityQueue<String> pq6 = new PriorityQueue<>(Arrays.asList("task1", "task3", "task2"));
        pq6.clear();
        System.out.println(pq6);


        // HashMap
        // 1. Write a Java program to test if a map contains a mapping for the specified value
        HashMap<Integer, String> hm = new HashMap<>();
        hm.put(1, "item1");
        hm.put(2, "item2");
        hm.put(3, "item3");
        hm.put(4, "item4");
        System.out.println(hm);
        System.out.println(hm.containsValue("item2"));

        // 2. Write a Java program to create a set view of the mappings contained in a map
        Set set = hm.entrySet();
        System.out.println(set);

        // 3. Write a Java program to get the value of a specified key in a map
        System.out.println(hm.get(2));

        // 4. Write a Java program to get a set view of the keys contained in this map
        System.out.println(hm.keySet());

        // 5. Write a Java program to get a collection view of the values contained in this map
        System.out.println(hm.values());

        // 6. Write a Java program to remove all mappings from a map
        hm.clear();
        System.out.println(hm);


        // TreeMap
        // 1. Write a Java program to remove and get a key-value mapping associated with the greatest key in this map
        TreeMap<Integer, String> tm = new TreeMap<>();
        tm.put(1, "item1");
        tm.put(4, "item4");
        tm.put(3, "item3");
        tm.put(2, "item2");
        System.out.println(tm);
        System.out.println(tm.pollLastEntry());
        System.out.println(tm);
        tm.put(4, "item4");

        // 2. Write a Java program to get the portion of a map whose keys range from a given key (inclusive) to another key (exclusive)
        System.out.println(tm.subMap(2,4));

        // 3. Write a Java program to get the portion of a map whose keys range from a given key to another key.
        System.out.println(tm.subMap(2, true, 4, true));

        // 4. Write a Java program to get a portion of a map whose keys are greater than or equal to a given key
        System.out.println(tm.tailMap(3));

        // 5. Write a Java program to get a portion of a map whose keys are greater than a given key
        System.out.println(tm.tailMap(3, false));

        // 6. Write a Java program to get a key-value mapping associated with the least key greater than or equal to the given key. Return null if there is no such key.
        System.out.println(tm.ceilingEntry(2));
    }
}
