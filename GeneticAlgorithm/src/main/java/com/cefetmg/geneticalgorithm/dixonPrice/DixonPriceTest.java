package com.cefetmg.geneticalgorithm.dixonPrice;

import com.cefetmg.geneticalgorithm.commom.FGAGeneticAlgorithm;
import com.cefetmg.geneticalgorithm.commom.individual.Individual;

public class DixonPriceTest {
    
    
    public static void main(String[] args) {
        
        FGAGeneticAlgorithm fga = new FGAGeneticAlgorithm();
        Individual i = fga.process(20, 4, 500, new DixonPriceIndividualFactory(2), Boolean.FALSE);
        
        System.out.println(i);
        
    }
    
}
