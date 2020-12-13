import io.jenetics.*;
import io.jenetics.engine.Codecs;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionStatistics;
import java.util.stream.IntStream;
import static io.jenetics.engine.EvolutionResult.toBestPhenotype;
import static io.jenetics.engine.Limits.bySteadyFitness;
import static java.lang.Math.*;


public class TravelingSalesman {

    private static final int NODES = 30;
    private static final double[][] ALPHA = matrix(NODES);
    private static final int iterations = 30;
    private static final int sizePerIteration = 500;

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
        final Engine<EnumGene<Integer>, Double> engine = Engine.builder(TravelingSalesman::dist, Codecs.ofPermutation(NODES))
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

}