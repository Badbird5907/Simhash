package dev.badbird.simhash.hash.impl;

import dev.badbird.simhash.hash.HashAlgorithm;

public class MD5Hash implements HashAlgorithm {
    private static long bytesToLong(byte[] bytes) {
        long result = 0;
        for (int i = 0; i < Long.BYTES; i++) { // Using Long.BYTES for a 64-bit hash
            result += (i < bytes.length ? (bytes[i] & 0xffL) : 0L) << (8 * i);
        }
        return result;
    }
    @Override
    public long hash(String in) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(in.getBytes());
            return bytesToLong(bytes);
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
