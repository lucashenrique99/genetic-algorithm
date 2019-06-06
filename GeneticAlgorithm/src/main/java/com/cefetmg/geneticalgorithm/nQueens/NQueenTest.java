package com.cefetmg.geneticalgorithm.nQueens;

import com.cefetmg.geneticalgorithm.commom.FGAGeneticAlgorithm;
import com.cefetmg.geneticalgorithm.commom.individual.Individual;

public class NQueenTest {

    public static void main(String[] args) {
        FGAGeneticAlgorithm fga = new FGAGeneticAlgorithm();
        Individual best = fga.process(40, 8, 2000, new NQueenIndividualFactory(8), Boolean.FALSE);
        System.out.println("Melhor Resultado: " + best.getEvaluation() + " , " + best);

    }

}
