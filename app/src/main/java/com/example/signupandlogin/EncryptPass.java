package com.example.signupandlogin;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class EncryptPass {
    public static void passwordCheck(String password)
    {

        /* generates the Salt value. It can be stored in a database. */
        String saltValue = PassBasedEnc.getSaltValue(30);

        /* generates an encrypted password. It can be stored in a database.*/
        String encryptedPassword = PassBasedEnc.generateSecurePassword(password, saltValue);

//        /* Print out plain text password, encrypted password and salt value. */
//        System.out.println("Plain text password = " + password);
//        System.out.println("Secure password = " + encryptedPassword);
//        System.out.println("Salt value = " + saltValue);

        /* verify the original password and encrypted password */
        boolean status = PassBasedEnc.verifyUserPassword(password,encryptedPassword,saltValue);
        if(status)
            System.out.println("Password Matched!!");
        else
            System.out.println("Password Mismatched");
    }
}

