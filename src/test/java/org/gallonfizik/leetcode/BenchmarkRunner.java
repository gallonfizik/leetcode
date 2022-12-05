package org.gallonfizik.leetcode;

import java.util.DoubleSummaryStatistics;


public class BenchmarkRunner {
    int batches;
    int batchSize;

    public BenchmarkRunner(int batches, int batchSize) {
        this.batches = batches;
        this.batchSize = batchSize;
    }

    public void benchmark(Runnable subject) {
        DoubleStatistics statistics = new DoubleStatistics();
        warmup(subject);

        for (int batch = 0; batch < batches; batch++) {
            long tic = System.nanoTime();
            for (int i = 0; i < batchSize; i++) {
                subject.run();
            }
            double throughput = 1.0e6 * batchSize / (System.nanoTime() - tic);
            statistics.accept(throughput);
        }

        System.out.println(statistics);
        System.out.printf("Average throughput: %.1f +- %.1f KOps/s.%n", statistics.getAverage(), statistics.getStandardDeviation());
    }

    private void warmup(Runnable subject) {
        for (int i = 0; i < 0.05 * batchSize * batches; i++) {
            subject.run();
        }
    }
}

class DoubleStatistics extends DoubleSummaryStatistics {

    private double sumOfSquare = 0.0d;
    private double sumOfSquareCompensation; // Low order bits of sum
    private double simpleSumOfSquare; // Used to compute right sum for non-finite inputs

    @Override
    public void accept(double value) {
        super.accept(value);
        double squareValue = value * value;
        simpleSumOfSquare += squareValue;
        sumOfSquareWithCompensation(squareValue);
    }

    private void sumOfSquareWithCompensation(double value) {
        double tmp = value - sumOfSquareCompensation;
        double level = sumOfSquare + tmp;
        sumOfSquareCompensation = (level - sumOfSquare) - tmp;
        sumOfSquare = level;
    }

    public double getSumOfSquare() {
        double tmp = sumOfSquare + sumOfSquareCompensation;
        if (Double.isNaN(tmp) && Double.isInfinite(simpleSumOfSquare)) {
            return simpleSumOfSquare;
        }
        return tmp;
    }

    public final double getStandardDeviation() {
        long count = getCount();
        double sumOfSquare = getSumOfSquare();
        double average = getAverage();
        return count > 0 ? Math.sqrt((sumOfSquare - count * Math.pow(average, 2)) / (count - 1)) : 0.0d;
    }
}
