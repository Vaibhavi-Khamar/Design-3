//not an iterative sol //recursion
// public class NestedIterator implements Iterator<Integer> {
//     private ArrayList<Integer> li;
//     int i;
//     public NestedIterator(List<NestedInteger> nestedList) {
//         this.li = new ArrayList<>();
//         dfs(nestedList);
//     }
//     private void dfs(List<NestedInteger> nestedList){
//         for(NestedInteger el: nestedList){
//             if(el.isInteger()){
//                 li.add(el.getInteger());
//             } else {
//                 dfs(el.getList());
//             }
//         }
//     }

//     @Override
//     public Integer next() { //O(1)
//         Integer result = li.get(i);
//         i++;
//         return result;
//     }

//     @Override
//     public boolean hasNext() { //O(1)
//         return i != li.size();
//     }
// }

//This is the only solution as it uses lazy loading
//Iterative to control better //lazy loading
//using basic/native iterator
//maintain stack of basic iterators
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator()); //put iterator inside stack
    }

    @Override
    public Integer next() { //O(1)
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() { //O(d) depth of the stack in worst case. //O(1) amortised
        while (!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            } else if((nextEl = st.peek().next()).isInteger()){
                return true;
            } else {
                st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}