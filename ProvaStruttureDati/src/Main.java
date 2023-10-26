import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        var start = System.nanoTime();
        //var start = System.currentTimeMillis();
        LinkedList<String> linkList = new LinkedList<>();
        //var end = System.currentTimeMillis();
        var end = System.nanoTime();

        var time = end - start;
        System.out.println("Tempo trascorso linkedList: " + time);

        start = System.nanoTime();
        ArrayList<String> arrList= new ArrayList<>();
        end = System.nanoTime();
        time = end - start;
        System.out.println("Tempo trascorso arrList: " + time);

        start = System.nanoTime();
        TreeMap<String, String> treeMap = new TreeMap<>();
        end = System.nanoTime();
        time = end - start;
        System.out.println("Tempo trascorso Treemap: " + time);

        start = System.nanoTime();
        HashMap<String, Integer> hashmap = new HashMap<>();
        end = System.nanoTime();
        time = end - start;
        System.out.println("Tempo trascorso HashMap: " + time);


        LinkedList<String> linkedList = new LinkedList<>();
        TreeMap<Integer, Integer> treemap = new TreeMap<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        start = System.currentTimeMillis();

        for(int i = 0; i < 999999; i++)
            arrList.add("sium");

        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Inserimento arrayList: " + time);

        start = System.currentTimeMillis();

        for(int i = 0; i < 999999;  i++)
            linkedList.add("sium");

        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Inserimento linkedList: " + time);

        start = System.currentTimeMillis();

        for(int i = 0; i < 999999; i++)
            treemap.put(i, i);

        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Inserimento Treemap: " + time);

        start = System.currentTimeMillis();

        for(int i = 0; i < 999999; i++)
            hashMap.put(i,i);

        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Inserimento hashMap: " + time);



        start = System.currentTimeMillis();
            hashMap.remove(42);

        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Rimozione hashMap: " + time);

        start = System.currentTimeMillis();
        treemap.remove(42);

        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Rimozione treemap: " + time);

        start = System.currentTimeMillis();
        linkedList.remove(42);

        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Rimozione linkedList: " + time);

        start = System.currentTimeMillis();
        arrList.remove(42);

        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Rimozione arrayList: " + time);





    }
}