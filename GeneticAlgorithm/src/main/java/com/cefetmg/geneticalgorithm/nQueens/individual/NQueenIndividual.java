package com.cefetmg.geneticalgorithm.nQueens.individual;

import com.cefetmg.geneticalgorithm.commom.individual.Individual;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NQueenIndividual implements Individual {

    private final List<Integer> genes;

    public NQueenIndividual(int numGenes) {

        Random r = new Random();

        genes = new ArrayList<>(numGenes);
        for (int i = 0; i < numGenes; i++) {
            this.genes.add(r.nextInt(numGenes));
        }

    }

    public NQueenIndividual(List<Integer> genes) {
        this.genes = genes;
    }

    @Override
    public List<Individual> recombine(Individual individual) {

        int size = (int) (0.3 * this.getGenes().size()); // 60 % from dad

        Random r = new Random();
        int index = r.nextInt(this.genes.size());
        int numGenes = this.genes.size();
        List<Integer> childOne = new ArrayList<>();
        List<Integer> childTwo = new ArrayList<>();

        for (int i = 0; i < numGenes; i++) {
            boolean case1 = (index + size) >= numGenes;
            if ((case1 && (i >= index || i < ((index + size) % numGenes)))
                    || (!case1 && (i >= index && i < ((index + size) % numGenes)))) {
                childOne.add(this.genes.get(i));
                childTwo.add(individual.getGenes().get(i));
            } else {
                childOne.add(individual.getGenes().get(i));
                childTwo.add(this.genes.get(i));
            }
        }

//        int i = 0;
//        while( i < size){
//            childOne.add(this.genes.get(index));
//            childTwo.add(individual.getGenes().get(index));
//            index = (index + 1) % numGenes;
//            i++;
//        }
//        
//        while(childOne.size() < this.genes.size()){
//            childOne.add(individual.getGenes().get(index));
//            childTwo.add(this.genes.get(index));
//            index = (index + 1) % numGenes;
//            i++;
//        }
        return Arrays.asList(new NQueenIndividual(childOne), new NQueenIndividual(childTwo));
    }

    @Override
    public Individual mutate() {
        List<Integer> newGenes = new ArrayList<>(this.genes.size());

        Random r = new Random();
        for (int i = 0; i < this.genes.size(); i++) {
            double probability = r.nextDouble();
            if (probability < 0.8) {
                newGenes.add(this.genes.get(i));
            } else {
                newGenes.add(r.nextInt(this.genes.size()));
            }
        }

        for (int i = 0; i < newGenes.size(); i++) {
            Integer get = newGenes.get(i);
            if (get.equals(this.genes.get(i))) {
                Integer newValue = r.nextInt(this.genes.size());
                while (newValue.equals(get)) {
                    newValue = r.nextInt(this.genes.size());
                }
                newGenes.set(i, newValue);
                break;
            }
        }

        return new NQueenIndividual(newGenes);
    }

    @Override
    public Double getEvaluation() {
        double result = 0;
        for (int i = 0; i < genes.size(); i++) {
            Integer current = genes.get(i);

            for (int j = i + 1; j < genes.size(); j++) {
                Integer target = genes.get(j);

                if (current.equals(target) || target.equals(current + (j - i)) || target.equals(current - (j - i))) {
                    result++;
                }
            }
        }

        return result;
    }

    @Override
    public List<Integer> getGenes() {
        return this.genes;
    }

    @Override
    public String toString() {
        return "Genes{" + String.join(", ", this.genes.stream().map(i -> i.toString()).collect(Collectors.toList())) + '}';
    }

}
