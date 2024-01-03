package dev.badbird.simhash;

import dev.badbird.simhash.hash.HashAlgorithm;
import dev.badbird.simhash.hash.impl.MD5Hash;
import dev.badbird.simhash.hash.impl.MurmurHash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern EMPTY_LINE_PATTERN = Pattern.compile("(?m)^[ \t]*\r?\n");
    public static void main(String[] args) throws IOException {
        File test1 = new File("1.txt"), test2 = new File("2.txt");
        String str1 = new String(Files.readAllBytes(test1.toPath())), str2 = new String(Files.readAllBytes(test2.toPath()));
        // preprocess by removing empty lines / spaces
        str1 = EMPTY_LINE_PATTERN.matcher(str1).replaceAll("");
        str2 = EMPTY_LINE_PATTERN.matcher(str2).replaceAll("");

        HashAlgorithm hashAlgorithm = new MD5Hash();
        long hash1 = SimHash.simHash(str1, hashAlgorithm);
        long hash2 = SimHash.simHash(str2, hashAlgorithm);

        double similarity = SimHash.calculateSimilarity(hash1, hash2);

        System.out.println("Similarity Score: " + similarity);
    }
}