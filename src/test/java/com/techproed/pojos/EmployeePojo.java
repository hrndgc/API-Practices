package com.techproed.pojos;

public class EmployeePojo {

    private String status;
    private DataPojo data;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataPojo getData() {
        return data;
    }

    public void setData(DataPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EmployeePojo() {
    }

    public EmployeePojo(String status, DataPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    @Override
    public String toString() {
        return "EmployeePojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
