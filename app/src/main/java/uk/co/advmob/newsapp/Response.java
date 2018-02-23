package uk.co.advmob.newsapp;

/**
 * Created by Kostas on 23/02/2018.
 */

public class Response {
    private boolean error;
    private String errorMessage;
    private Object dataObject;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getDataObject() {
        return dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }
}
