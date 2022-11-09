package com.lschaan.zeromq.request;

public class MultiplyRequest {

    private Double firstNumber = 0D;
    private Double secondNumber = 0D;

    public MultiplyRequest() {
    }

    public MultiplyRequest(Double firstNumber, Double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public Double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Double secondNumber) {
        this.secondNumber = secondNumber;
    }
}
