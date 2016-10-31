package Google;

import java.util.*;

/*
 * 
第二题, 一个array,rearrange成为另一个array, 现在给了这两个array, 求是怎么变化成第二个array的. 
挺简单的就用了Hashmap秒了...
然后问我,那现在给你原array,也知道了是怎么变化的了,所以我们现在可以用原array求出变化后的array对吗? 
但是我要run这个method好多次比如k次, 怎么最快能求出array被rearrange了k次以后的结果? 最后我就推倒出求LCM.

没错就是周期~ 每个位置上,rearrange成为原始的数字的周期是不同的. 需要计算各个位置的周期, 然后求他们的LCM,就是整体rearrange成为原array的周期了. 
然后再把k用这个LCM周期数取模, 就能算出最少要rearrange几次了

我们的想法非常类似. 如果你对于每个位置都分别找规律,一个一个位置的求的想法非常好~但是如果这array非常长,是不是每个位置都要求一遍呢?那样的话时间复杂度又是多少呢? 
我是把这个array看成一个整体,取LCM的意思就是,对于一整个array来说,要变换多少次能变回原array? 比如说位置一要3次,位置二要2次,
位置三要5次,那么LCM就是30.也就是说,变换30/60/90/...次能完全变回去. 
那我就先把k除以30取模,所以最多变换29次就是结果了

那就是说每个位置上的元素都按照一定的规律改变位置，然后用一个array来记录每个位置上的元素要经过多少次arrange可以回到原来的位置，
然后对这个array求LCM，最后mod下就可以了是吗？
对~ 也就是第一问是求表示从arr1变化到arr2的变化位置的array, 就是rearrange一次的规律就好了. 第二问是分别求出每个position上变化的周期,然后求出整体的LCM.


LCM: Least Common Multiple
GCD: Greatest Common Divisor
 */
public class LCM {
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4};
		int[] format = {3, 2, 0, 1};
		//int[] array = {1, 2};
		//int[] format = {1, 0};
		int[] array1 = rearrange(array, format, 6);
		System.out.println(Arrays.toString(array1));
		int[] array2 = rearrangeLCM(array, format, 6);
		System.out.println(Arrays.toString(array2));
	}
	
	
	
	// O(kn) if k is a large number, this would not be efficient.
	public static int[] rearrange(int[] array, int[] format, int k) {
		int[] result = new int[array.length];
		for (int j = 0; j < k; j++) {
			for (int i = 0; i < array.length; i++) {
				result[format[i]] = array[i];
			}
			array = result;
			result = new int[array.length];
			//System.out.println(Arrays.toString(array));
		}
		return array;
	}
	
	public static int[] rearrangeLCM(int[] array, int[] format, int k) {
		int[] record = new int[array.length];
		int[] changedArray = new int[array.length];
		int[] stay = Arrays.copyOf(array, array.length);
		int count = 0;
		int changedTime = 0;
		while (count < array.length - 1) {
			changedTime++;
			for (int i = 0; i < array.length; i++) {
				changedArray[format[i]] = array[i];
				if (changedArray[format[i]] == stay[format[i]] && record[format[i]] == 0) {
					record[format[i]] = changedTime;
					count++;
				}
			}
			array = changedArray;
			changedArray = new int[array.length];
		}
		
		int lcm = record[0];
		for (int i = 1; i < record.length; i++) {
			lcm = getLCM(lcm, record[i]);
		}
		k = k % lcm;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < stay.length; j++) {
				changedArray[format[j]] = stay[j];
			}
			stay = changedArray;
			changedArray = new int[stay.length];
		}
		return stay;
	}
	
	private static int getLCM(int a, int b) {
		return (a * b) / getGCD(a, b);
	}
	
	private static int getGCD(int a, int b) {
		if (a == b) {
			return a;
		}
		if (a > b) {
			return getGCD(a - b, b);
		} else {
			return getGCD(a, b - a);
		}
	}
}
