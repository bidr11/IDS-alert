package org;

import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigInteger;

public class Suspect extends DynamicStruct {
    public BigInteger parameterIndex;

    public BigInteger feature;

    public String path;

    public String chars;

    public Suspect(BigInteger parameterIndex, BigInteger feature, String path, String chars) {
        super(new org.web3j.abi.datatypes.generated.Uint256(parameterIndex),
                new org.web3j.abi.datatypes.generated.Uint256(feature),
                new org.web3j.abi.datatypes.Utf8String(path),
                new org.web3j.abi.datatypes.Utf8String(chars));
        this.parameterIndex = parameterIndex;
        this.feature = feature;
        this.path = path;
        this.chars = chars;
    }

    public Suspect(Uint256 parameterIndex, Uint256 feature, Utf8String path, Utf8String chars) {
        super(parameterIndex, feature, path, chars);
        this.parameterIndex = parameterIndex.getValue();
        this.feature = feature.getValue();
        this.path = path.getValue();
        this.chars = chars.getValue();
    }

    public BigInteger getParameterIndex() {
        return this.parameterIndex;
    }

    public BigInteger getFeature() {
        return this.feature;
    }

    public String getPath() {
        return this.path;
    }

    public String getChars() {
        return this.chars;
    }


}