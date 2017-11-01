/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aproject;

import java.util.Random;

/**
 *
 * @author jm2-radford
 */
public class Rule {
    public int[] r;
    public int fitness = 0;
    
    public Rule(int rulecount, int rulelength, Random random)
    {
        r = new int[rulecount * rulelength];
        for(int i = 0; i < rulecount * rulelength; i++)
            r[i] = random.nextInt(3);
        for(int i = 0; i < rulecount; i++)
            r[i * rulelength + (rulelength - 1)] = random.nextInt(2);
    }
    
    public Rule(int[] _r)
    {
        r = _r;
    }
    
    public void calculate(int[] data, int rulelength, int rulecount)
    {
        fitness = 0;
        for(int i = 0; i < data.length / rulelength; i++)
            for(int j = 0; j < rulecount; j++)
                if(matchRule(data, j * rulelength, i * rulelength, rulelength))
                {
                    if(r[(j * rulelength) + rulelength - 1] == data[(i * rulelength) + rulelength - 1])
                        fitness++;
                    break;
                }
    }
    
    boolean matchRule(int[] data, int rl, int dl, int rulelength)
    {
        for(int i = 0; i < rulelength - 1; i++)
            if(r[rl + i] != 2 && r[rl + i] != data[dl + i])
                return false;
        return true;
    }
    
    @Override
    public String toString()
    {
        String s = "";
        for(int i : r)
            s += i;
        return s;
    }
}