import java.util.Hashtable;

public class PassOne {
    Hashtable<String,MneumonicTable> is;

    public void createIS(){
        MneumonicTable m = new MneumonicTable(STOP, null, 0);
        is.put(null, m);
    }
}
