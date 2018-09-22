package chrysalis;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;   
import java.util.Random;

public class hasher
{
public static String hash(String passwordToHash)
{
String generatedPassword = null;
    try {
       String salt = getSalt();
         MessageDigest md = MessageDigest.getInstance("SHA-512");
         md.update(salt.getBytes(StandardCharsets.UTF_8));
         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
         StringBuilder sb = new StringBuilder();
         for(int i=0; i< bytes.length ;i++)
         {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
         }
         generatedPassword = sb.toString();
        } 
       catch (NoSuchAlgorithmException e)
       {
       }
    return generatedPassword;
}

public static String getSalt() {
    byte[] array = new byte[7]; // length is bounded by 7
    new Random().nextBytes(array);
    String generatedString = new String(array, Charset.forName("UTF-8"));
   
    return generatedString;
}
}