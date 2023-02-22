package me.nicomunoz.kiroscraft.parkour.shared.utils;

public class ArrayUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> T[] getArray(T... objects) {
		return (objects);
	}
	
	public static String[] sub(int i, String... args) {
		StringBuilder builder = new StringBuilder();
		for(;args.length > i; i++) {
			builder.append(args[i]);
			if((args.length-1)!=i) builder.append(" ");
		}
		return builder.toString().split(" ");
	}
	
	public static <T> T[] append(T[] arr, T... lastElement) {
		if(lastElement == null || lastElement.length == 0) return arr;
	    int N = arr.length;
	    arr = java.util.Arrays.copyOf(arr, N+lastElement.length);
	    for(T element : lastElement) {
	    	arr[N++] = element;
	    }
	    return arr;
	}
	
	public static <T> T[] append(T[] arr, T lastElement) {
	    final int N = arr.length;
	    arr = java.util.Arrays.copyOf(arr, N+1);
	    arr[N] = lastElement;
	    return arr;
	}
	public static <T> T[] prepend(T[] arr, T firstElement) {
	    final int N = arr.length;
	    arr = java.util.Arrays.copyOf(arr, N+1);
	    System.arraycopy(arr, 0, arr, 1, N);
	    arr[0] = firstElement;
	    return arr;
	}

}
