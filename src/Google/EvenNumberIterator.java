package Google;

public class EvenNumberIterator {
	
	/*
	 * 实现一个iterator, input 是一个array{3, 8, 0, 12, 2, 9}, 希望输出是 {8, 8, 8, 9, 9},   
	 * 也就是eventh number代表 词频， oddth number 代表词， {3, 8, 12, 0, 2, 9}， 就是3个8， 0个12， 2个9. 
和美眉商量了输入不用array, 用个List<Integer> 简单好多。 
	 */
	
	public static void main(String[] args) {
		int array[] = {3, 8, 0, 12, 2, 9};
		EvenNumberIterator e = new EvenNumberIterator(array);
		while (e.hasNext()) {
			System.out.println(e.next());
		}
	}
	
	int nums[];
	int[] output;
	int globalIndex;
	EvenNumberIterator(int[] nums) {
		this.nums = nums;
		int count = 0;
		for (int i = 0; i < nums.length; i = i + 2) {
			count += nums[i];
		}
		output = new int[count];
		int index = 0;
		for (int i = 0; i < count; i++) {
			while (nums[index] == 0) {
				index = index + 2;
			}
			nums[index]--;
			output[i] = nums[index + 1];
		}
		globalIndex = 0;
	}
	boolean hasNext() {
		if (globalIndex >= output.length) {
			return false;
		} else {
			return true;
		}
	}
	int next() {
		int index = globalIndex;
		globalIndex++;
		return output[index];
	}
}
