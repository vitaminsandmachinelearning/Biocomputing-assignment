
package aproject;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Aproject {
    int datacount;
    int dataset;
    int poolsize;
    int rulecount;
    int rulelength;
    int generations;
    float mutationrate;
    Random random = new Random();
    
    public Aproject(int _datacount, int _poolsize, int _rulecount, int _rulelength, int _generations, float _mutationrate, int _dataset)
    {
        datacount = _datacount;
        poolsize = _poolsize;
        rulecount = _rulecount;
        rulelength = _rulelength;
        generations = _generations;
        mutationrate = _mutationrate;
        dataset = _dataset;
    }
    
    public Rule run(int[] data) throws IOException {
        //make rules
        Rule[] rules = new Rule[poolsize];
        Rule[] offspring = new Rule[poolsize];
        for(int i = 0; i < poolsize; i++)
            rules[i] = new Rule(rulecount, rulelength, random);
        //GA
        for(int gen = 0; gen < generations; gen++)
        {
            for(int i = 0; i < poolsize; i++)
                rules[i].calculate(data, rulelength, rulecount);
            //selection
            //select offspring with tournament
            for(int i = 0; i < poolsize; i++)
            {
                int a = random.nextInt(poolsize);
                int b = random.nextInt(poolsize);
                offspring[i] = rules[a].fitness > rules[b].fitness ? new Rule(Arrays.copyOf(rules[a].r, rulelength * rulecount)) : new Rule(Arrays.copyOf(rules[b].r, rulelength * rulecount));
            }
            //crossover
            int t;
            for(int i = 0; i < poolsize; i+=2)
            {
                int c = random.nextInt(rulelength * rulecount - 1) + 1;
                for(int j = c; j < rulelength * rulecount; j++)
                {
                    t = rules[i].r[j];
                    rules[i].r[j] = rules[i+1].r[j];
                    rules[i+1].r[j] = t;
                }
            }
            //mutation
            for(Rule r : rules)
            {
                for(int i = 0; i < rulelength; i++)
                {
                    if(random.nextFloat() < mutationrate)
                        switch(r.r[i]){
                            case 0: r.r[i] += 1 + random.nextInt(2); break;
                            case 1: r.r[i] = random.nextInt(2) == 1 ? r.r[i] - 1 : r.r[i] + 1; break;
                            case 2: r.r[i] -= 1 + random.nextInt(2); break;
                        }
                }
                for(int i = 0; i < rulelength; i++)
                    if(r.r[i * rulelength + (rulelength - 1)] == 2)
                        r.r[i * rulelength + (rulelength - 1)] = random.nextInt(2);
            }
            rules = Arrays.copyOf(offspring, poolsize);
        }
        for(int i = 0; i < poolsize; i++)
                rules[i].calculate(data, rulelength, rulecount);
        int bi = 0;
        int bt = 0;
        for(int i = 0; i < poolsize; i++)
        {
            if(rules[i].fitness > bt)
            {
                bt = rules[i].fitness;
                bi = i;
            }
        }
        return rules[bi];
    } 
}
