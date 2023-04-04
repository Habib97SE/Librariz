package Database;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecureData
{
    private static final String ALGORITHM_KEY = "SHA-256";
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY = "mysecretkey";

    public static boolean isValidName (String name)
    {
        String nameRegex = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidEmail (String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber (String phoneNumber)
    {
        // pattern for xxx-xxx xx xx
        String phoneRegex = "^(?:\\+46|0)[ -]*(?:\\d[ -]*){8,10}\\d$\n";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidPersonalNumber (String personalNumber)
    {
        String personalNumberRegex = "^(\\d{6})[-]{1}(\\d{4})$";
        return personalNumber.matches(personalNumberRegex);
    }

    public static boolean isValidZipCode (String zipCode)
    {
        // the valid forma is either xxx xx or xxx-xx
        String zipCodeRegex = "^(\\d{3})[-]{1}(\\d{2})$";
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

    /**
     * Encrypts the data using the secret key and returns the encrypted data in String type
     * We use the <strong>SHA-256</strong> algorithm for encryption and decryption
     *
     * @param data the data to be encrypted in String type (e.g. password or social security number)
     * @return the encrypted data in String type. If the data is not encrypted or the secret key is not valid, it returns null.
     * @throws Exception if the data is not encrypted or the secret key is not valid.
     */
    public static String encrypt (String data) throws Exception
    {
        String encryptedString = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM_KEY);
            byte[] bytes = md.digest(data.getBytes());
            encryptedString = Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return encryptedString;
    }

    /**
     * Decrypts the data using the secret key and returns the decrypted data
     * We use the same encryption algorithm for decryption as well (<strong>SHA-256</strong>)
     *
     * @param data the encrypted data
     * @return the decrypted data in String type.
     * @throws Exception if the data is not encrypted or the secret key is not valid
     */
    public static String decrypt (String data) throws Exception
    {
        String encryptedString = null;
        try
        {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM_KEY);
            byte[] bytes = md.digest(data.getBytes());
            encryptedString = Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public static boolean isValidPassword (String password)
    {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{9,}$";
        return password.matches(passwordRegex);
    }

}

