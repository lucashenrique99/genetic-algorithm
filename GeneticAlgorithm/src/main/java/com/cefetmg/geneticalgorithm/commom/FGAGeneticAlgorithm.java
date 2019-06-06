package com.cefetmg.geneticalgorithm.commom;

import com.cefetmg.geneticalgorithm.commom.individual.Individual;
import com.cefetmg.geneticalgorithm.commom.individual.IndividualFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FGAGeneticAlgorithm implements GeneticAlgorithm {

    @Override
    public Individual process(int numIndividual, int numElitism, int numGenerations, IndividualFactory factory, Boolean isMaximization) {

        List<Individual> initialPopulation = factory.getIndividuals(numIndividual);

        List<Individual> generationResult = new ArrayList<>(numIndividual * 3);
        for (int i = 0; i < numGenerations; i++) {
            generationResult.clear();
            generationResult.addAll(initialPopulation);

            // mutates
            generationResult.addAll(
                    initialPopulation
                            .stream()
                            .map((individual) -> individual.mutate())
                            .collect(Collectors.toList()));

            List<Individual> fathers = new ArrayList(initialPopulation);
            Random r = new Random();
            for (int j = 0; j < numIndividual / 2; j++) {
                Individual father1 = fathers.remove(r.nextInt(fathers.size()));
                Individual father2 = fathers.remove(r.nextInt(fathers.size()));

                generationResult.addAll(father1.recombine(father2));
            }

            initialPopulation = generateNextGeneration(numElitism, numIndividual, generationResult, isMaximization);

            System.out.println("Generation: " + i + "\t Evaluation: " + initialPopulation.get(0).getEvaluation() + "\t " + initialPopulation.get(0));
        }

        return initialPopulation.stream().findFirst().get();
    }

    private List<Individual> generateNextGeneration(int numElitism, int numIndividual, List<Individual> candidates, final Boolean isMaximization) {

        List<Individual> selections = candidates.stream()
                .sorted((i1, i2)
                        -> (isMaximization)
                        ? i2.getEvaluation().compareTo(i1.getEvaluation())
                        : i1.getEvaluation().compareTo(i2.getEvaluation()))
                .collect(Collectors.toList())
                .subList(0, numIndividual - numElitism);

        candidates.removeAll(selections);

        // elitism below
        List<Double> evaluationFunction = candidates.stream().map(i -> (isMaximization) ? i.getEvaluation() : 1 / i.getEvaluation()).collect(Collectors.toList());
        
        double total = evaluationFunction.stream().reduce( (current, accumulator ) -> current + accumulator).get();
        Random r = new Random();
        for (int i = 0; i < numElitism; i++) {
            double limit = r.nextDouble()* total;
            double accumulator = 0;

            int j = 0;
            while (accumulator < limit && j < evaluationFunction.size() -1) {
                accumulator += evaluationFunction.get(j);
                j++;
            }

            selections.add(candidates.get(j));
        }

        return selections;
    }

}
