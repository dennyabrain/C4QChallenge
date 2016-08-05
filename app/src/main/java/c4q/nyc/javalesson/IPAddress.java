package c4q.nyc.javalesson;

import java.util.regex.Pattern;

/**
 * Created by abrain on 8/4/16.
 */
public class IPAddress {
    private String address;
    private String[] parts;
    private String[] binaryStringParts;
    private String longBinaryString="";

    /*
        Constructor stores the IP address in a string
        the parts array stores all the integers as distinct elements :
            for example, for the ip address 10.100.20.19
                parts[0]=10, parts[2]=100, parts[3]=20, parts[4]=19
        binaryStringParts stores the corresponding binary String for part array
        longBinaryString stores the ip address as one long string
            for example, for the IP address 12.23.34.45
               longBinaryString stores 00001100000101110010001000101101
     */
    IPAddress(String ip){
        address=ip;
        parts = ip.split(Pattern.quote("."));
        binaryStringParts = new String[4];
        longBinaryString=convertIPToLongBinaryString();
    }

    /*
        This converts the integer portions of the IP address into binary strings and combines them into
        one long string.
     */
    private String convertIPToLongBinaryString(){
        for(int i=0;i<4;i++){
            int temp = Integer.parseInt(parts[i]);
            binaryStringParts[i]=Integer.toBinaryString(temp);
            binaryStringParts[i]=("00000000"+binaryStringParts[i]).substring(binaryStringParts[i].length());
            //a trick I found here - http://stackoverflow.com/questions/17098329/add-leading-zeroes-to-a-string
            longBinaryString=longBinaryString+binaryStringParts[i];
        }
        return longBinaryString;
    }

    public String getLongBinaryString(){
        return longBinaryString;
    }

    /*
        Since "all addresses in the block share the same leading (most significant) bits,
        in their binary representation", this function compares the leading bits of the two IP addresses
        depending on the blockSize
     */
    public boolean isInBlock(IPAddress ipaddress, int blockSize){
        String commonBitsIP1 = longBinaryString.substring(0,blockSize);
        String commonBitsIP2 = ipaddress.longBinaryString.substring(0,blockSize);
        if(commonBitsIP1.equals(commonBitsIP2)){
            return true;
        }else{
            return false;
        }
    }

    /*
        enumerateBlock appends all permutations of binary numbers to the end of the leadingBits of an IPAddress
        (depending on the block size)
        It then calls longBinaryToIP() to generate dot notation IP strings and prints it out.
     */

    public void enumerateBlock(int blockSize){
        String Mask = longBinaryString.substring(0,blockSize);
        String paddingString ="";
        for(int i=0;i<32-blockSize;i++){
            paddingString+="0";
        }
        System.out.println(paddingString);
        for(int i=0;i<Math.pow(2,32-blockSize);i++){
            String binary = Integer.toBinaryString(i);
            binary = (paddingString+binary).substring(binary.length());
            String appendedIP = Mask+binary;
            System.out.println(longBinaryStringToIP(appendedIP));
            //System.out.println(appendedIP);
        }
    }

    /*
        longBinaryStringToIP splits the longBinaryString to 4 strings (each 8 in length) and converts in
        into integers and inserts a dot as separator.
     */
    public String longBinaryStringToIP(String ipAddressString){
        //System.out.println("inside longBinaryString");
        //System.out.println(ipAddressString);
        String ip="";
        String[] ipParts=new String[4];
        int [] ipPartsInt=new int[4];
        for(int i=0;i<4;i++){
            ipParts[i]=ipAddressString.substring(8*i,8*i+8);
            ipPartsInt[i]=Integer.parseInt(ipParts[i],2);
            ip+=ipPartsInt[i]+".";
            ip=ip.substring(0,ip.length()-1);
        }
        return ip;
    }
}
