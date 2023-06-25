package org.example;

public class Responder {
    private long chatId;
    private String phone;
    private int supportStatus;
    public  Integer amountActivity;
    public static final int SERVICE_UNKNOWN = 0;
    public static final int SERVICE_WEATHER = 1;
    public static final int SERVICE_FIXER_IO_API = 2;
    public static final int SERVICE_JOKE_API = 3;
    public static final int SERVICE_NEWS_API = 4;
    public static final int SERVICE_COVID_19_DATA_API = 5;

    public Responder(long chatId) {
        this.chatId = chatId;
        this.supportStatus = SERVICE_UNKNOWN;
        this.amountActivity=0;

    }
    public void updateAmountActivity(){
        this.amountActivity++;
    }

    public void updateSupportStatus(char choice) {
        switch (choice) {
            case ('W') -> this.supportStatus = SERVICE_WEATHER;
            case ('F') -> this.supportStatus = SERVICE_FIXER_IO_API;
            case ('J') -> this.supportStatus = SERVICE_JOKE_API;
            case ('N') -> this.supportStatus = SERVICE_NEWS_API;
            case ('C') -> this.supportStatus = SERVICE_COVID_19_DATA_API;
        }
    }

    public  Integer getAmountActivity() {
        return this.amountActivity;
    }
}
