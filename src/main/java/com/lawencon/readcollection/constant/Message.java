package com.lawencon.readcollection.constant;

public enum Message {
    SUCCESS_SAVE("Data success to saved"),FAILED_SAVE("Data failed to save"),
    SUCCESS_UPDATE("Data success to updaated"),FAILED_UPDATE("Data failed to update");

    private String message;

    private Message(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
