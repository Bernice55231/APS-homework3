import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] text = br.readLine().split(" ");
        int N = Integer.parseInt(text[0]);
        int C = Integer.parseInt(text[1]);

        Queue<Integer> res = dejavu(N, C);
        String output = "" + N + " " + C + ":";
        for(Integer item : res) {
            output += " " + item;
        }
        System.out.println(output);

    }

    public static Queue<Integer> dejavu(int N, int C) {
        boolean[] arr = new boolean[N+1];

        int m;
        for(int i = 2; i <= N; i++) {
            if(!arr[i]) {
                m = 2;
                while(i * m <= N) {
                    arr[i*m] = true;
                    m++;
                }
            }
        }

        Queue<Integer> primeList = new LinkedList<>();
        for(int i = 1; i < arr.length; i++) {
            if(!arr[i])
                primeList.add(i);
        }

        if(2*C > primeList.size()) {
            return primeList;
        } else {
            Queue<Integer> res = new LinkedList<>();
            int middle = primeList.size() / 2;
            int interval;
            if(primeList.size() % 2 == 0) {
                interval = C;
                int index = 0;
                for(Integer item : primeList) {
                    if((index >= middle - interval) && (index <= middle + interval -1)) {
                        res.add(item);
                    }
                    index++;
                }
            } else {
                interval = (2 * C - 1) / 2;
                int index = 0;
                for(Integer item : primeList) {
                    if((index >= middle - interval) && (index <= middle + interval)) {
                        res.add(item);
                    }
                    index++;
                }
            }
            return res;
        }
        
    }
}