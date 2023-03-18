package Database;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecureData
{

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY = "mysecretkey";

    public static boolean isValidName(String name) {
        String nameRegex = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\+46\\d{9}$|^0\\d{9}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidPersonalNumber (String personalNumber)
    {
        String personalNumberRegex = "^(\\d{6}|\\d{8})[-|\\s]?\\d{4}$";
        return personalNumber.matches(personalNumberRegex);
    }

    public static boolean isValidZipCode (String zipCode)
    {
        String zipCodeRegex = "^(\\d{5})$";
        return zipCode.matches(zipCodeRegex);
    }

    public static boolean isValidCity (String city)
    {
        if (city.length() < 3)
            return false;
        else
        {
            for (int i = 0; i < city.length(); i++)
            {
                if (Character.isDigit(city.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    public static boolean isValidAddress (String homeAddress)
    {
        return homeAddress.length() < 3;
    }

    public static String encrypt(String data) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Arrays.toString(cipher.doFinal(data.getBytes()));
    }

    public static String decrypt(byte[] encryptedData) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(encryptedData);
        return new String(decryptedBytes);
    }

    private static Key generateKey() throws Exception {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

}

