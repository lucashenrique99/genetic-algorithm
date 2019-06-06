package com.cefetmg.geneticalgorithm.nQueens.individual;

import com.cefetmg.geneticalgorithm.commom.individual.Individual;
import com.cefetmg.geneticalgorithm.commom.individual.IndividualFactory;
import java.util.ArrayList;
import java.util.List;

public class NQueenIndividualFactory implements IndividualFactory{

    private final Integer numGenes;

    public NQueenIndividualFactory(Integer numGenes) {
        this.numGenes = numGenes;
    }    
    
    @Override
    public Individual getIndividual() {
        return new NQueenIndividual(this.numGenes);
    }

    @Override
    public List<Individual> getIndividuals(int num) {
        List<Individual> individuals = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            individuals.add(getIndividual());
        }
        
        return individuals;
    }
    
}
