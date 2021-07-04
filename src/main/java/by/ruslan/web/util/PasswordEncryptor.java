package by.ruslan.web.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The {@code PasswordEncryptor} class is responsible for password encryption
 *
 * @author Ruslan Nedvedskiy
 */
public class PasswordEncryptor {
    private final static String ENCRYPTOR_ALGORITHM = "SHA-1";
    private static final String ENCODING = "utf8";

    private PasswordEncryptor() {
    }

    public static String encrypt(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTOR_ALGORITHM);
        messageDigest.update(password.getBytes(ENCODING));
        byte[] bytesEncoded = messageDigest.digest();
        BigInteger bigInt = new BigInteger(1, bytesEncoded);
        String encryptedPassword = bigInt.toString(16);
        return encryptedPassword;
    }
}
