package crud.website;

public class Pagination {

    private final boolean prev;
    private final boolean next;
    private final long prevVal;
    private final long curVal;
    private final long nextVal;


    public Pagination(Long cur, Long max) {
        this.prev = cur > 1L;
        this.prevVal = cur-1;
        this.curVal = cur;
        this.nextVal = cur+1;
        this.next = cur < max;
    }
}
