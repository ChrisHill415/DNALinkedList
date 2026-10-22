import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkStrand implements IDnaStrand
{
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private Node myCurrent;
    private int myLocalIndex;


    private static class Node {
        String info;
        Node next;
        Node(String dna){
            info = dna;
            next = null;
        }
    }

    public LinkStrand() 
    {
        this("");
    }
    public LinkStrand(String one)
    {
        initialize(one);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        return super.equals(arg0);
    }

    @Override
    protected void finalize() throws Throwable {
        // TODO Auto-generated method stub
        super.finalize();
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder bowl = new StringBuilder();
        Node current = myFirst;
        while(current != null)
        {
            bowl.append(current.info);
            current = current.next;
        }
        return bowl.toString();
    }

    @Override
    public IDnaStrand append(String dna) {
       myAppends++;
       myLast.next = new Node(dna);
       mySize += dna.length();
       myLast = myLast.next;
       return this;
    }

    @Override
    public char charAt(int index) {
        if(index < 0 || index >= mySize)
        {
            throw new IndexOutOfBoundsException("nothing");
        }
        if(index < myIndex)
        {
            myIndex = 0;
            myLocalIndex = 0;
            myCurrent = myFirst;
        }
        int spot = myIndex;
        int dex = myLocalIndex;
        Node list = myCurrent;
        while(spot != index)
        {
            spot++;
            dex++;
            if(dex >= list.info.length())
            {
                dex = 0;
                list = list.next;
            }
        }
        myIndex = index;
        myLocalIndex = dex;
        myCurrent = list;
        return list.info.charAt(dex);
    }

    @Override
    public IDnaStrand cutAndSplice(String enzyme, String splicee) {
        // TODO Auto-generated method stub
        return IDnaStrand.super.cutAndSplice(enzyme, splicee);
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        
        return new LinkStrand(source);
    }

    @Override
    public void initialize(String source) 
    {
        myAppends = 0;
        myFirst = new Node(source);
        myLast = myFirst;
        myCurrent = myFirst;
    
        myIndex = 0;
        mySize = source.length();
        myLocalIndex = 0;
        myAppends = 0;
        
    }
    

    @Override
    public Iterator<Character> iterator() {
        // TODO Auto-generated method stub
        return IDnaStrand.super.iterator();
    }

    @Override
    public IDnaStrand reverse() {
        LinkStrand rev = new LinkStrand();
        Node fir = null;
        Node las = null;
        Node cur = myFirst;
        rev.myIndex = 0;
        rev.myLocalIndex = 0;
        while(cur != null)
        {
            Node temp = new Node(new StringBuilder(cur.info).reverse().toString());
            temp.next = fir;
            if(las == null)
            {
                las = temp;
            }
            cur = cur.next;
            fir = temp;

        }
        rev.myFirst = fir;
        rev.myLast = las;
        rev.myAppends = myAppends;
        rev.mySize = mySize;
        rev.myCurrent = fir;
        return rev;
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public String strandInfo() {
        // TODO Auto-generated method stub
        return IDnaStrand.super.strandInfo();
    }

    @Override
    public void forEach(Consumer<? super Character> arg0) {
        // TODO Auto-generated method stub
        IDnaStrand.super.forEach(arg0);
    }

    @Override
    public Spliterator<Character> spliterator() {
        // TODO Auto-generated method stub
        return IDnaStrand.super.spliterator();
    }

}