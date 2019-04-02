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
        //@TODO: implement
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
        int i = this.maxArrSize - 1;
        while (nextLink != prevLink || i != 0) {
            if (key == currLink.getKey())
                return currLink;

            if (key > currLink.getKey() && key < nextLink.getKey()) {
                if (i > 0)
                    i--;
                prevLink = currLink;
                currLink = nextLink;
                nextLink = nextLink.getNext(i);

            } else if (key < currLink.getKey() && key > nextLink.getKey()) {
                if (i > 0)
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

    private boolean isBetween(double curent, double next, double key) {
        return () || (key < curent && key > next);
    }
}
