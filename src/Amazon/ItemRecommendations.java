package Amazon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class ItemRecommendations {
    
    static class Pair {
        HashSet<String> set;
        double count;
        public Pair(double count) {
            this.count = count;
            set = new HashSet<String>();
        }
    }

    public static void main(String[] args) {
        try{
            BufferedReader br = 
                          new BufferedReader(new InputStreamReader(System.in));
            
            String input;
            ArrayList<Pair> array = new ArrayList<Pair>();
            //While we have input on stdin
            while((input=br.readLine()) != null){
                String[] list = input.split(" ");
                String first = list[0];
                String second = list[1];
                String third = list[2];
                double d = Double.parseDouble(third);
                if (array.size() == 0) {
                    System.out.println("aaa");
                    Pair pair = new Pair(d);
                    pair.set.add(first);
                    pair.set.add(second);
                    array.add(pair);
                    
                } else {
                    System.out.println("bbbb");
                    boolean check = true;
                    for (int i = 0; i < array.size(); i++) {
                        HashSet<String> set = array.get(i).set;
                        if (set.contains(first) || set.contains(second)) {
                            set.add(first);
                            set.add(second);
                            array.get(i).count += d;
                            check = false;
                        }
                    }
                    
                    if (check) {
                        Pair pair = new Pair(d);
                        HashSet<String> set = pair.set;
                        set.add(first);
                        set.add(second);
                        array.add(pair);
                    }
                }
            }
            System.out.println("Ffd");
            for (int i = 0; i < array.size(); i++) {
                HashSet<String> set = array.get(i).set;
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    System.out.print(it.next() + " ");
                }
                System.out.println("*********");
            }
            
        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
//    public static int[] determineRecommendations(String itemId, String[] purchases) {
//        int[] result = new int[2];
//        if (purchases == null) {
//            return result;
//        }
//    }
}
