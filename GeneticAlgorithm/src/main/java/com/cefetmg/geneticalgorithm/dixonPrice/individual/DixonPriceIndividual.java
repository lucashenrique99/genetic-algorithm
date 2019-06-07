package com.cefetmg.geneticalgorithm.dixonPrice.individual;

import com.cefetmg.geneticalgorithm.commom.individual.Individual;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DixonPriceIndividual implements Individual {

    private List<Double> dimensions;

    public DixonPriceIndividual(List<Double> dimensions) {
        this.dimensions = dimensions;
    }

    public DixonPriceIndividual(int dimensionsNumber) {
        this.dimensions = new ArrayList<>(dimensionsNumber);
        Random r = new Random();

        for (int i = 0; i < dimensionsNumber; i++) {
            this.dimensions.add(new Double(r.nextInt(11) * ((r.nextInt(2) == 0) ? 1 : -1)));
        }
    }

    @Override
    public List<Individual> recombine(Individual individual) {
        List<Double> childOne = new ArrayList<>(this.dimensions.size());
        List<Double> childTwo = new ArrayList<>(this.dimensions.size());

        double alpha = 0.33;
        for (int i = 0; i < this.dimensions.size(); i++) {
            Double x1 =  (alpha * this.dimensions.get(i) + (1 - alpha) * individual.getGenes().get(i));
            Double x2 =  (alpha * individual.getGenes().get(i) + (1 - alpha) * this.dimensions.get(i));

            childOne.add(x1);
            childTwo.add(x2);
        }

        return Arrays.asList(new DixonPriceIndividual(childOne), new DixonPriceIndividual(childTwo));
    }

    @Override
    public Individual mutate() {
        final Random r = new Random();
        double mutationRate = 0.1;
        List<Double> newValues = this.dimensions
                .stream()
                .map(i -> (r.nextDouble() >= mutationRate) ? i : new Double((int) (i + r.nextGaussian())))
                .collect(Collectors.toList());

        for (int i = 0; i < newValues.size(); i++) {
            Double get = newValues.get(i);
            if (get.equals(this.dimensions.get(i))) {
                double aux = r.nextGaussian();
                newValues.set(i, (aux == 0) ? get + 1 : ((int) (get + aux)));
                break;
            }
        }

        return new DixonPriceIndividual(newValues);
    }

    @Override
    public Double getEvaluation() {
        double total = Math.pow((this.dimensions.get(0) - 1), 2);
        for (int i = 1; i < this.dimensions.size(); i++) {
            total += (i + 1) * Math.pow(2 * Math.pow(this.dimensions.get(i), 2) - this.dimensions.get(i - 1), 2);
        }
        return total;
    }

    @Override
    public List<Double> getGenes() {
        return this.dimensions;
    }

    @Override
    public String toString() {
        return "f(" + String.join(", ", this.dimensions.stream().map(i -> i.toString()).collect(Collectors.toList())) + ") = " + this.getEvaluation();
    }

}
