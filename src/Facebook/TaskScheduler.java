package Facebook;
/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216178&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3090%5D%5Bvalue%5D%3D1%26searchoption%5B3090%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D2%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 * 
 * http://massivealgorithms.blogspot.com/2016/01/fbprepare-task-schedule-neverlandly_24.html
 */

import java.util.*;

public class TaskScheduler {
	
	
	public static void main(String[] args) {
		int[] nums1 = {1, 2, 3, 1, 2, 3};
		int[] nums2 = {1, 2, 3, 2, 3};
		int[] nums3 = {1, 2, 1, 2, 3};
		int[] nums4 = {1, 2, 4, 2, 3, 5, 3};
		int[] nums5 = {1, 1, 2, 1};
		int t = 4;
		int res1 = TaskScheduler(nums4, t);
		int res2 = schedule(nums4, t);
		int res3 = task(nums4, t);
		System.out.println(res1);
		System.out.println(res2);
		System.out.println(res3);
		
		
		int[] nums6 = {1, 1, 2, 1, 2};
		int t2 = 3;
		int res4 = TaskScheduler2(nums6,t2);
		System.out.println(res4);
	}
	
	public static int TaskScheduler(int[] nums, int t) {
		int offset = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				int position = map.get(nums[i]);
				if (i + offset - position < t + 1) {
					offset += (t + 1 - i - offset + position);
					map.put(nums[i], i + offset);
				}
			} else {
				map.put(nums[i], i + offset);
			}
		}
		return nums.length + offset;
	}
	
	static class Pair {
		int key;
		int count;
		public Pair(int key, int count) {
			this.key = key;
			this.count = count;
		}
	}
	
	public static int TaskScheduler2(int[] nums, int t) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		ArrayList<Pair> list = new ArrayList<Pair>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				map.put(nums[i], map.get(nums[i]) + 1);
			} else {
				map.put(nums[i], 1);
			}
		}
		for (int key : map.keySet()) {
			Pair p = new Pair(key, map.get(key));
			list.add(p);
		}
		Collections.sort(list, new Comparator<Pair>() {
			public int compare(Pair left, Pair right) {
				return right.count - left.count;
			}
		});
		Queue<Pair> q = new LinkedList<Pair>();
		for (int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
		}
		HashMap<Integer, Integer> iMap = new HashMap<Integer, Integer>();
		int index = 0;
		while (!q.isEmpty()) {
			Pair p = q.poll();
			if (iMap.containsKey(p.key)) {
				int last = iMap.get(p.key);
				if (index - last < t) {
					index += t - index + last;
				}
				p.count--;
			} else {
				p.count--;
				index++;
			}
			iMap.put(p.key, index);
			if (p.count > 0) {
				q.add(p);
			}
		}
		//index--;
		return index;
	}
	
	
	public static int task(int[] tasks, int cooldown) {
		int time = 0;
	         HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
     
     StringBuilder output = new StringBuilder();
	   
		         for (int i=0; i<tasks.length; i++) {
		             if (!map.containsKey(tasks[i]) || time>=map.get(tasks[i])) {
		                 map.put(tasks[i], time+cooldown);
		                 time++;
		                 
		                 output.append(tasks[i]);
		             }
		             else { // time < map.get(tasks[i])
		                 for (int k=time; k<map.get(tasks[i]); k++) {
		                     output.append("-");
		                 }
		                 
		                 
		                 time = map.get(tasks[i]);
		                 map.put(tasks[i], time+cooldown);
		                 time++;
		                 
		                 
		                 output.append(tasks[i]);
		             }
		         }
		         return time;
		     }
	
	
	
	
	
	public static int schedule(int[] str, int recover) {
        if (str==null || str.length==0) return 0;
        if (recover == 0) return str.length;
        int pos = 0;
        int time = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (; pos<str.length; pos++) {
            int cur = str[pos];
            if (!map.containsKey(cur)) {
                map.put(cur, time+recover+1);
            }
            else {
                int lastApr = map.get(cur);
                if (time >= lastApr) {
                    map.put(cur, time);
                }
                else {
                    pos--;
                }
            }
            time++;
        }
        return time;
    }
}
