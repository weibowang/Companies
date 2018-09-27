package Google;

import java.util.*;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=351065&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio&page=1
 * 
 * http://yuanyi.gift/2018/04/21/google_keynote/
 * 
 * http://www.1point3acres.com/bbs/thread-200211-1-1.html
 * 
 */

public class TopKCandidates {
	public static class Vote {
		String name;
		int time;
		Vote(String name, int time) {
			this.name = name;
			this.time = time;
		}
	}
	public static void main(String[] args) {
		Vote v1 = new Vote("a", 1);
		Vote v2 = new Vote("a", 2);
		Vote v3 = new Vote("b", 5);
		Vote v4 = new Vote("c", 1000);
		Vote v5 = new Vote("b", 1005);
		Vote v6 = new Vote("b", 2000);
		List<Vote> votes = new ArrayList<Vote>();
		votes.add(v1);
		votes.add(v2);
		votes.add(v3);
		votes.add(v4);
		votes.add(v5);
		votes.add(v6);
		int t1 = 2001;
		
		String res1 = TopOneCandidate(votes, t1);
		System.out.println(res1);
		
		int t2 = 2000;
		int k = 1;
		List<String> res2 = TopKCandidate(votes, t2, k);
		for (String s : res2) {
			System.out.println(s);
		}
		
		List<String> names = new ArrayList<String>();
		names.add("b");
		names.add("a");
		int res3 = TopKCandidateII(votes, names);
		System.out.println(res3);
	}
	
	public static String TopOneCandidate(List<Vote> votes, int t) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Collections.sort(votes, new Comparator<Vote>() {
			public int compare(Vote left, Vote right) {
				return left.time - right.time;
			}
		});
		List<Vote> list = new ArrayList<Vote>();
		int max = 0;
		for (Vote v : votes) {
			if (map.containsKey(v.name)) {
				map.put(v.name, map.get(v.name) + 1);
			} else {
				map.put(v.name, 1);
			}
			if (map.get(v.name) > max) {
				list.add(v);
				max = map.get(v.name);
			}
		}
		return binarySearch(list, t);
	}
	
	public static String binarySearch(List<Vote> list, int t) {
		if (list.size() == 0 || t < list.get(0).time) {
			return "";
		}
		int left = 0;
		int right = list.size() - 1;
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (list.get(mid).time > t) {
				right = mid;
			} else {
				left = mid;
			}
		}
		if (list.get(right).time <= t) {
			return list.get(right).name;
		}
		return list.get(left).name;
	}
	
	static class Type {
		String name;
		int time;
		int count;
		Type(String name, int time, int count) {
			this.name = name;
			this.time = time;
			this.count = count;
		}
	}
	
	public static List<String> TopKCandidate(List<Vote> votes, int t, int k) {
		HashMap<String, Type> map = new HashMap<String, Type>();
		for (Vote v : votes) {
			if (v.time <= t) {
				if (map.containsKey(v.name)) {
					Type type = map.get(v.name);
					type.count++;
				} else {
					Type type = new Type(v.name, v.time, 1);
					map.put(v.name, type);
				}
			}
		}
		List<String> res = new LinkedList<String>();
		PriorityQueue<Type> pq = new PriorityQueue<Type>(11, new Comparator<Type>() {
			public int compare(Type left, Type right) {
				if (left.count == right.count) {
					if (left.time == right.time) {
						return left.name.compareTo(right.name);
					} else {
						return left.time - right.time;
					}
				}
				return left.count - right.count;
			}
		});
		for (String key : map.keySet()) {
			pq.add(map.get(key));
			if (pq.size() > k) {
				pq.poll();
			}
		}
		while (!pq.isEmpty()) {
			res.add(pq.poll().name);
		}
		return res;
	}
	
	
	//	
	//II. followup, 一样给vote list, K, 但这次给Top K list, 找时间T，给定一个List of name
	public static int TopKCandidateII(List<Vote> votes, List<String> names) {
		TreeSet<Type> set = new TreeSet<Type>(new Comparator<Type>(){
			public int compare(Type left, Type right) {
				if (left.count == right.count) {
					return left.name.compareTo(right.name);
				}
				return right.count - left.count;
			}
		});
		HashMap<String, Type> map = new HashMap<String, Type>();
		Collections.sort(votes, new Comparator<Vote>() {
			public int compare(Vote left, Vote right) {
				return left.time - right.time;
			}
		});
		for (Vote v : votes) {
			Type type = null;
			if (map.containsKey(v.name)) {
				type = map.get(v.name);
				type.count++;
			} else {
				type = new Type(v.name, v.time, 1);
				map.put(v.name, type);
				set.add(type);
			}
			set.remove(type);
			set.add(type);
			if (set.size() >= names.size()) {
				if (check(set, names)) {
					return v.time;
				}
			}
		}
		return -1;
	}
	
	public static boolean check(TreeSet<Type> set, List<String> names) {
		int index = 0;
		for (Type type : set) {
			if (index >= names.size()) {
				break;
			}
			if (!type.name.equals(names.get(index))) {
				return false;
			}
			index++;
		}
		return true;
	}
}
