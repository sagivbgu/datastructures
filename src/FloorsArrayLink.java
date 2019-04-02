public class FloorsArrayLink {
    
    private	double key;
    protected FloorsArrayLink nextKey;
    protected FloorsArrayLink prevKey;
    private final int arrSize;
    private FloorsArrayLink[] arrFront;
    private FloorsArrayLink[] arrBack;
	
    public FloorsArrayLink(double key, int arrSize){
       this.key=key;
       this.arrSize=arrSize;
       
    }

    public double getKey() {
        return this.key;
    }

    public FloorsArrayLink getNext(int i) {
        if(this.arrSize<i)
        	return null;
        return this.arrFront[i-1];
    }

    public FloorsArrayLink getPrev(int i) {
        if(this.arrSize<i)
        	return null;
        return this.arrBack[i-1];
    }

    public void setNext(int i, FloorsArrayLink next) {
        if(this.arrSize>=i)
        {
        	this.arrFront[i-1]=next;
        }

    }

    public void setPrev(int i, FloorsArrayLink prev) {
        if(this.arrSize>=i)
        {
        	this.arrBack[i-1]=next;
        }
    }

    public int getArrSize(){
         return arrSize;
    }
    
    
}

