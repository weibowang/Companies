package Amazon;
import java.util.*;


public class ItemRecommendations {
    
    

    public static void main(String[] args) {
        String itemId = "ABC";
        String[] purchases = {"first:ABC", "first:HIJ", "sec:HIJ", "sec:KLM", "third:NOP", "fourth:ABC", "fourth:QRS", "first:DEF", "fifth:KLM", "fifth:TUV"};
        int[] result = determineRecommendations(itemId, purchases);
        System.out.println(Arrays.toString(result));
    
    }
    
    
    public static int[] determineRecommendations(String itemId, String[] purchases) {
        int[] result = new int[2];
        if (purchases == null) {
            return result;
        }
        HashSet<String> group = new HashSet<String>();
        HashSet<String> secondaryGroup = new HashSet<String>();
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();
        for (int i = 0; i < purchases.length; i++) {
        	String[] split = purchases[i].split(":");
        	if (map.containsKey(split[0])) {
        		HashSet<String> set = map.get(split[0]);
        		set.add(split[1]);
        	} else {
        		HashSet<String> set = new HashSet<String>();
        		set.add(split[1]);
        		map.put(split[0], set);
        	}
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
        	Map.Entry pair = (Map.Entry) it.next();
        	HashSet<String> current = (HashSet<String>) pair.getValue();
        	if (current.contains(itemId)) {
        		for (String s : current) {
        			group.add(s);
        		}
        	}
        }
        group.remove(itemId);
        result[0] = group.size();
        
        
        it = map.entrySet().iterator();
        while (it.hasNext()) {
        	Map.Entry pair = (Map.Entry) it.next();
        	HashSet<String> current = (HashSet<String>) pair.getValue();
        	boolean check = false;
        	for (String s : current) {
    			for (String str : group) {
    				if (s.equals(str)) {
    					check = true;
    				}
    			}
    		}
        	if (check) {
        		for (String s : current) {
        			if (!group.contains(s)) {
        				secondaryGroup.add(s);
        			}
        		}
        	}
        }
        
        result[1] = secondaryGroup.size();
        return result;
    }
}
