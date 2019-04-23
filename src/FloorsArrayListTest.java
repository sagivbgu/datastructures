import static org.junit.jupiter.api.Assertions.*;

class FloorsArrayListTest {
    @org.junit.jupiter.api.Test
    void getSize() {
        assertEquals(0, getEmptyList().getSize());
        assertEquals(4, getList().getSize());
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
        FloorsArrayList list = getList();
        list.remove(list.lookup(2.5));

        assertEquals(list.infinityLink, list.negativeInfinityLink.getNext(6));
        assertEquals(3, list.negativeInfinityLink.getNext(5).getKey());
        assertEquals(3, list.negativeInfinityLink.getNext(4).getKey());
        assertEquals(3, list.negativeInfinityLink.getNext(3).getKey());
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
        assertEquals(Double.NEGATIVE_INFINITY, highestLink.getPrev(4).getKey());
        assertEquals(Double.NEGATIVE_INFINITY, highestLink.getPrev(3).getKey());
        assertEquals(1, highestLink.getPrev(2).getKey());
        assertEquals(2, highestLink.getPrev(1).getKey());

        FloorsArrayLink firstLink = highestLink.getPrev(2);
        assertEquals(3, firstLink.getNext(2).getKey());
        assertEquals(2, firstLink.getNext(1).getKey());
        assertEquals(Double.NEGATIVE_INFINITY, firstLink.getPrev(2).getKey());
        assertEquals(Double.NEGATIVE_INFINITY, firstLink.getPrev(1).getKey());

        FloorsArrayLink secondLink = firstLink.getNext(1);
        assertEquals(3, secondLink.getNext(1).getKey());
        assertEquals(1, secondLink.getPrev(1).getKey());

        // Another remove
        list.remove(highestLink);
        assertEquals(2, list.infinityLink.getPrev(1).getKey());
        assertEquals(1, list.infinityLink.getPrev(2).getKey());
        for (int i = 3; i <= 6; i++) {
            assertEquals(Double.NEGATIVE_INFINITY, list.infinityLink.getPrev(i).getKey());
        }
    }

    @org.junit.jupiter.api.Test
    void lookup() {
        FloorsArrayList list = getEmptyList();
        assertNull(list.lookup(1));
    }

    @org.junit.jupiter.api.Test
    void lookup_empty_list() {
        FloorsArrayList list = getList();
        assertEquals(2.5, list.lookup(2.5).getKey());
        assertEquals(1, list.lookup(1).getKey());
        assertEquals(2, list.lookup(2).getKey());
        assertEquals(3, list.lookup(3).getKey());
        assertNull(list.lookup(9));
        assertNull(list.lookup(0.5));
        assertNull(list.lookup(1.5));
    }

    @org.junit.jupiter.api.Test
    void successor() {
        FloorsArrayList list = getList();

        double firstSuccessor = list.successor(list.lookup(1));
        assertEquals(2, firstSuccessor);

        double secondSuccessor = list.successor(list.lookup(2));
        assertEquals(2.5, secondSuccessor);

        double lastSuccessor = list.successor(list.lookup(3));
        assertEquals(Double.NEGATIVE_INFINITY, lastSuccessor);
    }

    @org.junit.jupiter.api.Test
    void predecessor() {
        FloorsArrayList list = getList();

        double firstPredecessor = list.predecessor(list.lookup(1));
        assertEquals(Double.POSITIVE_INFINITY, firstPredecessor);

        double secondPredecessor = list.predecessor(list.lookup(2));
        assertEquals(1, secondPredecessor);

        double lastPredecessor = list.predecessor(list.lookup(3));
        assertEquals(2.5, lastPredecessor);
    }

    @org.junit.jupiter.api.Test
    void minimum() {
        FloorsArrayList list = getList();
        assertEquals(1, list.minimum());
    }

    @org.junit.jupiter.api.Test
    void minimum_empty_list() {
        FloorsArrayList list = getEmptyList();
        assertEquals(Double.POSITIVE_INFINITY, list.minimum());
    }

    @org.junit.jupiter.api.Test
    void maximum() {
        FloorsArrayList list = getList();
        assertEquals(3, list.maximum());
    }

    @org.junit.jupiter.api.Test
    void maximum_empty_list() {
        FloorsArrayList list = getEmptyList();
        assertEquals(Double.NEGATIVE_INFINITY, list.maximum());
    }

    private FloorsArrayList getEmptyList() {
        return new FloorsArrayList(6);
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