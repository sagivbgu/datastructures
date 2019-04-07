package src;

public class Tests {

	public static void main(String[] args) {
		
		FloorsArrayList list = new FloorsArrayList(20);

		list.insert(1, 1);
		list.insert(3, 2);
		list.insert(11, 1);
		list.insert(8, 1);
		list.insert(4, 3);
		list.insert(14, 1);
		list.insert(17, 1);
		list.insert(19, 1);
		list.insert(24, 2);
		list.insert(31, 3);
		list.insert(49, 1);
		list.insert(58, 2);
		list.insert(36, 1);
		list.insert(41, 2);
		list.insert(43, 1);
		list.insert(51, 4);
		
		System.out.println(list.getSize()); //16
		System.out.println(list.lookup(49).getKey()); //49
		System.out.println(list.lookup(3).getArrSize()); //2
		System.out.println(list.minimum()); // 1
		System.out.println(list.maximum()); // 58
		System.out.println(list.successor(list.lookup(3))); //4
		
		list.remove(list.lookup(31));
		System.out.println(list.lookup(31)); //null
		System.out.println(list.predecessor(list.lookup(1))); // -infinity
		
		list.remove(list.lookup(11));
		System.out.println(list.getSize()); //14
		
		list.insert(46, 3);
		System.out.println(list.lookup(41).getNext(2).getKey()); // 46
	}
}