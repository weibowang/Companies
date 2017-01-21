package Google;

import java.util.*;

public class TwoNumberMinors {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(19);
		list.add(17);
		list = TwoNumberSubtracts(list);
		System.out.println(Arrays.toString(list.toArray()));
	}
	
	
	public static List<Integer> TwoNumberSubtracts(List<Integer> list) {
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < list.size(); i++) {
			set.add(list.get(i));
		}
		
		while (true) {
			List<Integer> addList = new ArrayList<Integer>();
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.size(); j++) {
					if (i != j) {
						List<Integer> currentIntegers = TwoDigitSubtracts(list.get(i), list.get(j));
						for (int k = 0; k < currentIntegers.size(); k++) {
							if (!set.contains(currentIntegers.get(k))) {
								addList.add(currentIntegers.get(k));
								set.add(currentIntegers.get(k));
							}
						}
					}
//					set.add(list.get(i));
//					set.add(list.get(j));
				}
			}
			if (addList.isEmpty()) {
				break;
			}
			list.addAll(addList);
		}
		return list;
	}
	
	private static List<Integer> TwoDigitSubtracts(int a, int b) {
		List<Integer> result = new ArrayList<Integer>();
		while (a != 0 && b != 0) {
			int aNow = a % 10;
			int bNow = b % 10;
			result.add(Math.abs(aNow - bNow));
			a = a / 10;
			b = b / 10;
		}
		while (a != 0) {
			int aNow = a % 10;
			result.add(aNow);
			a = a / 10;
		}
		
		while (b != 0) {
			int bNow = b % 10;
			result.add(bNow);
			b = b / 10;
		}
		return result;
	}
}

