/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kcc.software;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author pradeep
 */
public class UserEncrypt {
    private static FileInputStream fis = null;
    private static BufferedInputStream bis = null;
    private static DataInputStream dis = null;
    Cipher ecipher;
    Cipher dcipher;
    byte[] salt = new byte[]{-87, -101, -56, 50, 86, 53, -29, 3};
    int iterationCount = 19;
    private String secretKey = "ezeon8547";
    private String plainStr;

    public void encrypt() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException {
        URL location = UserEncrypt.class.getProtectionDomain().getCodeSource().getLocation();
        String path = location.getFile();
        String filePath = new File(path).getParent();
        String plainText1 = "onward123,postgres,postgres,onward";
        String plainText = "postgres,postgres,onward,onward";
        System.out.println("hello==========>>>" + plainText1);
        PBEKeySpec keySpec = new PBEKeySpec(this.secretKey.toCharArray(), this.salt, this.iterationCount);
        SecretKey key = SecretKeyFactory.getInstance((String)"PBEWithMD5AndDES").generateSecret((KeySpec)keySpec);
        PBEParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
        this.ecipher = Cipher.getInstance((String)key.getAlgorithm());
        this.ecipher.init(1, (Key)key, (AlgorithmParameterSpec)paramSpec);
        String charSet = "UTF-8";
        byte[] in = plainText1.getBytes(charSet);
        byte[] out = this.ecipher.doFinal(in);
        String encStr = new BASE64Encoder().encode(out);
        System.out.println(" Encrypted Users:" + encStr);
    }

    public String decrypt(String encryptedText) {
        String plainSt = "";
        try {
            PBEKeySpec keySpec = new PBEKeySpec(this.secretKey.toCharArray(), this.salt, this.iterationCount);
            SecretKey key = SecretKeyFactory.getInstance((String)"PBEWithMD5AndDES").generateSecret((KeySpec)keySpec);
            PBEParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
            this.dcipher = Cipher.getInstance((String)key.getAlgorithm());
            this.dcipher.init(2, (Key)key, (AlgorithmParameterSpec)paramSpec);
            byte[] enc = new BASE64Decoder().decodeBuffer(encryptedText);
            byte[] utf8 = this.dcipher.doFinal(enc);
            String charSet = "UTF-8";
            this.plainStr = new String(utf8, charSet);
        }
        catch (IllegalBlockSizeException ex) {
            Logger.getLogger((String)UserEncrypt.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (BadPaddingException ex) {
            Logger.getLogger((String)UserEncrypt.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (IOException ex) {
            Logger.getLogger((String)UserEncrypt.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (InvalidKeyException ex) {
            Logger.getLogger((String)UserEncrypt.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger((String)UserEncrypt.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (NoSuchPaddingException ex) {
            Logger.getLogger((String)UserEncrypt.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger((String)UserEncrypt.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        catch (InvalidKeySpecException ex) {
            Logger.getLogger((String)UserEncrypt.class.getName()).log(Level.SEVERE, null, (Throwable)ex);
        }
        return this.plainStr;
    }

    public String decrypt1(String encryptedText) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException {
        PBEParameterSpec paramSpec = new PBEParameterSpec(this.salt, this.iterationCount);
        byte[] enc = new BASE64Decoder().decodeBuffer(encryptedText);
        byte[] utf8 = this.dcipher.doFinal(enc);
        String charSet = "UTF-8";
        String plainStr = new String(utf8, charSet);
        return plainStr;
    }

    public static void main(String[] args) throws Exception {
        UserEncrypt cryptoUtil = new UserEncrypt();
        cryptoUtil.encrypt();
    }
}
