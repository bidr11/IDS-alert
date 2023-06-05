package org;

import java.math.BigInteger;

public class Alert {
    Signature signature;
    BigInteger timestamp;
    String description;

    public Alert(Signature signature, BigInteger timestamp, String description) {
        this.signature = signature;
        this.timestamp = timestamp;
        this.description = description;
    }

    public Signature getSignature() {
        return signature;
    }

    public BigInteger getTimestamp() {
        return this.timestamp;
    }

    public String getDescription() {
        return this.description;
    }
}
