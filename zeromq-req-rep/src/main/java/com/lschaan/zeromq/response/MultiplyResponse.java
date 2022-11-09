package com.lschaan.zeromq.response;

public class MultiplyResponse {

    private Double result;

    public MultiplyResponse() {
    }

    public MultiplyResponse(Double result) {
        this.result = result;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }
}
