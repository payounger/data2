package data2;

public class Data2 {
    
    public static void main(String[] args) {
        
    }
    
    public interface MultiSet<T extends Comparable<T>>{
        
        public int cardinality();
        
        public Boolean isEmptyHuh();
        
        public Boolean member(T t);
        
        public int count(T t);
        
        public MultiSet<T> add(T t);
        
        public MultiSet<T> add(T t, int x);
        
        public MultiSet<T> remove(T t);
        
        public MultiSet<T> remove(T t, int x);
        
        public MultiSet<T> union(MultiSet<T> ms);
        
        public MultiSet<T> inter(MultiSet<T> ms);
        
        public MultiSet<T> diff(MultiSet<T> ms);
        
        public Boolean equalHuh(MultiSet<T> ms);
        
        public Boolean subHuh(MultiSet<T> ms);
    }
}
