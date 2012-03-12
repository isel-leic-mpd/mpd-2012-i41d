package collect;

import java.util.List;

public class IterUtils {
	public static <T> void copy(Iterable<T> iter, Object [] arr, Projection<T, Object> func){
		int idx = 0;
		for (T item : iter) {
			arr[idx++] = func.invoke(item);
    }
	}
	public static <T> void forEach(T[] strArr, Func<T> f){
		for (T t : strArr) {
			f.invoke(t);
		}
	}
	public static <T> void forEach(Iterable<T> strArr, Func<T> f){
		for (T t : strArr) {
			f.invoke(t);
		}
	}
	public static <T> T find(Iterable<T> elems, Predicate<T> p){
		for (T item : elems) {
			if(p.invoke(item))
				return item;
		}
		return null;
	}
	public static <T> int indexOf(List<T> elems, Predicate<T> p){
		for(int i = 0; i < elems.size(); i++){
			if(p.invoke(elems.get(i)))
				return i;
		}
		return -1;
	}
}
