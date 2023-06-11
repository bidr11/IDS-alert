package org;

import java.math.BigInteger;

public class TestEmit {
    public static byte[] stringToBytes32(String string) {
        byte[] byteValue = string.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
//        return new Bytes32(byteValueLen32);
        return byteValueLen32;
    }
    public static void main(String[] args) {
        AlertSender sender = new AlertSender();
        String fqmn = "Nouveau";
        byte[] callstack = stringToBytes32("oieriwueoirwer");
        BigInteger[] parameterIndexes = {BigInteger.valueOf(1231231), BigInteger.valueOf(9934)};
        BigInteger[] features = {BigInteger.valueOf(1234), BigInteger.valueOf(222)};
        String[] paths = {"asdasdasdasdasd", "ehtlsiejrse"};
        String[] chars = {"sdsdsdsdsds", "akuwhdkahsd"};
        BigInteger timestamp = BigInteger.valueOf(1234);
        String description = "This is a test done to test";
        sender.createAlert(fqmn, callstack, parameterIndexes, features, paths, chars, timestamp, description);
    }
}
