package bello.andrea.calcolatrice.operations;

public abstract class Operation {

    private Integer mOperator1;
    private Integer mOperator2;

    public Integer getOperator1() {
        return mOperator1;
    }

    public void setOperator1(Integer operator1) {
        this.mOperator1 = operator1;
    }

    public Integer getOperator2() {
        return mOperator2;
    }

    public void setOperator2(Integer operator2) {
        this.mOperator2 = operator2;
    }

    public abstract Integer calculate();
}
