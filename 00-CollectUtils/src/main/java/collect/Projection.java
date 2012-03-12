package collect;

public interface Projection<S,R>{
	  R invoke(S item);
}
