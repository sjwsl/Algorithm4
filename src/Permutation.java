import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k == 0) return;
        /*

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
            if(randomizedQueue.size()==k)break;
        }

        while (!randomizedQueue.isEmpty()) {
            StdOut.println(randomizedQueue.dequeue());
        }
        */



        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();

        while(!StdIn.isEmpty()){
            randomizedQueue.enqueue(StdIn.readString());
            if(randomizedQueue.size()==k)break;
        }
        int n=k+1;
        while(!StdIn.isEmpty()){
            String now=StdIn.readString();
            int randomIndex=StdRandom.uniform(1,n+1);
            if(randomIndex<=k){
                randomizedQueue.dequeue();
                randomizedQueue.enqueue(now);
            }
            n++;
        }

        for(int i=0;i<k;i++){
            StdOut.println(randomizedQueue.dequeue());
        }

    }
}
