package base;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Net {
    public static void main(String[] args){
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
