package org;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.StaticGasProvider;
import java.math.BigInteger;

public class AlertSender {
    private static final String CONTRACT_ADDRESS = "0x66752e648a8E96eD9cFE5CFB8BF4646Bb4E8317C";

    private static final BigInteger GAS_LIMIT = BigInteger.valueOf(500000);
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L); // 20 Gwei

    private final Web3j web3j;
    private final Credentials creds;

    public AlertSender() {
        String networkLink = "http://192.168.109.131:8545";
        Web3j web3 = Web3j.build(new HttpService(networkLink));

        String senderPrivKey = "d5ade9e961f2873084f8ee7eaaf91f729b15abcd7249041ed964a279f4c3b405";
        Credentials creds = Credentials.create(senderPrivKey);

        this.web3j = web3;
        this.creds = creds;
    }

    public void createAlert(String fqmn, byte[] callstack, BigInteger[] parameterIndexes,
                            BigInteger[] features, String[] paths, String[] chars, BigInteger timestamp, String description) {
        Suspect[] suspects = new Suspect[parameterIndexes.length];
        for (int i = 0; i < parameterIndexes.length; i++) {
            suspects[i] = new Suspect(
                    parameterIndexes[i],
                    features[i],
                    paths[i],
                    chars[i]
            );
        }

        Signature signature = new Signature(
                fqmn,
                callstack,
                suspects
        );

        Alert alert = new Alert(
                signature,
                timestamp,
                description
        );

        sendAlert(alert);
    }

    private void sendAlert(Alert alert) {
        try {
            AlertProcessor contract = AlertProcessor.load(CONTRACT_ADDRESS, web3j, creds, new StaticGasProvider(GAS_PRICE, GAS_LIMIT));
            Signature signature = alert.getSignature();
//            Suspect[] suspects = signature.getSuspects();

            TransactionReceipt transactionReceipt = contract.processAlert(
                    signature.getFqmn(),
                    signature.getCallstack(),
                    signature.getSuspectsParameterIndexes(),
                    signature.getSuspectsFeatures(),
                    signature.getSuspectsPaths(),
                    signature.getSuspectsChars(),
                    alert.getTimestamp(),
                    alert.getDescription()
            ).send();

            System.out.println("L'alerte est envoyée! Hash de la transaction: " + transactionReceipt.getTransactionHash());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}