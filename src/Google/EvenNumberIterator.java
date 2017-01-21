package Google;

public class EvenNumberIterator {
	
	/*
	 * ʵ��һ��iterator, input ��һ��array{3, 8, 0, 12, 2, 9}, ϣ������� {8, 8, 8, 9, 9},   
	 * Ҳ����eventh number���� ��Ƶ�� oddth number ����ʣ� {3, 8, 12, 0, 2, 9}�� ����3��8�� 0��12�� 2��9. 
����ü���������벻��array, �ø�List<Integer> �򵥺öࡣ 
	 */
	
	public static void main(String[] args) {
		int array[] = {3, 8, 0, 12, 2, 9};
		EvenNumberIterator e = new EvenNumberIterator(array);
		while (e.hasNext()) {
			System.out.print(e.next() + " ");
		}
		System.out.println();
		int array2[] = {3, 8, 0, 12, 2, 9};
		EvenNumberIterator2 e2 = new EvenNumberIterator2(array2);
		while (e2.hasNext()) {
			System.out.print(e2.next() + " ");
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
	
	
	static class EvenNumberIterator2 {
		int[] record;
		int[] nums;
		EvenNumberIterator2(int[] nums) {
			record = new int[2];
			this.nums = nums;
		}
		
		boolean hasNext() {
			int x = record[0];
			if (x == nums.length - 2 && record[1] == nums[nums.length - 2]) {
				return false;
			}
			//System.out.println("AA");
			return true;
		}
		
		int next() {
			while (nums[record[0]] <= record[1]) {
				//System.out.println(record[0] + " " + nums[record[0]] + " " + record[1]);
				record[0] = record[0] + 2;
				record[1] = 0;
			}
			record[1]++;
			return nums[record[0] + 1];
			
		}
	}
	
	
}
