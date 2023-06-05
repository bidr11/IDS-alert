package org;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Signature {
    String fqmn;
    byte[] callstack;
    Suspect[] suspects;
    public Signature(String fqmn, byte[] callstack, Suspect[] suspects) {
        this.fqmn = fqmn;
        this.callstack = callstack;
        this.suspects = suspects;
    }

    public String getFqmn() {
        return this.fqmn;
    }

    public byte[] getCallstack() {
        return this.callstack;
    }

    public Suspect[] getSuspects() {
        return this.suspects;
    }

    public List<BigInteger> getSuspectsParameterIndexes() {
        List<BigInteger> output = new ArrayList<>() ;
        for (Suspect suspect : suspects) {
            output.add(suspect.getParameterIndex());
        }
        return output;
    }

    public List<BigInteger> getSuspectsFeatures() {
        List<BigInteger> output = new ArrayList<>() ;
        for (Suspect suspect : suspects) {
            output.add(suspect.getFeature());
        }
        return output;
    }

    public List<String> getSuspectsPaths() {
        List<String> output = new ArrayList<>();
        for (Suspect suspect : suspects) {
            output.add(suspect.getPath());
        }
        return output;
    }

    public List<String> getSuspectsChars() {
        List<String> output = new ArrayList<>();
        for (Suspect suspect : suspects) {
            output.add(suspect.getChars());
        }
        return output;
    }
}
