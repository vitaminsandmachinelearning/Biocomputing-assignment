
package aproject;

import java.io.IOException;
import java.util.Arrays;

public class Overlord {
    public static void main(String[] args) throws IOException
    {
        
        Aproject apa;
        int testcount = 100;
        int progress = 0;
        
        int dataset = 2;
        int datacount = 64;
        int rulelength = 7;
        int min = 64;
        
        int rulecount = 10;
        int poolsize = 300;
        int generations = 500;
        
        float mutationrate = 1 / (rulecount * rulelength) + 0.01f;
        
        int[] data = loader.load(rulelength, datacount, dataset);
        
        int avg = 0;
        int max = 0;
        
        int[] r = new int[0];
        for(int i = 0; i < testcount; i++)
        {
            int f;
            int[] t;
            System.out.println(progress++);
            apa = new Aproject(datacount, poolsize, rulecount, rulelength, generations, mutationrate, dataset);
            Rule temp = apa.run(data);
            t = temp.r;
            f = temp.fitness;
            avg += f;
            if(f >= max)
            {
                max = f;
                r = Arrays.copyOf(t, t.length);
            }
            if(f < min)
                min = f;
        }
        avg /= testcount;
        System.out.println("Average: " + avg);
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
        for(int i = 0; i < rulecount * rulelength; i++)
        {
            if(i % rulelength == 0)
                System.out.print(" ");
            System.out.print(r[i]);
        }
        System.out.println();
    }
        
}
