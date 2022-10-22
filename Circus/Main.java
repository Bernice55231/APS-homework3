import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        
        String[] text;
        while(true) {
            text = br.readLine().split(" ");
            if(Integer.parseInt(text[0]) == 0)
                break;
            else {
                if(check(len, text))
                    System.out.println("Yes");
                else
                    System.out.println("No");
            }
        }
    }

    public static boolean check(int len, String[] text) {
        Stack<Integer> order = new Stack<>();
        int ele;
        int max = 0;
        for(int i = 0; i < len; i++) {
            ele = Integer.parseInt(text[i]);
            if(ele > max) {
                int j = max+1;
                while(j < ele) {
                    order.push(j);
                    j++;
                }
                max = ele;
            } else {
                if(ele != order.pop()) return false;
                else continue;
            }
        }
        return true;
    }
}
