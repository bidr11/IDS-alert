package org;

public class TestSubscribe {
    public static void main(String[] args) {
        AlertSubscriber subscriber = new AlertSubscriber();
        try {
            subscriber.subscribeToEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
