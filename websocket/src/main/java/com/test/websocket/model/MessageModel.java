package com.test.websocket.model;

public class MessageModel {

    private String message;
    private String fromLogin;

    public String getFromLogin() {
        return fromLogin;
    }

    public String getMessage() {
        return message;
    }

    public void setFromLogin(String fromLogin) {
        this.fromLogin = fromLogin;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        
        return "MessageModel{"+
               "message=' "+message + '\''+
               ", fromLogin=' " + fromLogin +'\'' +
               "}"; 

    }
}
