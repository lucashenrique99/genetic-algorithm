package com.cefetmg.geneticalgorithm.commom.individual
        ;

import java.util.List;

public interface IndividualFactory {
    
    public Individual getIndividual();
    
    public List<Individual> getIndividuals(int num);
    
}
