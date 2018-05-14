package Amazon;

import java.util.*;

//http://www.1point3acres.com/bbs/thread-281940-1-1.html

public class ItemAssociation {
	public static void main(String[] args) {
		String[] s1 = {"a", "b"};
		String[] s2 = {"b", "c"};
		String[] s3 = {"c", "y"};
		String[] s4 = {"d", "e"};
		String[] s5 = {"e", "f"};
		String[] s6 = {"g", "f"};
		String[][] items = new String[6][2];
		items[0] = s1;
		items[1] = s2;
		items[2] = s3;
		items[3] = s4;
		items[4] = s5;
		items[5] = s6;
        List<String> res = itemAssociation(items);
        for (String s : res) {
        	System.out.print(s + ",");
        }
    }
	
	public static List<String> itemAssociation(String[][] items) {
		return solution1(items);
	}
	
	//Union-find
	static HashMap<String, String> map;
	public static List<String> solution1(String[][] items) {
		map = new HashMap<String, String>();
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < items.length; i++) {
			String[] item = items[i];
			set.add(item[0]);
			set.add(item[1]);
			String f1 = find(item[0]);
			String f2 = find(item[1]);
			if (!f1.equals(f2)) {
				map.put(f1, f2);
			}
		}
		HashMap<String, List<String>> counts = new HashMap<String, List<String>>();
		List<List<String>> lists = new ArrayList<List<String>>();
		for (String s : set) {
			String f = find(s);
			if (counts.containsKey(f)) {
				counts.get(f).add(s);
			} else {
				List<String> list = new ArrayList<String>();
				lists.add(list);
				list.add(s);
				counts.put(f, list);
			}
		}
		Collections.sort(lists, new Comparator<List<String>>() {
			public int compare(List<String> left, List<String> right) {
				if (left.size() != right.size()) {
					return right.size() - left.size();
				} else {
					Collections.sort(left);
					Collections.sort(right);
					return left.toString().compareTo(right.toString());
				}
			}
		});
		return lists.get(0);
	}
	
	public static String find(String s) {
		if (!map.containsKey(s)) {
			map.put(s, s);
			return s;
		}
		if (map.get(s).equals(s)) {
			return s;
		} else {
			String val = find(map.get(s));
			map.put(s, val);
			return val;
		}
	}
}
