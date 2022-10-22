import java.util.*;
import java.io.*;

public class Main {
    public static boolean LEFT = true;
    public static boolean RIGHT = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] text = br.readLine().split(" ");
        int N = Integer.parseInt(text[0]);
        int T = Integer.parseInt(text[1]);
        int num = Integer.parseInt(text[2]);

        String[] cars_info;
        // use two queues to store cars info
        Queue<String> leftbank = new LinkedList<>();
        Queue<String> rightbank = new LinkedList<>();
        int[] finish_time = new int[num];

        for(int i = 0; i < num; i++) {
            cars_info = br.readLine().split(" ");
            int arrive_time = Integer.parseInt(cars_info[0]);

            String info = i + " " + arrive_time;

            if(cars_info[1].charAt(0) == 'l') {
                leftbank.add(info);
            } else {
                rightbank.add(info);
            }
        }

        int clock = 0;
        boolean ferry_pos = LEFT;

        while(leftbank.size()+rightbank.size() != 0 ) {
            // if there isn't any cars waiting on either bank
            // the ferry waits until one comes
            int earliest_car = Integer.MAX_VALUE;
            if(!leftbank.isEmpty()) 
                earliest_car = Integer.parseInt(leftbank.peek().split(" ")[1]);
            if(!rightbank.isEmpty()) 
                earliest_car = Math.min(earliest_car, Integer.parseInt(rightbank.peek().split(" ")[1]));
            
            // set the clock to be the time that the earliest car can board
            clock = Math.max(clock, earliest_car);
            // System.out.println("enter here, clock time: " + clock);

            int load_count = 0;
            // if two cars arrive at the same time but at different bank
            // then consider the bank where the ferry is current at
            if(ferry_pos) {
                // check whether left bank has waiting car (check whether the car has arrived)
                // check whether the ferry is able to load car
                
                while (!leftbank.isEmpty()
                    && (Integer.parseInt(leftbank.peek().split(" ")[1]) <= clock)
                    && load_count < N ) 
                {
                    int index = Integer.parseInt(leftbank.peek().split(" ")[0]);
                    finish_time[index] = clock + T;
                    leftbank.remove();
                    load_count += 1;
                }
            } else { 
                // same as the left bank case

                while (!rightbank.isEmpty()
                    && (Integer.parseInt(rightbank.peek().split(" ")[1]) <= clock)
                    && (load_count < N)) 
                {
                    int index = Integer.parseInt(rightbank.peek().split(" ")[0]);
                    finish_time[index] = clock + T;
                    rightbank.remove();
                    load_count += 1;
                }

            }  
            clock += T;
            ferry_pos = !ferry_pos;
        }
        for (int i = 0; i < num; i++) {
            System.out.println(finish_time[i]);
        }
            


    }

}
