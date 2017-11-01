package aproject;

import java.io.*;

public class loader {
    static public int[] load(int length, int count, int set) throws FileNotFoundException, IOException 
    {
        int[] toRead = new int[length * count];
        int index = 0;
        BufferedReader r;
        String x;
        try{
            r = new BufferedReader(new FileReader(String.format("data%d.txt", set)));
            r.readLine();
            while((x = r.readLine()) != null)
                for(int i = 0; i < length + 1; i++)
                {
                    if(i != length - 1)
                    {
                        toRead[index] = Character.getNumericValue(x.charAt(i));
                        index++;
                    }
                }
        } catch(Exception e){System.out.println(e);}
        return toRead;
    }
}
