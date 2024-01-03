package dev.badbird.simhash.hash.impl;

import com.google.common.hash.Hashing;
import dev.badbird.simhash.hash.HashAlgorithm;

public class MurmurHash implements HashAlgorithm {
    @Override
    public long hash(String in) {
        return Hashing.murmur3_128().hashUnencodedChars(in).asLong();
    }
}
