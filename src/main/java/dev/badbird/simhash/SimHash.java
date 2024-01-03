package dev.badbird.simhash;

import dev.badbird.simhash.hash.HashAlgorithm;

import java.util.Arrays;

public class SimHash {
    private static final int HASH_BITS = 64;

    public static long simHash(String text, HashAlgorithm hashAlgorithm) {
        int[] bits = new int[HASH_BITS];
        Arrays.fill(bits, 0);

        String[] words = text.split("\\s+");
        for (String word : words) {
            long hash = hashAlgorithm.hash(word);

            for (int i = 0; i < HASH_BITS; i++) {
                if ((hash >> i & 1) == 1) {
                    bits[i]++;
                } else {
                    bits[i]--;
                }
            }
        }

        long simHash = 0;
        for (int i = 0; i < HASH_BITS; i++) {
            if (bits[i] > 0) {
                simHash |= (1L << i);
            }
        }

        return simHash;
    }

    public static double calculateSimilarity(long hash1, long hash2) {
        return (HASH_BITS - hammingDistance(hash1, hash2)) / (double) HASH_BITS;
    }

    private static int hammingDistance(long x, long y) {
        long xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            distance += 1;
            xor &= xor - 1;
        }
        return distance;
    }
}
