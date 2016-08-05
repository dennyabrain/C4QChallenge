package c4q.nyc.javalesson;

import java.util.Scanner;
import java.util.regex.Pattern;
import c4q.nyc.javalesson.IPAddress;

/**
 * Created by abrain on 8/2/16.
 */
public class Main {
    public static void main(String[] args){
        /*IPAddress ip1 = new IPAddress("12.23.34.45");
        IPAddress ip2 = new IPAddress("12.22.35.44");

        System.out.println(ip1.isInBlock(ip2,16));


        IPAddress ip3 = new IPAddress("12.23.34.45");
        ip3.enumerateBlock(29);*/
        if(args.length==1){
            String[] input = args[0].split("/");
            IPAddress ip = new IPAddress(input[0]);
            ip.enumerateBlock(Integer.parseInt(input[1]));
        }
        if(args.length==2){
            String IP1 = args[0];
            String IP2 = args[1];
            String[] IP1Parts = IP1.split("/");
            String IP1Address = IP1Parts[0];
            int IP1BlockSize = Integer.parseInt(IP1Parts[1]);

            IPAddress ip1 = new IPAddress(IP1Address);
            IPAddress ip2 = new IPAddress(IP2);

            System.out.println(ip1.isInBlock(ip2, IP1BlockSize));
        }
    }

}