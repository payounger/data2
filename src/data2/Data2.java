package data2;

public class Data2 {

    public static void main(String[] args) {

    }

    public interface MultiSet<T extends Comparable<T>> {

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

    public class Leaf<T extends Comparable<T>> implements MultiSet<T> {

        Leaf() {

        }

        public int cardinality() {
            return 0;
        }

        public Boolean isEmptyHuh() {
            return true;
        }

        public Boolean member(T t) {
            return false;
        }

        public int count(T t) {
            return 0;
        }

        public MultiSet<T> add(T t) {
            return new Branch<T>(this, t, 1, this);
        }

        public MultiSet<T> add(T t, int x) {
            return new Branch<T>(this, t, x, this);
        }

        public MultiSet<T> remove(T t) {
            return this;
        }

        public MultiSet<T> remove(T t, int x) {
            return this;
        }

        public MultiSet<T> union(MultiSet<T> ms) {
            return ms;
        }

        public MultiSet<T> inter(MultiSet<T> ms) {
            return this;
        }

        public MultiSet<T> diff(MultiSet<T> ms) {
            return this;
        }

        public Boolean equalHuh(MultiSet<T> ms) {
            return ms.isEmptyHuh();
        }

        public Boolean subHuh(MultiSet<T> ms) {
            return true;
        }
    }

    public class Branch<T extends Comparable<T>> implements MultiSet<T> {

        MultiSet<T> left;
        MultiSet<T> right;
        Comparable<T> key;
        int multiplicity;

        Branch(MultiSet<T> left, T t, int multiplicity, MultiSet<T> right) {
            this.left = left;
            this.key = key;
            this.multiplicity = multiplicity;
            this.right = right;
        }

        public int cardinality() {
            return this.multiplicity + this.left.cardinality() + this.right.cardinality();
        }

        public Boolean isEmptyHuh() {
            return false;
        }

        public Boolean member(T t) {
            int compareVal = this.key.compareTo(t);
            if (compareVal == 0) {
                return true;
            } else if (compareVal < 0) {
                return this.left.member(t);
            } else /*if compareVal>0*/ {
                return this.right.member(t);
            }
        }

        public int count(T t) {
            int compareVal = this.key.compareTo(t);
            if (compareVal == 0) {
                return this.multiplicity;
            } else if (compareVal < 0) {
                return this.left.count(t);
            } else /*if compareVal>0*/ {
                return this.right.count(t);
            }
        }

        public MultiSet<T> add(T t) {
            int compareVal = this.key.compareTo(t);
            if (compareVal == 0) {
                return new Branch(this.left, this.key, this.multiplicity + 1, this.right);
            } else if (compareVal < 0) {
                return new Branch(this.left.add(t), this.key, this.multiplicity, this.right);
            } else /*if compareVal>0*/ {
                return new Branch(this.left, this.key, this.multiplicity, this.right.add(t));
            }
        }

        public MultiSet<T> add(T t, int x) {
            int compareVal = this.key.compareTo(t);
            if (compareVal == 0) {
                return new Branch(this.left, this.key, this.multiplicity + x, this.right);
            } else if (compareVal < 0) {
                return new Branch(this.left.add(t, x), this.key, this.multiplicity, this.right);
            } else /*if compareVal>0*/ {
                return new Branch(this.left, this.key, this.multiplicity, this.right.add(t, x));
            }
        }

        public MultiSet<T> remove(T t) {
            int compareVal = this.key.compareTo(t);
            if (compareVal == 0) {
                if (this.multiplicity > 1){
                    return new Branch(this.left, this.key, this.multiplicity-1, this.right);
                } else {
                    return this.left.union(this.right);
                }
            } else if (compareVal < 0){
                return new Branch(this.left.remove(t), this.key, this.multiplicity, this.right);
            } else /*if compareVal > 0*/{
                return new Branch(this.left, this.key, this.multiplicity, this.right.remove(t));
            }

        }

        public MultiSet<T> remove(T t, int x) {
            int compareVal = this.key.compareTo(t);
            if (compareVal == 0) {
                if (this.multiplicity > x) {
                    return new Branch(this.left, this.key, this.multiplicity - x, this.right);
                } else {
                    return this.left.union(this.right);
                }
            } else if (compareVal < 0) {
                return new Branch(this.left.remove(t, x), this.key, this.multiplicity, this.right);
            }else /*if compareVal > 0 */ {
                return new Branch(this.left, this.key, this.multiplicity, this.right.remove(t, x));
            }
        }

        public MultiSet<T> union(MultiSet<T> ms){
            return ms.union(this.left).union(this.right).add(this.key, this.multiplicity);
        }

        public MultiSet<T> inter(MultiSet<T> ms){
            if (ms.member(this.key)){
                if(ms.count(this.key)>this.multiplicity){
                    return new Branch(this.left.inter(ms), this.key, this.multiplicity, this.right.inter(ms));
                }else /*if ms.count(this.key)<this.multiplicity)*/{
                    return new Branch(this.left.inter(ms), this.key, ms.count(this.key), this.right.inter(ms));
                }
            }else {
                return this.remove(this.key, this.multiplicity).inter(ms);
            }
        }

        public MultiSet<T> diff(MultiSet<T> ms){
            if(ms.member(this.key)){
                if(ms.count(this.key)>this.multiplicity){
                    return new Branch(this.left.diff(ms), this.key, ms.count(this.key)-this.multiplicty, this.right.diff(ms));
                }else if(ms.count(this.key)<this.multiplicity){
                    return new Branch(this.left.diff(ms), this.key, this.multiplicity-ms.count(this.key), this.right.diff(ms));
                }else /*ms.count(this.key)==this.multiplicity*/{
                    return this.remove(this.key, this.multiplicity).diff(ms);
                }
            }else {
                return new Branch(this.left.diff(ms), this.key, this.multiplicity, this.right.diff(ms));
            }
        }

        public Boolean equalHuh(MultiSet<T> ms){
            if(this.diff(ms).isEmptyHuh()&&ms.diff(this).isEmptyHuh()){
                return true;
            }else return false;
        }

        public Boolean subHuh(MultiSet<T> ms){
            if(this.union(ms)==this){
                return true;
            }else return false;
        }
    }
}
