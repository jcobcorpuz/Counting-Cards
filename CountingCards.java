import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CountingCards {
    public static int[] badShuffle(int[] array){
        List<Integer> copy = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            copy.add(array[i]);
        }

        int[] shuffled = new int[array.length];
        Random rand = new Random();

        for (int i = 0; i < array.length; i++){
            int index = rand.nextInt(copy.size());
            shuffled[i] = copy.remove(index);
        }
        return shuffled;
    }

    public static int[] betterShuffle(int[] array){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++){
            list.add(array[i]);
        }

        int[] shuffled = new int[array.length];
        Random rand = new Random();

        for (int i = 0; i < array.length; i++){
            int index = rand.nextInt(list.size());
            shuffled[i] = list.remove(index);
        }
        return shuffled;
    }

    public static void bestShuffle(int[] array){
        Random rand = new Random();
        int m = array.length;
        while (m > 0){
            int i = rand.nextInt(m--);
            int temp = array[m];
            array[m] = array[i];
            array[i] = temp;
        }
    }

    public static int[] generateArray(int n){
        int[] array = new int[n];
        for (int i = 0; i < n; i++){
            array[i] = i + 1;
        }
        return array;
    }

    public static void benchmark(int n, int trials){
        System.out.println("\nArray size: " + n);

        for(int i = 0; i < trials; i++){
            int[] array = generateArray(n);

            // badShuffle timing
            long start = System.nanoTime();
            badShuffle(Arrays.copyOf(array,array.length));
            long end = System.nanoTime();
            System.out.println("Worst Shuffle: " + (end-start) + " ns");

            // betterShuffle timing
            start = System.nanoTime();
            betterShuffle(Arrays.copyOf(array,array.length));
            end = System.nanoTime();
            System.out.println("Improved Shuffle: " + (end-start) + " ns");

            // bestShuffle timing
            start = System.nanoTime();
            bestShuffle(Arrays.copyOf(array,array.length));
            end = System.nanoTime();
            System.out.println("Fisher-Yates Shuffle: " + (end-start) + " ns");

        }
    }

    public static void main (String[] args){
        int[] sizes = {10, 100, 1000, 10000};
        int trials = 4;

        for (int i = 0; i < sizes.length; i++){
            benchmark(sizes[i], trials);
        }
    }
}
