package leancarbuilder.workstation;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.SecureRandom;

public class Worker {

	/**
	 * Uses SHA1 password encryption to work the CPU
	 */
    private static final int iterations = 1000;
    private static final int saltLen = 32;
    private static final int desiredKeyLen = 256;
    private static final String password = "correcthorsebatterystaple";
    private int effort = 1;

	public Worker(int taskSize) {
		effort = taskSize;
	}
	
	public void doWork() {
		for (int i=0; i<effort; i++) {
			try {
				String hash = getSaltedHash(password);
				boolean check = check(password, hash);
				//System.out.println("Hash = "+hash+" and is "+check);;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//System.out.println(effort+" units of work done");
	}
	
    /**
     * Copied from erickson @ http://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java/2861125#2861125 
     */
	
    /** Computes a salted PBKDF2 hash of given plaintext password
        suitable for storing in a database. 
        Empty passwords are not supported. */
    private static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        return Base64.encode(salt) + "$" + hash(password, salt);
    }

    /** Checks whether given plaintext password corresponds 
        to a stored salted hash of the password. */
    private static boolean check(String password, String stored) throws Exception{
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2) {
            throw new IllegalStateException(
                "The stored password have the form 'salt$hash'");
        }
        String hashOfInput = hash(password, Base64.decode(saltAndPass[0]));
        return hashOfInput.equals(saltAndPass[1]);
    }

    // using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
    // cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, iterations, desiredKeyLen)
        );
        return Base64.encode(key.getEncoded());
    }
}
