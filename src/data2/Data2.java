package data2;

/**
 *
 * @author Paul
 */
public class Data2 {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     *
     * @param <T>
     */
    public interface MultiSet<T extends Comparable<T>> {

        /**
         *
         * @return
         */
        public int cardinality();

        /**
         *
         * @return
         */
        public Boolean isEmptyHuh();

        /**
         *
         * @param t
         * @return
         */
        public Boolean member(T t);

        /**
         *
         * @param t
         * @return
         */
        public int count(T t);

        /**
         *
         * @param t
         * @return
         */
        public MultiSet<T> add(T t);

        /**
         *
         * @param t
         * @param x
         * @return
         */
        public MultiSet<T> add(T t, int x);

        /**
         *
         * @param t
         * @return
         */
        public MultiSet<T> remove(T t);

        /**
         *
         * @param t
         * @param x
         * @return
         */
        public MultiSet<T> remove(T t, int x);

        /**
         *
         * @param ms
         * @return
         */
        public MultiSet<T> union(MultiSet<T> ms);

        /**
         *
         * @param ms
         * @return
         */
        public MultiSet<T> inter(MultiSet<T> ms);

        /**
         *
         * @param ms
         * @return
         */
        public MultiSet<T> diff(MultiSet<T> ms);

        /**
         *
         * @param ms
         * @return
         */
        public Boolean equalHuh(MultiSet<T> ms);

        /**
         *
         * @param ms
         * @return
         */
        public Boolean subHuh(MultiSet<T> ms);
    }

    /**
     *
     * @param <T>
     */
    public class Leaf<T extends Comparable<T>> implements MultiSet<T> {

        Leaf() {

        }

        /**
         *The leaf implementation of cardinality always returns 0
         * @return
         */
        public int cardinality() {
            return 0;
        }

        /**
         *The leaf implementation of isEmptyHuh always returns true
         * @return
         */
        public Boolean isEmptyHuh() {
            return true;
        }

        /**
         *The leaf implementation of member always returns false
         * @param t
         * @return
         */
        public Boolean member(T t) {
            return false;
        }

        /**
         *The leaf implementation of count always returns 0
         * @param t
         * @return
         */
        public int count(T t) {
            return 0;
        }

        /**
         *The leaf implementation of returns a new MultiSet<T> containing t with a multiplicty of one, and two leaf children
         * @param t
         * @return
         */
        public MultiSet<T> add(T t) {
            return new Branch<T>(this, t, 1, this);
        }

        /**
         *The leaf implementation of returns a new MultiSet<T> containing t with a multiplicty of x, and two leaf children
         * @param t
         * @param x
         * @return
         */
        public MultiSet<T> add(T t, int x) {
            return new Branch<T>(this, t, x, this);
        }

        /**
         *The leaf implementation of remove returns a leaf
         * @param t
         * @return
         */
        public MultiSet<T> remove(T t) {
            return this;
        }

        /**
         *The leaf implementation of remove returns a leaf
         * @param t
         * @param x
         * @return
         */
        public MultiSet<T> remove(T t, int x) {
            return this;
        }

        /**
         *The leaf implementation of union returns ms
         * @param ms
         * @return
         */
        public MultiSet<T> union(MultiSet<T> ms) {
            return ms;
        }

        /**
         *The leaf implementation of inter returns a leaf
         * @param ms
         * @return
         */
        public MultiSet<T> inter(MultiSet<T> ms) {
            return this;
        }

        /**
         *The leaf implementation of diff returns a leaf
         * @param ms
         * @return
         */
        public MultiSet<T> diff(MultiSet<T> ms) {
            return this;
        }

        /**
         *The leaf implementation of equalHuh returns true if ms is empty, false otherwise
         * @param ms
         * @return
         */
        public Boolean equalHuh(MultiSet<T> ms) {
            return ms.isEmptyHuh();
        }

        /**
         *The leaf implementation of subHuh returns true
         * @param ms
         * @return
         */
        public Boolean subHuh(MultiSet<T> ms) {
            return true;
        }
    }

    /**
     *
     * @param <T>
     */
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

        /**
         *The Branch implementation of cardinality returns the summed multiplicity of all keys in the branch and its children
         * @return
         */
        public int cardinality() {
            return this.multiplicity + this.left.cardinality() + this.right.cardinality();
        }

        /**
         *The Branch implementation of isEmptyHuh returns false
         * @return
         */
        public Boolean isEmptyHuh() {
            return false;
        }

        /**
         *The Branch implementation of member returns true if the key equals t, or any child's key equals t
         * @param t
         * @return
         */
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

        /**
         *The Branch implementation of count returns the multiplicity t, returns 0 if t is not a member of the Branch
         * @param t
         * @return
         */
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

        /**
         *The Branch implementation of add returns a new MultiSet<T> with t added to it, either by increasing the multiplicity of the existing t in the Branch by one, or by adding a new node containing t with a multiplicity of one
         * @param t
         * @return
         */
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

        /**
         *The Branch implementation of add returns a new MultiSet<T> with t added to it, either by increasing the multiplicity of the existing t in the Branch by x, or by adding a new node containing t with a multiplicity of x
         * @param t
         * @param x
         * @return
         */
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

        /**
         *The Branch implementation of remove returns a new MultiSet<T> with one less copy of t in it, by reducing the multiplicity of t by one, using union to generate a new Branch if the multiplicity of t was reduced to zero, uses binary search to find t
         * @param t
         * @return
         */
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

        /**
         *The Branch implementation of remove returns a new MultiSet<T> with x less copies of t in it, by reducing the multiplicity of t by x, using union to generate a new Branch if the multiplicity of t was reduced to zero or below, uses binary search to find t
         * @param t
         * @param x
         * @return
         */
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

        /**
         *The Branch implementation of union returns a MultiSet<T> containing all the members of this and ms
         * @param ms
         * @return
         */
        public MultiSet<T> union(MultiSet<T> ms){
            return ms.union(this.left).union(this.right).add(this.key, this.multiplicity);
        }

        /**
         *The Branch implementation of inter returns a MultiSet<T> containing the members common to both this and ms
         * @param ms
         * @return
         */
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

        /**
         *The Branch implementation of diff returns a MultiSet<T> containing the members of ms and this that they do not share in common
         * @param ms
         * @return
         */
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

        /**
         *The Branch implementation of equalHuh returns true if this and ms contain all the same members and the same multiplicity, only checks for membership and multiplicity
         * @param ms
         * @return
         */
        public Boolean equalHuh(MultiSet<T> ms){
            if(this.diff(ms).isEmptyHuh()&&ms.diff(this).isEmptyHuh()){
                return true;
            }else return false;
        }

        /**
         *The Branch implementation of subHuh returns true if all the members of this are contained in ms, and the multiplicty of those members in ms is greater than or equal to their multiplicity in this
         * @param ms
         * @return
         */
        public Boolean subHuh(MultiSet<T> ms){
            if(this.union(ms)==this){
                return true;
            }else return false;
        }
    }
}
