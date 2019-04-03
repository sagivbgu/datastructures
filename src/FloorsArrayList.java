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
        //@TODO: implement
        return 0;
    }

    @Override
    public void insert(double key, int arrSize) {
        FloorsArrayLink newLink = new FloorsArrayLink(key, arrSize);
        FloorsArrayLink prevLink = null;
        FloorsArrayLink currLink = this.negtiveInfintyLink;
        FloorsArrayLink nextLink = this.negtiveInfintyLink.getNext(this.maxArrSize - 1);
        int i = this.maxArrSize;
        while (nextLink != prevLink || i != 1) {
            if (key == currLink.getKey())
                return currLink;

            if (key > currLink.getKey() && key < nextLink.getKey()) {
                if (i == arrSize) {
                    currLink.setPrev(i, newLink);
                    prevLink.setNext(i, newLink);
                    newLink.setPrev(i, prevLink);
                    newLink.setNext(i, nextLink);
                }
                if (i > 1)
                    i--;
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getNext(i);

            } else if (key < currLink.getKey() && key > nextLink.getKey()) {
                if (i == arrSize) {
                    currLink.setNext(i, newLink);
                    prevLink.setPrev(i, newLink);
                    newLink.setNext(i, prevLink);
                    newLink.setPrev(i, nextLink);
                }
                if (i > 1)
                    i--;
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getPrev(i);

            } else if (key > currLink.getKey() && key > nextLink.getKey()) {
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getNext(i);

            } else if (key < currLink.getKey() && key < nextLink.getKey()) {
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getPrev(i);
            }
        }
    }

    @Override
    public void remove(FloorsArrayLink toRemove) {
        //@TODO: implement
    }

    @Override
    public FloorsArrayLink lookup(double key) {
        FloorsArrayLink prevLink = null;
        FloorsArrayLink currLink = this.negtiveInfintyLink;
        FloorsArrayLink nextLink = this.negtiveInfintyLink.getNext(this.maxArrSize - 1);
        int i = this.maxArrSize;
        while (nextLink != prevLink || i != 1) {
            if (key == currLink.getKey())
                return currLink;

            if (key > currLink.getKey() && key < nextLink.getKey()) {
                if (i > 1)
                    i--;
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getNext(i);

            } else if (key < currLink.getKey() && key > nextLink.getKey()) {
                if (i > 1)
                    i--;
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getPrev(i);

            } else if (key > currLink.getKey() && key > nextLink.getKey()) {
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getNext(i);

            } else if (key < currLink.getKey() && key < nextLink.getKey()) {
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getPrev(i);
            }
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
        //@TODO: implement
        return 0;
    }

    @Override
    public double maximum() {
        //@TODO: implement
        return 0;
    }
}
