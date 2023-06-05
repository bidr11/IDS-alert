package org;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

public class AlertSubscriber {
    public void subscribeToEvents() throws Exception {
        String CONTRACT_ADDRESS = "0x66752e648a8E96eD9cFE5CFB8BF4646Bb4E8317C";

        String networkLink = "http://192.168.109.131:8545";
        Web3j web3j = Web3j.build(new HttpService(networkLink));

        // create filter for contract events
        EthFilter filter = new EthFilter(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST, CONTRACT_ADDRESS).addSingleTopic("0x2df5a1b557bcfa7dc126ad7e27a5ae2b5731a8019dab5f0ad8d616b185cb93e4");

        // subscribe to events
        web3j.ethLogFlowable(filter).subscribe(event -> {
            System.out.println("Event received");

            List<Type> args = FunctionReturnDecoder.decode(
                    event.getData(), AlertProcessor.NEWALERT_EVENT.getParameters());

            String fqmn = (String)args.get(0).getValue();
            BigInteger timestamp = (BigInteger)args.get(1).getValue();
            List<Suspect> suspects = (List<Suspect>)args.get(2).getValue();
            String description = (String)args.get(3).getValue();

            System.out.println("fqmn: " + fqmn);
            System.out.println("timestamp: " + timestamp);
            System.out.println("description: " + description);

            System.out.println("Suspects:");
            for (int i = 0; i < suspects.size(); i++) {
                Suspect suspect = suspects.get(i);
                System.out.println("\tparameterIndex: " + suspect.getParameterIndex());
                System.out.println("\tfeature: " + suspect.getFeature());
                System.out.println("\tpath: " + suspect.getPath());
                System.out.println("\tchars: " + suspect.getChars() + "\n");
            }
        }, error -> {
            System.out.println("Error: " + error);
        });
    }

    public static void main(String[] args) {
        AlertSubscriber wow = new AlertSubscriber();
        try {
            wow.subscribeToEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
