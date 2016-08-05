package c4q.nyc.javalesson;

import java.util.Scanner;
import java.util.regex.Pattern;
import c4q.nyc.javalesson.IPAddress;

/**
 * Created by abrain on 8/2/16.
 */
public class Main {
    public static void main(String[] args){
        IPAddress ip1 = new IPAddress("12.23.34.45");
        IPAddress ip2 = new IPAddress("12.22.35.44");

        IPAddress ip3 = new IPAddress("12.23.34.45");

        System.out.println(ip1.getLongBinaryString());

        //System.out.println(ip1.isInBlock(ip2,16));
        System.out.println(ip3.getLongBinaryString());
        ip3.enumerateBlock(29);

    }

}