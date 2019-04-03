public class FloorsArrayList implements DynamicSet {
    private FloorsArrayLink infintyLink;
    private FloorsArrayLink negtiveInfintyLink;
    private int size;
    private int maxArrSize;


    public FloorsArrayList(int N) {
        this.infintyLink = new FloorsArrayLink(Double.POSITIVE_INFINITY, N);
        this.negtiveInfintyLink = new FloorsArrayLink(Double.NEGATIVE_INFINITY, N);
        this.size = 0;
        this.maxArrSize = 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void insert(double key, int arrSize) {
        int i = this.maxArrSize;
        FloorsArrayLink newLink = new FloorsArrayLink(key, arrSize);
        FloorsArrayLink prevLink = null;
        FloorsArrayLink currLink = this.negtiveInfintyLink;
        FloorsArrayLink nextLink = currLink.getNext(i);

        while (nextLink != prevLink || i != 1) {
            if (key > currLink.getKey()) {
                if (key < nextLink.getKey()) {
                    if (i <= arrSize)
                        insertBetween(newLink, currLink, nextLink, i);

                    if (i > 1)
                        i--;
                }

                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getNext(i);

            } else { // key == currLink.getKey() isn't possible
                if (key > nextLink.getKey()) {
                    if (i == arrSize)
                        insertBetween(newLink, nextLink, currLink, i);
                    if (i > 1)
                        i--;
                }
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getPrev(i);
            }
        }
    }

    @Override
    public void remove(FloorsArrayLink toRemove) {
        for (int i = toRemove.getArrSize(); i > 1; i--) {
            FloorsArrayLink prevLink = toRemove.getPrev(i);
            FloorsArrayLink nextLink = toRemove.getNext(i);

            prevLink.setNext(i, nextLink);
            nextLink.setPrev(i, prevLink);
        }
    }

    @Override
    public FloorsArrayLink lookup(double key) {
        int i = this.maxArrSize;
        FloorsArrayLink currLink = this.negtiveInfintyLink.getNext(i);

        while (i != 0) {
            if (key > currLink.getKey()) {
                currLink = currLink.getNext(i);

                if (key <= currLink.getKey())
                    i--;

            } else {
                currLink = currLink.getPrev(i);

                if (key >= currLink.getKey())
                    i--;
            }

            if (key == currLink.getKey())
                return currLink;
        }
        return null;
    }

    @Override
    public double successor(FloorsArrayLink link) {
        //@TODO: implement
        return 0;
    }

    @Override
    public double predecessor(FloorsArrayLink link) {
        //@TODO: implement
        return 0;
    }

    @Override
    public double minimum() {
        return this.negtiveInfintyLink.getNext(0).getKey();
    }

    @Override
    public double maximum() {
        return this.infintyLink.getPrev(0).getKey();
    }

    private void insertBetween(FloorsArrayLink newLink, FloorsArrayLink predecessor, FloorsArrayLink successor, int i) {
        predecessor.setNext(i, newLink);
        successor.setPrev(i, newLink);
        newLink.setNext(i, successor);
        newLink.setPrev(i, predecessor);
    }
}
