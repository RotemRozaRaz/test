package test;
import java.util.ArrayList;
import java.util.BitSet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;



public class BloomFilter {

    private BitSet bitSet;
    private String hashFunction1;
    private String hashFunction2;

    public BloomFilter(int i, String string, String string2) {
        this.bitSet = new BitSet(i);
        this.hashFunction1 = string;
        this.hashFunction2 = string2;
        
    }

    private void applyHashFunc(String hashFunction, String w) throws NoSuchAlgorithmException {
        
        MessageDigest MD = MessageDigest.getInstance(hashFunction);
        byte[] bts = MD.digest(w.getBytes());
        BigInteger bi = new BigInteger(1, bts);
        int bitsAsInt = Math.abs(bi.intValue());
        int bitIndex = bitsAsInt % this.bitSet.size();
        bitSet.set(bitIndex, true);
    }

    public void add(String w) { 

        try {
            //apply hashfunc1
            applyHashFunc(hashFunction1, w);
            //apply hasgfunc2
            applyHashFunc(hashFunction2, w);
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("I'm sorry, but MD5 is not a valid message digest algorithm");
        } 
        
    }

    private boolean isBitSet(String hashFunction, String w) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(hashFunction);
        byte[] bts = md.digest(w.getBytes());
        BigInteger bi = new BigInteger(1, bts);
        int bitsAsInt = bi.intValue();
        int bitIndex = Math.abs(bitsAsInt) % this.bitSet.size();
        return bitSet.get(bitIndex);
    }



    public boolean contains(String w) {
        
        try {    
            boolean contains1 = isBitSet(hashFunction1, w);
            boolean contains2 = isBitSet(hashFunction2, w);
            return contains1 && contains2;
        }
        catch(NoSuchAlgorithmException e) {
            System.err.println("I'm sorry, but MD5 is not a valid message digest algorithm");
            return false;
        }
        
    }

    private ArrayList<Integer> ConvertBitSetToList(){
        ArrayList<Integer> bitsArray = new ArrayList<>();

        for (int i = 0; i < this.bitSet.length(); i++) {
            if (this.bitSet.get(i) == true) {
                bitsArray.add(1);
            }
            else {
                bitsArray.add(0);
            }
        }

        return bitsArray;
    }

    private StringBuilder ConvertToStringBuilder(ArrayList<Integer> bitsArray){
        StringBuilder sb = new StringBuilder();

        for (Integer i : bitsArray) {
            sb.append(i.toString());
        }

        return sb;
    }

    @Override
    public String toString(){
        
        ArrayList<Integer> bitsArray = ConvertBitSetToList();

        StringBuilder sb = ConvertToStringBuilder(bitsArray);
        String s = sb.toString();

        return s;
        
    }

}
