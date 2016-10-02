package Amazon;

import java.util.*;


public class DonutMapper {
    
    static class Pair {
        public String name;
        public PriorityQueue<String> tastes;
        public Pair(String name) {
            this.name = name;
            tastes = new PriorityQueue<String>(1, new Comparator<String>() {
                public int compare(String left, String right) {
                    return left.compareTo(right);
                }
            });
        }
    }
    
    public static void main(String[] args) {
        String[][] donuts = {{"cruller", "vegan"}, {"beignet", "coffee"}};
        String[][] people = {{"rob", "coffee"}, {"beth", "vegan"}};
        
        String[][] donuts2 = {{"cruller", "vegan"}, {"eclair", "chocolate"}};
        String[][] people2 = {{"jose", "vegan"}, {"john", "chocolate"}, {"mary", "*"}};
        String[][] result = DonutMapper(donuts2, people2);
        System.out.println(Arrays.deepToString(result));
    }
    
    public static String[][] DonutMapper(String[][] donuts, String[][] people) {
        HashMap<String, ArrayList<String>> donutMap = new HashMap<String, ArrayList<String>>();
        donutMap.put("*", new ArrayList<String>());
        for (int i = 0; i < donuts.length; i++) {
            String type = donuts[i][1];
            String donut = donuts[i][0];
            if (donutMap.containsKey(type)) {
                donutMap.get(type).add(donut);
            } else {
                ArrayList<String> array = new ArrayList<String>();
                array.add(donut);
                donutMap.put(type, array);
            }
            donutMap.get("*").add(donut);
        }
        
        PriorityQueue<Pair> q = new PriorityQueue<Pair>(1, new Comparator<Pair>() {
            public int compare(Pair left, Pair right) {
                return left.name.compareTo(right.name);
            }
        });
        
        PriorityQueue<Pair> q2 = new PriorityQueue<Pair>(1, new Comparator<Pair>() {
            public int compare(Pair left, Pair right) {
                return left.name.compareTo(right.name);
            }
        });
        
        for (int i = 0; i < people.length; i++) {
            String peopleName = people[i][0];
            String type = people[i][1];
            ArrayList<String> donutArray = donutMap.get(type);
            Pair pair = new Pair(peopleName);
            PriorityQueue<String> donutsQueue = pair.tastes;
            for (int j = 0; j < donutArray.size(); j++) {
                donutsQueue.add(donutArray.get(j));
            }
            q.add(pair);
            q2.add(pair);
        }
        
        int count = 0;
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int size = pair.tastes.size();
            count += size;
        }
        String[][] result = new String[count][2];
        int index = 0;
        while (!q2.isEmpty()) {
            Pair pair = q2.poll();
            PriorityQueue<String> queue = pair.tastes;
            while (!queue.isEmpty()) {
                String donut = queue.poll();
                result[index][0] = pair.name;
                result[index][1] = donut;
                index++;
            }
        }
        return result;
    }
}
