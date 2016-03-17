package bello.andrea.calcolatrice.operations;

public class Subtraction extends Operation{

    @Override
    public Integer calculate() {
        return getOperator1() - getOperator2();
    }
}
