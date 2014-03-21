import checkers.nullness.quals.*;


public class TestCheckerCompiler {
	void sample() {
        @NonNull Object ref = null;
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
			System.out.println("Hello world");
	}

}
