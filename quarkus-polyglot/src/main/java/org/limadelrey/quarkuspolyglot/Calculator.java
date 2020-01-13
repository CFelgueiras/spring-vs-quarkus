package org.limadelrey.quarkuspolyglot;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.File;
import java.io.IOException;

public class Calculator {

    public static final String JS = "js";
    public static final String CALCULATOR_FILENAME = "calculator.js";
    public static final String CALCULATOR_FUNCTION_NAME = "fibonacci";

    public void calc() {
        try (Context context = Context.create(JS)) {
            final File calculatorJS = new File(getClass().getClassLoader().getResource(CALCULATOR_FILENAME).getFile());

            context.eval(Source.newBuilder(JS, calculatorJS).build());

            final Value fibonacciFunction = context.getBindings(JS).getMember(CALCULATOR_FUNCTION_NAME);
            final Integer fibonacciResult = fibonacciFunction.execute(5).asInt();

            System.out.println(fibonacciResult);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
