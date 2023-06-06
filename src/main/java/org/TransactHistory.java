package org;

import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class TransactHistory {

    public static List<Type> decodeInputData(String inputData,
                                             String methodName,
                                             List<TypeReference<?>> outputParameters) {
        Function function = new Function(methodName,
                Collections.<Type>emptyList(),
                outputParameters
        );
        List<Type> result = FunctionReturnDecoder.decode(
                inputData.substring(10),
                function.getOutputParameters());
        return result;
    }
    public static void main(String[] args) throws IOException {
        String networkLink = "http://192.168.109.131:8545";
        String CONTRACT_ADDRESS = "0x66752e648a8E96eD9cFE5CFB8BF4646Bb4E8317C".toLowerCase();
        Web3j web3 = Web3j.build(new HttpService(networkLink));
        EthBlock.Block block = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
        BigInteger latestBlockNumber = block.getNumber();
        List<EthBlock.TransactionResult> allTransactions = new ArrayList<>();

        int totalTransactions = 0;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(latestBlockNumber) <= 0; i = i.add(BigInteger.ONE)) {
            EthBlock etherBlock = web3.ethGetBlockByNumber(DefaultBlockParameter.valueOf(i), true).send();
            EthBlock.Block currentBlock = etherBlock.getBlock();
            List<EthBlock.TransactionResult> transactions = currentBlock.getTransactions();
            allTransactions.addAll(transactions);
            totalTransactions += transactions.size();
        }
        System.out.println("Nombre total de blocks: " + latestBlockNumber+1);
        System.out.println("Nombre total de transactions: " + totalTransactions);

        for (EthBlock.TransactionResult transaction : allTransactions) {
            EthBlock.TransactionObject transactionObject = (EthBlock.TransactionObject) transaction.get();
            String contractAddress = transactionObject.getTo();
            if (contractAddress != null && contractAddress.toLowerCase().equals(CONTRACT_ADDRESS)) {
                String data = transactionObject.getInput();
                System.out.println("De: " + transactionObject.getFrom());
                System.out.println("Données: " + data);
                System.out.println();

//                List<Type> result = decodeInputData(data, "processAlert",
//                        Arrays.asList(new TypeReference<Utf8String>() {},
//                                new TypeReference<Bytes32>() {},
//                                new TypeReference<DynamicArray<Uint256>>() {},
//                                new TypeReference<DynamicArray<Uint256>>() {},
//                                new TypeReference<DynamicArray<Utf8String>>() {},
//                                new TypeReference<DynamicArray<Utf8String>>() {},
//                                new TypeReference<Uint256>() {},
//                                new TypeReference<Utf8String>() {}
//                        )
//                );
//
//                for (Type thing: result) {
//                    if (thing.getClass().equals(new Uint256(1).getClass()))
//                        System.out.println((BigInteger) thing);
//                    if (thing.getClass().equals(new Utf8String("test").getClass()))
//                        System.out.println(thing);
//
//
//                }
        }

        List<EthBlock.TransactionResult> txs = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock().getTransactions();
        if (txs.size() > 0) {
            EthBlock.TransactionObject lastTransaction = (EthBlock.TransactionObject) txs.get(txs.size() - 1).get();
            System.out.println("Dernière transaction: ");
            System.out.println("De:" + lastTransaction.getFrom());
            System.out.println("A:" + lastTransaction.getTo());
            System.out.println("Nonce:" + lastTransaction.getNonce());
            System.out.println(lastTransaction.get());
            System.out.println("Données:" + lastTransaction.getInput());
        }
    }
}}