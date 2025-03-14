package com.system.notification.model.DTO;

public class ResponseObject {
    private Boolean status;
    private String jsonData;
    private String errorMessage;
    private Object data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "status=" + status +
                ", jsonData='" + jsonData + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", data=" + data +
                '}';
    }
}
