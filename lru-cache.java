//Using Doubly Linked List and Hashmap
//All operations (insert/add, remove, get/serach, put) in both DLL and HashMap is O(1)
class LRUCache {
    class Node{
        int key;
        int val;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key=key;
            this.val=val;
        }
    }
    private void addToHead(Node node){
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }
    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private HashMap<Integer,Node> map;
    private Node head;
    private Node tail;
    private int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1,-1); //dummy node
        this.tail = new Node(-1,-1); //dummy node
        this.head.next = tail;
        this.tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1; //dosen't have a key
        Node node = map.get(key); //does have a key
        removeNode(node);
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        //is fresh
        if(map.containsKey(key)){ //not fresh
            Node node = map.get(key);
            node.val = value;
            removeNode(node);
            addToHead(node);
        } else {
            //fresh
            if(capacity == map.size()){
                //remove lru
                Node tailPrev = tail.prev;
                removeNode(tailPrev);
                map.remove(tailPrev.key);
            }
            Node newNode = new Node(key,value);
            map.put(key, newNode);
            addToHead(newNode);
        }
    }
}