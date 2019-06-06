package com.cefetmg.geneticalgorithm.commom.individual;

import java.util.List;

public interface Individual {
    
    public List<Individual> recombine(Individual individual);
    
    public Individual mutate();
    
    public Double getEvaluation();
    
    public List<Integer> getGenes();
    
}
