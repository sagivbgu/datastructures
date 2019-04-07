public class FloorsArrayLink {

    protected double key;
    protected final int arrSize;
    protected FloorsArrayLink[] arrFront;
    protected FloorsArrayLink[] arrBack;

    public FloorsArrayLink(double key, int arrSize) {
        this.key = key;
        this.arrSize = arrSize;
        this.arrBack = new FloorsArrayLink[arrSize];
        this.arrFront = new FloorsArrayLink[arrSize];
    }

    public double getKey() {
        return this.key;
    }

    public FloorsArrayLink getNext(int i) {
        if (this.arrSize < i)
            return null;
        return this.arrFront[i - 1];
    }

    public FloorsArrayLink getPrev(int i) {
        if (this.arrSize < i)
            return null;
        return this.arrBack[i - 1];
    }

    public void setNext(int i, FloorsArrayLink next) {
        if (this.arrSize >= i) {
            this.arrFront[i - 1] = next;
        }
    }

    public void setPrev(int i, FloorsArrayLink prev) {
        if (this.arrSize >= i) {
            this.arrBack[i - 1] = prev;
        }
    }

    public int getArrSize() {
        return this.arrSize;
    }
}
