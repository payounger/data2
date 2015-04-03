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
    
    public class Leaf<T extends Comparable<T>> implements MultiSet<T>{
    
        Leaf(){
            
        }
        
        public int cardinality(){
            return 0;
        }
        
        public Boolean isEmptyHuh(){
            return true;
        }
        
        public Boolean member(T t){
            return false;
        }
        
        public int count(T t){
            return 0;
        }
        
        public MultiSet<T> add(T t){
        
        }
        
        public MultiSet<T> add(T t, int x){
            
        }
        
//        public MultiSet<T> remove(T t);
//        
//        public MultiSet<T> remove(T t, int x);
//        
//        public MultiSet<T> union(MultiSet<T> ms);
//        
//        public MultiSet<T> inter(MultiSet<T> ms);
//        
//        public MultiSet<T> diff(MultiSet<T> ms);
//        
//        public Boolean equalHuh(MultiSet<T> ms);
//        
//        public Boolean subHuh(MultiSet<T> ms);
    }
    
    public class Branch<T extends Comparable<T>> implements MultiSet<T>{
        
        MultiSet<T> left;
        MultiSet<T> right;
        int key;
        int multiplicity;
        
        Branch(MultiSet<T> left, int key, int multiplicity, MultiSet<T> right) {
            this.left = left;
            this.right = right;
            this.key = key;
            this.multiplicity = multiplicity;
        }

//        public int cardinality();
//        
//        public Boolean isEmptyHuh();
//        
//        public Boolean member(T t);
//        
//        public int count(T t);
//        
//        public MultiSet<T> add(T t);
//        
//        public MultiSet<T> add(T t, int x);
//        
//        public MultiSet<T> remove(T t);
//        
//        public MultiSet<T> remove(T t, int x);
//        
//        public MultiSet<T> union(MultiSet<T> ms);
//        
//        public MultiSet<T> inter(MultiSet<T> ms);
//        
//        public MultiSet<T> diff(MultiSet<T> ms);
//        
//        public Boolean equalHuh(MultiSet<T> ms);
//        
//        public Boolean subHuh(MultiSet<T> ms);
    }
}
