import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] text = br.readLine().split(" ");
        int l = Integer.parseInt(text[0]);
        int r = Integer.parseInt(text[1]);

        int mode = primeGap(l, r);
        System.out.print(mode);
        

    }

    public static int primeGap(int l, int r) {
        boolean[] arr = new boolean[r+1];

        int m;
        for(int i = 2; i <= r; i++) {
            if(!arr[i]) {
                m = 2;
                while(i * m <= r) {
                    arr[i*m] = true;
                    m++;
                }
            }
        }

        Queue<Integer> primeList = new LinkedList<>();
        for(int i = l; i < arr.length; i++) {
            if(!arr[i])
                primeList.add(i);
        }
        Map<Integer, Integer> gapmap = new HashMap<Integer, Integer>();

        if(primeList.size() <= 1) return -1;

        int prev = primeList.remove();
        int gap = 0;
        int curr;
        while(!primeList.isEmpty()) {
            curr = primeList.remove();
            gap = curr - prev;
            prev = curr;
            gapmap.put(gap, gapmap.getOrDefault(gap, 0) + 1);
        }
        int mode = -1;
        int max = -1;
        for (Map.Entry<Integer,Integer> entry : gapmap.entrySet()) {
            // System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
            int key = entry.getKey();
            int value = entry.getValue();
            if(value > max) {
                max = value;
                mode = key;
            } else if (value == max) {
                mode = -1;
            } else {
                continue;
            }
        }
        return mode;
    }
}