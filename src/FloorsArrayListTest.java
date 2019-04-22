import static org.junit.jupiter.api.Assertions.*;

class FloorsArrayListTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getSize() {
    }

    @org.junit.jupiter.api.Test
    void insertToEmpty() {
        FloorsArrayList list = getEmptyList();
        list.insert(1, 3);

        for (int i = 0; i < 3; i++) {
            FloorsArrayLink newLink = list.negativeInfinityLink.arrFront[i];
            assertEquals(1, newLink.getKey());
            assertEquals(3, newLink.getArrSize());
            assertEquals(list.negativeInfinityLink, newLink.arrBack[i]);
            assertEquals(list.infinityLink, newLink.arrFront[i]);
        }
    }

    @org.junit.jupiter.api.Test
    void insertAfter() {
        FloorsArrayList list = getEmptyList();
        list.insert(1, 2);
        list.insert(2, 1);
        list.insert(3, 5);
        list.insert(2.5, 4);

        assertEquals(list.infinityLink, list.negativeInfinityLink.getNext(6));
        assertEquals(3, list.negativeInfinityLink.getNext(5).getKey());
        assertEquals(2.5, list.negativeInfinityLink.getNext(4).getKey());
        assertEquals(2.5, list.negativeInfinityLink.getNext(3).getKey());
        assertEquals(1, list.negativeInfinityLink.getNext(2).getKey());
        assertEquals(1, list.negativeInfinityLink.getNext(1).getKey());

        assertEquals(list.negativeInfinityLink, list.infinityLink.getPrev(6));
        assertEquals(3, list.infinityLink.getPrev(5).getKey());
        assertEquals(3, list.infinityLink.getPrev(4).getKey());
        assertEquals(3, list.infinityLink.getPrev(3).getKey());
        assertEquals(3, list.infinityLink.getPrev(2).getKey());
        assertEquals(3, list.infinityLink.getPrev(1).getKey());

        FloorsArrayLink highestLink = list.negativeInfinityLink.getNext(5);
        assertEquals(list.infinityLink, highestLink.getNext(5));
        assertEquals(list.infinityLink, highestLink.getNext(4));
        assertEquals(list.infinityLink, highestLink.getNext(3));
        assertEquals(list.infinityLink, highestLink.getNext(2));
        assertEquals(list.infinityLink, highestLink.getNext(1));
        assertEquals(list.negativeInfinityLink, highestLink.getPrev(5));
        assertEquals(2.5, highestLink.getPrev(4).getKey());
        assertEquals(2.5, highestLink.getPrev(3).getKey());
        assertEquals(2.5, highestLink.getPrev(2).getKey());
        assertEquals(2.5, highestLink.getPrev(1).getKey());

        FloorsArrayLink thirdLink = highestLink.getPrev(4);
        assertEquals(3, thirdLink.getNext(4).getKey());
        assertEquals(3, thirdLink.getNext(3).getKey());
        assertEquals(3, thirdLink.getNext(2).getKey());
        assertEquals(3, thirdLink.getNext(1).getKey());
        assertEquals(list.negativeInfinityLink, thirdLink.getPrev(4));
        assertEquals(list.negativeInfinityLink, thirdLink.getPrev(3));
        assertEquals(1, thirdLink.getPrev(2).getKey());
        assertEquals(2, thirdLink.getPrev(1).getKey());

        FloorsArrayLink secondLink = thirdLink.getPrev(1);
        assertEquals(2.5, secondLink.getNext(1).getKey());
        assertEquals(1, secondLink.getPrev(1).getKey());

        FloorsArrayLink firstLink = list.negativeInfinityLink.getNext(1);
        assertEquals(2.5, firstLink.getNext(2).getKey());
        assertEquals(2, firstLink.getNext(1).getKey());

        assertEquals(list.negativeInfinityLink, firstLink.getPrev(2));
        assertEquals(list.negativeInfinityLink, firstLink.getPrev(1));
    }

    @org.junit.jupiter.api.Test
    void remove() {
    }

    @org.junit.jupiter.api.Test
    void lookup() {
    	 FloorsArrayList list = getList();
    	 assertEquals(2.5, list.lookup(2.5).getKey());
    	 assertEquals(1, list.lookup(1).getKey());
    	 assertEquals(2, list.lookup(2).getKey());
    	 assertEquals(3, list.lookup(3).getKey());
    	 assertEquals(null, list.lookup(9));
    	
    	
    }

    @org.junit.jupiter.api.Test
    void successor() {
    }

    @org.junit.jupiter.api.Test
    void predecessor() {
    }

    @org.junit.jupiter.api.Test
    void minimum() {
    }

    @org.junit.jupiter.api.Test
    void maximum() {
    }

    private FloorsArrayList getEmptyList() {
        FloorsArrayList list = new FloorsArrayList(6);
        return list;
    }
    private FloorsArrayList getList() {
        FloorsArrayList list = getEmptyList();
        list.insert(1, 2);
        list.insert(2, 1);
        list.insert(3, 5);
        list.insert(2.5, 4);

        return list;
    }
}