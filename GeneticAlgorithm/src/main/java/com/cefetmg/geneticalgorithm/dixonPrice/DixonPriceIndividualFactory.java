package com.cefetmg.geneticalgorithm.dixonPrice;

import com.cefetmg.geneticalgorithm.commom.individual.Individual;
import com.cefetmg.geneticalgorithm.commom.individual.IndividualFactory;
import com.cefetmg.geneticalgorithm.dixonPrice.individual.DixonPriceIndividual;
import java.util.ArrayList;
import java.util.List;

public class DixonPriceIndividualFactory implements IndividualFactory{

    private final Integer dimensionsNumber;

    public DixonPriceIndividualFactory(Integer dimensionsNumber) {
        this.dimensionsNumber = dimensionsNumber;
    }
    
    @Override
    public Individual getIndividual() {
        return new DixonPriceIndividual(this.dimensionsNumber);
    }

    @Override
    public List<Individual> getIndividuals(int num) {
        List<Individual> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(this.getIndividual());
        }
        return list;
    }
    
}
