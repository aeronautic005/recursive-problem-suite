/**
 * Encapsulates performance metrics, execution timing, and call-depth data
 * for recursive algorithm benchmarks.
 */
public class BenchmarkResult {
    private final String operationName;
    private final String inputDescription;
    private final long elapsedNanos;
    private final int callDepthOrSteps;
    private final String outputSummary;

    public BenchmarkResult(String operationName, String inputDescription,
                           long elapsedNanos, int callDepthOrSteps, String outputSummary) {
        this.operationName = operationName;
        this.inputDescription = inputDescription;
        this.elapsedNanos = elapsedNanos;
        this.callDepthOrSteps = callDepthOrSteps;
        this.outputSummary = outputSummary;
    }

    public String getOperationName() {
        return operationName;
    }

    public String getInputDescription() {
        return inputDescription;
    }

    public long getElapsedNanos() {
        return elapsedNanos;
    }

    public double getElapsedMicros() {
        return elapsedNanos / 1_000.0;
    }

    public double getElapsedMillis() {
        return elapsedNanos / 1_000_000.0;
    }

    public int getCallDepthOrSteps() {
        return callDepthOrSteps;
    }

    public String getOutputSummary() {
        return outputSummary;
    }

    public String formatRow() {
        return String.format("| %-22s | %-16s | %10.3f us | %10d | %-15s |",
                operationName, inputDescription, getElapsedMicros(), callDepthOrSteps, outputSummary);
    }

    @Override
    public String toString() {
        return String.format("[%s(%s)] -> %s (Time: %.3f us, Steps: %d)",
                operationName, inputDescription, outputSummary, getElapsedMicros(), callDepthOrSteps);
    }
}
