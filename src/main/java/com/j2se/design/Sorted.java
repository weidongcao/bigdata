package com.j2se.design;

/**
 * 本程序用于测试java中的各种排序算法
 * 
 * @author Administrator
 * 
 */
public class Sorted {
	// public static int[] arr = {12, 53, 89, 75, 23, 97, 49, 17, 36, 25, 63,
	// 26, 41, 55};
	public static int[] arr = { 12, 53, 89, 75, 23, 97, 49 };

	public static void main(String[] args) {
		//Sorted sort = new Sorted();

		// sort.bubbleSortArray(arr);
		/*
		 * System.out.println("排序前："); display(arr); sort.quickSort(arr, 0,
		 * arr.length - 1); System.out.println("排序后："); display(arr);
		 */
		//sort.selectSort(arr);

	}

	/**
	 * 基本思想：依次比较相信的两个数，将小数放在前面，套数放在后面。如此重复下去直到最终完成排序 时间复杂度O(n*n),适用于排序小列表
	 */
	public int[] bubbleSortArray(int[] arr) {
		System.out.println("排序前：");
		display(arr);

		int temp = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = arr.length - 1; j > i; j--) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}
			}
		}

		System.out.println("排序后：");
		display(arr);

		return arr;
	}

	/**
	 * 基本思想：选择一个基准元素，通常选择第一个元素或者最后一个元素，通过一趟扫描，将待排序列分成两部分
	 * 一部分比基准元素小，平分大于等于基准元素，此时基准元素在其排好序后的正确位置，然后再用同样的方法 递归地排序划分的两部分
	 */

	public int[] quickSort(int[] arr, int low, int high) {

		if (low < high) {
			// 分割数组，找到枢轴
			int pivot = partition(arr, low, high);

			// 递归调用，对低子数组进行排序
			quickSort(arr, low, pivot - 1);
			// 对高子数组进行排序
			quickSort(arr, pivot + 1, high);
		}

		return arr;
	}

	/**
	 * 分割数组，找到枢轴
	 */
	public int partition(int[] arr, int low, int high) {
		// 用数组的第一个记录作枢轴
		int pivotKey = arr[low];

		while (low < high) {
			while (low < high && arr[high] >= pivotKey) {
				high--;
			}
			swap(arr, low, high);

			while (low < high && arr[low] <= pivotKey) {
				low++;
			}
			swap(arr, low, high);

			// 返回枢轴所在位置
		}
		return low;
	}

	/**
	 * 交换数组两个位置的元素
	 */
	public void swap(int[] arr, int low, int high) {
		if (low < high && null != arr && arr.length > 0) {
			arr[low] = arr[low] ^ arr[high];
			arr[high] = arr[high] ^ arr[low];
			arr[low] = arr[low] ^ arr[high];
		}
	}

	/*
	 * 每一趟从待排序的数据元素中选出最小的一个元素，顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完。
	 */
	public int[] selectSort(int[] arr) {
		System.out.println("排序前：");
		display(arr);
		int i, j, index;
		for (i = 0; i < arr.length - 2; i++) {
			index = i;
			for (j = i + 1; j < arr.length - 1; j++) {
				if (arr[j] < arr[index]) {
					index = j;
				}
			}
			if (index != i) {
				arr[i] = arr[i] ^ arr[j];
				arr[j] = arr[j] ^ arr[i];
				arr[i] = arr[i] ^ arr[j];
			}

		}
		System.out.println("排序后：");
		display(arr);
		return arr;
	}

	/**
	 * 显示数组
	 * 
	 * @param arr
	 */
	public static void display(int[] arr) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		if (null != arr && arr.length > 0) {
			for (int i = 0; i < arr.length; i++) {
				builder.append(arr[i]);
				if (i != arr.length - 1) {
					builder.append(", ");
				}
			}
		}
		builder.append("]");
		System.out.println(builder.toString());
	}
}
