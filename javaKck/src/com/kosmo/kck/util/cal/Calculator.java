package com.kosmo.kck.util.cal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.math.MathContext;

public class Calculator {
    private static final BigDecimal HUNDRED = new BigDecimal(100);
    private static final int SCALE = 10;

    private BigDecimal accumulator;

    public Calculator() {
        this.accumulator = BigDecimal.ZERO;
    }

    public void clear() {
        this.accumulator = BigDecimal.ZERO;
    }

    public BigDecimal toBigDecimal(String number) {
        return new BigDecimal(number);
    }

    private void add(BigDecimal operand) {
        accumulator = accumulator.add(operand).stripTrailingZeros();
    }

    public void add(String number) {
        add(toBigDecimal(number));
    }

    private void subtract(BigDecimal operand) {
        accumulator = accumulator.subtract(operand).stripTrailingZeros();
    }

    public void subtract(String number) {
        subtract(toBigDecimal(number));
    }

    private void multiply(BigDecimal operand) {
        accumulator = accumulator.multiply(operand).stripTrailingZeros();
    }

    public void multiply(String number) {
        multiply(toBigDecimal(number));
    }

    private void divide(BigDecimal operand) {
        accumulator = accumulator.divide(operand, MathContext.DECIMAL32).stripTrailingZeros();            
    }

    public void divide(String number) {
        divide(toBigDecimal(number));
    }

    private BigDecimal sqrt(BigDecimal operand) {
         return new BigDecimal(Math.sqrt(operand.doubleValue()));
    }

    public BigDecimal sqrt(String number) {
        return sqrt(toBigDecimal(number));
    }

    private BigDecimal percentage(BigDecimal operand) {
        return accumulator.multiply(operand).divide(HUNDRED, MathContext.DECIMAL32).stripTrailingZeros();
    }

    public BigDecimal percentage(String number) {
        return percentage(toBigDecimal(number));
    }

    private BigDecimal divisor(BigDecimal operand) {
        return BigDecimal.ONE.divide(operand, MathContext.DECIMAL32).stripTrailingZeros();
    }

    public BigDecimal divisor(String number) {
        return divisor(toBigDecimal(number));
    }

    public BigDecimal getAccumulator() { return accumulator; }
    public void setAccumulator(BigDecimal accumulator) { this.accumulator = accumulator; }
    public void setAccumulator(String number) { this.accumulator = toBigDecimal(number); }
}