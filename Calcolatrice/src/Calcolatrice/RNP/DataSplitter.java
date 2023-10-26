package Calcolatrice.RNP;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataSplitter {



    public static String[] split(String s) {
        List<String> ris = new ArrayList<>();
        int index = 0;

        for (int i = 0; i < s.length(); i++)
            if (!Character.isDigit(s.charAt(i))) {
                String str = s.substring(index, i);
                String op = String.valueOf(s.charAt(i));
                ris.add(str);
                ris.add(op);
                index = i+1;

            }

        ris.removeIf(String::isEmpty);
            ris.add(s.substring(index));

        return ris.toArray(new String[0]);

    }
}
