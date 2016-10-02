package Amazon;

import java.util.*;


public class Airplane {
    
    static class Pair {
        char move;
        int count;
        public Pair(char move, int count) {
            this.move = move;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        String s = "D1";
        int[] location = new int[2];
        airplane(s, location);
        System.out.println(Arrays.toString(location));
    }
    
    public static void airplane(String str, int[] location) {
        ArrayList<String> array = new ArrayList<String>();
        location[0] = 999;
        location[1] = 999;
        int index = 0;
        while (str.length() > 0) {
            if (index < str.length() && (str.charAt(index) == 'U' || str.charAt(index) == 'D' || str.charAt(index) == 'L' || str.charAt(index) == 'R' || str.charAt(index) == 'X')) {
                String current = str.substring(0, index + 1);
                str = str.substring(index + 1);
                System.out.println(current);
                array.add(current);
                index = 0;
            } else if (index < str.length() && str.charAt(index) >= '0' && str.charAt(index) <= '9') {
                index++;
            } else {
                return;
            }
        }
        
        ArrayList<Pair> ps = new ArrayList<Pair>();
        for (int i = 0; i < array.size(); i++) {
            String s = array.get(i);
            if (s.charAt(0) == '0') {
                return;
            }
            Pair pair = getPair(s);
            ps.add(pair);
        }
        
        ArrayList<Pair> left = new ArrayList<Pair>();
        for (int i = 0; i < ps.size(); i++) {
            Pair pair = ps.get(i);
            if (pair.move == 'X') {
                //System.out.println("aa");
                int count = pair.count;
                for (int j = 0; j < count; j++) {
                    if (left.size() > 0) {
                        //System.out.println("ds");
                        left.remove(left.size() - 1);
                    }
                }
            } else {
                left.add(pair);
            }
        }
        
        location[0] = 0;
        location[1] = 0;
        for (int i = 0; i < left.size(); i++) {
            Pair pair = left.get(i);
            char move = pair.move;
            int count = pair.count;
            if (move == 'U') {
                location[1] += count;
            }
            if (move == 'D') {
                location[1] -= count;
            }
            if (move == 'L') {
                location[0] -= count;
            }
            if (move == 'R') {
                location[0] += count;
            }
        }
        
    }
    
    
    public static Pair getPair(String str) {
        char move = '\0';
        int count = 0;
        if (str.charAt(0) < '0' || str.charAt(0) > '9') {
            count = 1;
            move = str.charAt(0);
            return new Pair(move, count);
        }
        String number = "";
        
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                number += str.charAt(i);
            } else {
                move = str.charAt(i);
            }
        }
        count = Integer.parseInt(number);
        return new Pair(move, count);
        
    }
}