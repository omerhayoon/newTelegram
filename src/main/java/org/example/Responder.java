package org.example;

public class Responder {

    private long chatId;
    public  Integer amountActivity;
    private String name;

    public Responder(long chatId,String name) {
        this.chatId = chatId;
        this.name=name;
        this.amountActivity=0;
    }
    public void updateAmountActivity(){
        this.amountActivity++;
    }

    public  Integer getAmountActivity() {
        return this.amountActivity;
    }
    public String getName(){
        return this.name;
    }
    public long getChatId() {
        return this.chatId;
    }
}
