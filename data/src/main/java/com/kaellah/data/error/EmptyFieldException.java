package com.kaellah.data.error;


import java.util.List;

public class EmptyFieldException extends Exception {

    private List<Object> tags;

    public EmptyFieldException() {
    }

    public EmptyFieldException(List<Object> tags) {
        this.tags = tags;
    }

    public List<Object> getTags() {
        return tags;
    }
}
