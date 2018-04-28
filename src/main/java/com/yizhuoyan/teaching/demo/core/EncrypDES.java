package com.yizhuoyan.teaching.demo.core;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Security;
import java.util.Arrays;

public class EncrypDES {

    //KeyGenerator 提供对称密钥生成器的功能，支持各种算法
    private static  KeyGenerator keygen;
    //SecretKey 负责保存对称密钥
    private static SecretKey deskey;
    static{
        init();
    }
    private static void init(){
        try {
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            keygen = KeyGenerator.getInstance("DES");
            deskey=keygen.generateKey();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 对字符串加密
     *
     */
    public static String encrytor(String str) throws Exception {
        // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
        Cipher  c = Cipher.getInstance("DES");
        c.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] src = str.getBytes();
        // 加密，结果保存进cipherByte
        src= c.doFinal(src);
        //转换为32进制
        StringBuilder result=new StringBuilder();
        for(byte b:src){
            result.append(Integer.toString(Byte.toUnsignedInt(b),32));
            result.append("-");
        }
        result.deleteCharAt(result.length()-1);
        System.out.println(Arrays.toString(src));
        return result.toString();
    }

    /**
     * 对字符串解密
     *
     */
    public static  String decryptor(String str) throws Exception {
        String[] cs=str.split("-");
        byte[] buff=new byte[cs.length];
        int i=0;
        for(String s:cs){
            int v=Integer.parseInt(s,32);
            buff[i++]=(byte)v;
        }
        Cipher c=Cipher.getInstance("DES");
        c.init(Cipher.DECRYPT_MODE, deskey);
        buff=c.doFinal(buff);
        return new String(buff);
    }



    public static void main(String[] args) throws Exception {
        String msg ="你大爷";
        String encontent = encrytor(msg);
        String decontent = decryptor(encontent);
        System.out.println("明文是:" + msg);
        System.out.println("加密后:" + encontent);
        System.out.println("解密后:" + decontent);
    }

}
