package org;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Array;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class AlertProcessor extends Contract {

    public static final String BINARY = "608060405234801561001057600080fd5b50610652806100206000396000f3fe608060405234801561001057600080fd5b506004361061002b5760003560e01c80637bc3ebcb14610030575b600080fd5b61004361003e3660046103ca565b610045565b005b6000865167ffffffffffffffff81111561006157610061610204565b6040519080825280602002602001820160405280156100bd57816020015b6100aa6040518060800160405280600081526020016000815260200160608152602001606081525090565b81526020019060019003908161007f5790505b50905060005b87518110156101815760405180608001604052808983815181106100e9576100e96104d4565b60200260200101518152602001888381518110610108576101086104d4565b60200260200101518152602001878381518110610127576101276104d4565b60200260200101518152602001868381518110610146576101466104d4565b6020026020010151815250828281518110610163576101636104d4565b60200260200101819052508080610179906104ea565b9150506100c3565b5060408051606080820183528b825260208083018c905282840185815284519283018552838352908201879052818401869052825190519351929391927f2df5a1b557bcfa7dc126ad7e27a5ae2b5731a8019dab5f0ad8d616b185cb93e4926101ef92918991908990610557565b60405180910390a15050505050505050505050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff8111828210171561024357610243610204565b604052919050565b600082601f83011261025c57600080fd5b813567ffffffffffffffff81111561027657610276610204565b610289601f8201601f191660200161021a565b81815284602083860101111561029e57600080fd5b816020850160208301376000918101602001919091529392505050565b600067ffffffffffffffff8211156102d5576102d5610204565b5060051b60200190565b600082601f8301126102f057600080fd5b81356020610305610300836102bb565b61021a565b82815260059290921b8401810191818101908684111561032457600080fd5b8286015b8481101561033f5780358352918301918301610328565b509695505050505050565b600082601f83011261035b57600080fd5b8135602061036b610300836102bb565b82815260059290921b8401810191818101908684111561038a57600080fd5b8286015b8481101561033f57803567ffffffffffffffff8111156103ae5760008081fd5b6103bc8986838b010161024b565b84525091830191830161038e565b600080600080600080600080610100898b0312156103e757600080fd5b883567ffffffffffffffff808211156103ff57600080fd5b61040b8c838d0161024b565b995060208b0135985060408b013591508082111561042857600080fd5b6104348c838d016102df565b975060608b013591508082111561044a57600080fd5b6104568c838d016102df565b965060808b013591508082111561046c57600080fd5b6104788c838d0161034a565b955060a08b013591508082111561048e57600080fd5b61049a8c838d0161034a565b945060c08b0135935060e08b01359150808211156104b757600080fd5b506104c48b828c0161024b565b9150509295985092959890939650565b634e487b7160e01b600052603260045260246000fd5b60006001820161050a57634e487b7160e01b600052601160045260246000fd5b5060010190565b6000815180845260005b818110156105375760208185018101518683018201520161051b565b506000602082860101526020601f19601f83011685010191505092915050565b6000608080835261056a81840188610511565b602087818601526040858303818701528288518085528385019150838160051b860101848b0160005b838110156105f757601f198884030185528151805184528781015188850152868101518a888601526105c78b860182610511565b9050606080830151925085820381870152506105e38183610511565b968901969450505090860190600101610593565b505088810360608a015261060b818b610511565b9d9c5050505050505050505050505056fea26469706673582212203aebe7f899cd3eacc9c182445334dc4ab560ac075af6697873104a5a58dbf96c64736f6c63430008120033";

    public static final String FUNC_PROCESSALERT = "processAlert";

    public static final Event NEWALERT_EVENT = new Event("NewAlert",
            Arrays.asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Suspect>>() {}, new TypeReference<Utf8String>() {}));

//    @Deprecated
//    protected AlertProcessor(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
//    }

    protected AlertProcessor(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

//    @Deprecated
//    protected AlertProcessor(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
//    }

    protected AlertProcessor(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

//    public static List<NewAlertEventResponse> getNewAlertEvents(TransactionReceipt transactionReceipt) {
//        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NEWALERT_EVENT, transactionReceipt);
//        ArrayList<NewAlertEventResponse> responses = new ArrayList<NewAlertEventResponse>(valueList.size());
//        for (Contract.EventValuesWithLog eventValues : valueList) {
//            NewAlertEventResponse typedResponse = new NewAlertEventResponse();
//            typedResponse.log = eventValues.getLog();
//            typedResponse.fqmn = (String) eventValues.getNonIndexedValues().get(0).getValue();
//            typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
//            typedResponse.suspect = (List<Suspect>) ((Array) eventValues.getNonIndexedValues().get(2)).getNativeValueCopy();
//            typedResponse.description = (String) eventValues.getNonIndexedValues().get(3).getValue();
//            responses.add(typedResponse);
//        }
//        return responses;
//    }

    public Flowable<NewAlertEventResponse> newAlertEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewAlertEventResponse>() {
            @Override
            public NewAlertEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWALERT_EVENT, log);
                NewAlertEventResponse typedResponse = new NewAlertEventResponse();
                typedResponse.log = log;
                typedResponse.fqmn = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.timestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.suspect = (List<Suspect>) ((Array) eventValues.getNonIndexedValues().get(2)).getNativeValueCopy();
                typedResponse.description = (String) eventValues.getNonIndexedValues().get(3).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewAlertEventResponse> newAlertEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWALERT_EVENT));
        return newAlertEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> processAlert(String fqmn, byte[] callstack, List<BigInteger> parameterIndexes, List<BigInteger> features, List<String> paths, List<String> chars, BigInteger timestamp, String description) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PROCESSALERT,
                Arrays.asList(new org.web3j.abi.datatypes.Utf8String(fqmn),
                        new org.web3j.abi.datatypes.generated.Bytes32(callstack),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                                org.web3j.abi.datatypes.generated.Uint256.class,
                                org.web3j.abi.Utils.typeMap(parameterIndexes, org.web3j.abi.datatypes.generated.Uint256.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                                org.web3j.abi.datatypes.generated.Uint256.class,
                                org.web3j.abi.Utils.typeMap(features, org.web3j.abi.datatypes.generated.Uint256.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                                org.web3j.abi.datatypes.Utf8String.class,
                                org.web3j.abi.Utils.typeMap(paths, org.web3j.abi.datatypes.Utf8String.class)),
                        new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                                org.web3j.abi.datatypes.Utf8String.class,
                                org.web3j.abi.Utils.typeMap(chars, org.web3j.abi.datatypes.Utf8String.class)),
                        new org.web3j.abi.datatypes.generated.Uint256(timestamp),
                        new org.web3j.abi.datatypes.Utf8String(description)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

//    @Deprecated
//    public static AlertProcessor load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        return new AlertProcessor(contractAddress, web3j, credentials, gasPrice, gasLimit);
//    }
//
//    @Deprecated
//    public static AlertProcessor load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        return new AlertProcessor(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
//    }

    public static AlertProcessor load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AlertProcessor(contractAddress, web3j, credentials, contractGasProvider);
    }

//    public static AlertProcessor load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return new AlertProcessor(contractAddress, web3j, transactionManager, contractGasProvider);
//    }

//    public static RemoteCall<AlertProcessor> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(AlertProcessor.class, web3j, credentials, contractGasProvider, BINARY, "");
//    }

//    @Deprecated
//    public static RemoteCall<AlertProcessor> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
//        return deployRemoteCall(AlertProcessor.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
//    }

//    public static RemoteCall<AlertProcessor> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(AlertProcessor.class, web3j, transactionManager, contractGasProvider, BINARY, "");
//    }

//    @Deprecated
//    public static RemoteCall<AlertProcessor> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
//        return deployRemoteCall(AlertProcessor.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
//    }


    public static class NewAlertEventResponse extends BaseEventResponse {
        public String fqmn;

        public BigInteger timestamp;

        public List<Suspect> suspect;

        public String description;
    }
}

