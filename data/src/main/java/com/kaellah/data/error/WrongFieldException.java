package com.kaellah.data.error;


public class WrongFieldException extends Exception {

    private final static Object EMPTY = new Object();

    private final Object tag;
    private final int messageRes;

    public WrongFieldException(int messageRes) {
        this.tag = EMPTY;
        this.messageRes = messageRes;
    }

    public WrongFieldException(Object tag, int messageRes) {
        this.tag = tag;
        this.messageRes = messageRes;
    }

    public int getMessageRes() {
        return messageRes;
    }

    public Object getTag() {
        return tag;
    }
}
