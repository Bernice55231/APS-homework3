import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        String[] arr = br.readLine().split("\\s+");
        int[] line = new int[length];
        for(int i = 0; i < length; i++) {
            line[i] = Integer.parseInt(arr[i]);
        }

        Stack<Integer> winner = new Stack<>();
        winner.push(line[0]);
        for(int i = 1; i < length; i++) {
            if(winner.size() == 0)
                winner.push(line[i]);
            else {
                if((winner.peek() + line[i]) % 2 == 0) 
                    winner.pop();
                else    
                    winner.push(line[i]);
            }
        }

        System.out.print(winner.size());
    }
}
