package main.java.org.problem.utils;

/**
 * 数组辅助操作类
 *
 */
public class ArrayUtils {
	/**
	 * 在数组中搜索指定的字符
	 * @param arrays 被搜索的数组
	 * @param symbols 要搜索的字符
	 * @return >=0时，查找成功，否则查找失败
	 */
	public static int search(char[] arrays, char symbols) {
		for (int i = 0; i < arrays.length; i++)
			if (arrays[i] == symbols)
				return i;
		return -1;
	}
}
