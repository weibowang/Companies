package Amazon;

import java.util.*;
//http://www.1point3acres.com/bbs/thread-280797-1-1.html

public class MenuRecommendation {
	public static void main(String[] args) {
        List<String> fav = new LinkedList<String>();
        List<String> types = new LinkedList<String>();
        fav.add("zhang,1");
        fav.add("john,2");
        fav.add("wang,3");
        fav.add("li,*");
        types.add("1,China");
        types.add("1,US");
        types.add("2,France");
        types.add("3,Japan");
        List<String> result = menuRecommendation(fav, types);
        for (String s : result) {
        	System.out.println(s);
        }
    }
	
	public static List<String> menuRecommendation(List<String> fav, List<String> types) {
		HashMap<String, HashSet<String>> typeMap = new HashMap<String, HashSet<String>>();
		HashSet<String> dishes = new HashSet<String>();
		for (String s : types) {
			String[] split = s.split(",");
			dishes.add(split[1]);
			if (typeMap.containsKey(split[0])) {
				typeMap.get(split[0]).add(split[1]);
			} else {
				HashSet<String> set = new HashSet<String>();
				set.add(split[1]);
				typeMap.put(split[0], set);
			}
		}
		List<String> res = new LinkedList<String>();
		for (String s : fav) {
			String[] split = s.split(",");
			if (split[1].equals("*")) {
				for (String dish : dishes) {
					res.add(split[0] + "," + dish);
				}
			} else {
				HashSet<String> set = typeMap.get(split[1]);
				for (String dish : set) {
					res.add(split[0] + "," + dish);
				}
			}
		}
		return res;
	}
}
