package com.cefetmg.geneticalgorithm.commom;

import com.cefetmg.geneticalgorithm.commom.individual.Individual;
import com.cefetmg.geneticalgorithm.commom.individual.IndividualFactory;

public interface GeneticAlgorithm {
    
    public Individual process(int numIndividual, int numElitism, int numGenerations, IndividualFactory factory, Boolean isMaximization);
    
}
