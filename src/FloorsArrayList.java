public class FloorsArrayList implements DynamicSet {
    private FloorsArrayLink infinityLink;
    private FloorsArrayLink negativeInfinityLink;
    private int size;
    private int maxArrSize; // arrSize of the link with the maximum arrSize in the list


    public FloorsArrayList(int N) {
        this.infinityLink = new FloorsArrayLink(Double.POSITIVE_INFINITY, N);
        this.negativeInfinityLink = new FloorsArrayLink(Double.NEGATIVE_INFINITY, N);
        initializeInfinities(N);
        this.size = 0;
        this.maxArrSize = 1;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * Iteration algorithm resembles lookup method, read its docstring.
     * In addition, if the key is between the two links, set the previous and the next links to point to the new link
     * instead of pointing each other.
     */
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
        if (toRemove.getArrSize() == this.maxArrSize)
            this.maxArrSize = 1;

        // Update the previous and the next link at same array indexes to point to each other
        for (int i = toRemove.getArrSize(); i > 1; i--) {
            FloorsArrayLink prevLink = toRemove.getPrev(i);
            FloorsArrayLink nextLink = toRemove.getNext(i);

            prevLink.setNext(i, nextLink);
            nextLink.setPrev(i, prevLink);

            // Update maxArrSize of the list if necessary
            if (prevLink != this.negativeInfinityLink && prevLink.getArrSize() > this.maxArrSize)
                this.maxArrSize = prevLink.getArrSize();
            if (nextLink != this.infinityLink && nextLink.getArrSize() > this.maxArrSize)
                this.maxArrSize = nextLink.getArrSize();
        }

        this.size--;
    }

    /**
     * Start with negative infinity link, and set i as the maximum arrSize of a link in the list.
     * Until the wanted link is found (or it's sure it isn't in the list),
     * Go to the next closest link (from the right or from the left, depending on the key) at the i-th index.
     * If the key is not between the two links, continue. Else, reduce i by 1 (so we don't go back to the previous
     * link) and then continue searching.
     */
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
        FloorsArrayLink nextLink = link.getNext(1);
        if (nextLink == this.infinityLink)
            return this.negativeInfinityLink.getKey();
        return nextLink.getKey();
    }

    @Override
    public double predecessor(FloorsArrayLink link) {
        FloorsArrayLink prevLink = link.getPrev(1);
        if (prevLink == this.negativeInfinityLink)
            return this.infinityLink.getKey();
        return prevLink.getKey();
    }

    @Override
    public double minimum() {
        return this.negativeInfinityLink.getNext(1).getKey();
    }

    @Override
    public double maximum() {
        return this.infinityLink.getPrev(1).getKey();
    }

    private void initializeInfinities(int N) {
        for (int i = 1; i <= N; i++) {
            this.negativeInfinityLink.setNext(i, this.infinityLink);
            this.negativeInfinityLink.setPrev(i, this.infinityLink);
            this.infinityLink.setNext(i, this.negativeInfinityLink);
            this.infinityLink.setPrev(i, this.negativeInfinityLink);
        }
    }

    private void insertBetween(FloorsArrayLink newLink, FloorsArrayLink predecessor, FloorsArrayLink successor, int i) {
        predecessor.setNext(i, newLink);
        successor.setPrev(i, newLink);
        newLink.setNext(i, successor);
        newLink.setPrev(i, predecessor);
    }
}
