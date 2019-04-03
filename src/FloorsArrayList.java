public class FloorsArrayList implements DynamicSet {
    private FloorsArrayLink infinityLink;
    private FloorsArrayLink negativeInfinityLink;
    private int size;
    private int maxArrSize;


    public FloorsArrayList(int N) {
        this.infinityLink = new FloorsArrayLink(Double.POSITIVE_INFINITY, N);
        this.negativeInfinityLink = new FloorsArrayLink(Double.NEGATIVE_INFINITY, N);
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
        FloorsArrayLink prevLink;
        FloorsArrayLink currLink = this.negativeInfinityLink.getNext(i);

        while (i != 0) {
            if (key > currLink.getKey()) {
                prevLink = currLink;
                currLink = currLink.getNext(i);

                if (key <= currLink.getKey()) {
                    if (i <= arrSize)
                        insertBetween(newLink, prevLink, currLink, i);
                    i--;
                }

            } else {
                prevLink = currLink;
                currLink = currLink.getPrev(i);

                if (key >= currLink.getKey()) {
                    if (i <= arrSize)
                        insertBetween(newLink, currLink, prevLink, i);
                    i--;
                }
            }
        }

        this.size++;
        if (arrSize > this.maxArrSize)
            this.maxArrSize = arrSize;
    }

    @Override
    public void remove(FloorsArrayLink toRemove) {
        this.maxArrSize = 0;

        for (int i = toRemove.getArrSize(); i > 1; i--) {
            FloorsArrayLink prevLink = toRemove.getPrev(i);
            FloorsArrayLink nextLink = toRemove.getNext(i);

            prevLink.setNext(i, nextLink);
            nextLink.setPrev(i, prevLink);

            if (prevLink != this.negativeInfinityLink && prevLink.getArrSize() > this.maxArrSize)
                this.maxArrSize = prevLink.getArrSize();
            if (nextLink != this.infinityLink && nextLink.getArrSize() > this.maxArrSize)
                this.maxArrSize = nextLink.getArrSize();
        }

        this.size--;
    }

    @Override
    public FloorsArrayLink lookup(double key) {
        int i = this.maxArrSize;
        FloorsArrayLink currLink = this.negativeInfinityLink.getNext(i);

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
        return link.getNext(1).getKey();
    }

    @Override
    public double predecessor(FloorsArrayLink link) {
        return link.getPrev(1).getKey();
    }

    @Override
    public double minimum() {
        return this.negativeInfinityLink.getNext(1).getKey();
    }

    @Override
    public double maximum() {
        return this.infinityLink.getPrev(1).getKey();
    }

    private void insertBetween(FloorsArrayLink newLink, FloorsArrayLink predecessor, FloorsArrayLink successor, int i) {
        predecessor.setNext(i, newLink);
        successor.setPrev(i, newLink);
        newLink.setNext(i, successor);
        newLink.setPrev(i, predecessor);
    }
}
