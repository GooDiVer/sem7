import io.jenetics.*;
import io.jenetics.engine.Codecs;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionStatistics;
import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.ConnectionFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

import static io.jenetics.engine.EvolutionResult.toBestPhenotype;
import static io.jenetics.engine.Limits.bySteadyFitness;
import static java.lang.Math.*;


public class TravelingSalesmanLab4 {

    private static final int NODES = 10;
    private static final double[][] ALPHA = matrix(NODES);
    private static final int iterations = 500;
    private static final int sizePerIteration = 30;

    private static double[][] matrix(int stops) {
        final double radius = 30.0;
        double[][] matrix = new double[stops][stops];

        for (int i = 0; i < stops; ++i) {
            for (int j = 0; j < stops; ++j) {
                matrix[i][j] = chord(stops, abs(i - j), radius);
            }
        }
        return matrix;
    }

    private static double chord(int stops, int i, double r) {
        return 2.0 * r * abs(sin(PI * i / stops));
    }

    private static double dist(final int[] path) {
        return IntStream.range(0, NODES)
                .mapToDouble(i -> ALPHA[path[i]][path[(i + 1) % NODES]])
                .sum();
    }

    public static void main(String[] args) {

        makeNeuroph();

        final Engine<EnumGene<Integer>, Double> engine = Engine.builder(TravelingSalesmanLab4::dist, Codecs.ofPermutation(NODES))
                .optimize(Optimize.MINIMUM)
                .maximalPhenotypeAge(5)
                .populationSize(sizePerIteration)
                .alterers(new SwapMutator<>(0.1), new PartiallyMatchedCrossover<>(0.9))
                .build();

        final EvolutionStatistics<Double, ?> statistics = EvolutionStatistics.ofNumber();

        final Phenotype<EnumGene<Integer>, Double> best = engine.stream()
                .limit(bySteadyFitness(iterations))
                .limit(500)
                .peek(statistics)
                .collect(toBestPhenotype());

        System.out.println(best.toString().toCharArray());
    }

    private static void makeNeuroph() {
        DataSetRow rOne = new DataSetRow(new double[]{4, 5, 6, 7, 8, 9, 0, 1, 2, 3}, new double[]{185});

        DataSet ds = new DataSet(10, 1);

        ds.add(rOne);

        NeuralNetwork ann = new NeuralNetwork();

        Layer inputLayer = new Layer();
        Layer outputLayer = new Layer();
        Layer hiddenLayer = new Layer();

        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());
        inputLayer.addNeuron(new Neuron());

        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());
        hiddenLayer.addNeuron(new Neuron());

        outputLayer.addNeuron(new Neuron());

        ann.addLayer(0, inputLayer);
        ann.addLayer(1, hiddenLayer);
        ann.addLayer(2, outputLayer);

        ConnectionFactory.fullConnect(ann.getLayerAt(0), ann.getLayerAt(1));
        ConnectionFactory.fullConnect(ann.getLayerAt(1), ann.getLayerAt(2));
        ConnectionFactory.fullConnect(ann.getLayerAt(0), ann.getLayerAt(ann.getLayersCount() - 1), false);

        ann.setInputNeurons(inputLayer.getNeurons());
        ann.setOutputNeurons(ann.getLayerAt(ann.getLayersCount() - 1).getNeurons());

        BackPropagation backPropagation = new BackPropagation();
        backPropagation.setMaxIterations(500);

        ann.setLearningRule(backPropagation);
        ann.setInput(1, 0, 9, 8, 7, 6, 5, 4, 3, 2);
        ann.learn(ds);
        ann.calculate();

        System.out.println(Arrays.stream(ann.getOutput()).sum());
        System.out.println();
    }

}