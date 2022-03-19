package com.example.rest.sample;

import java.nio.charset.StandardCharsets;

public class MaxSubstringlen {

    public static void main(String args[]) {

        String s = "abcabcbb";
        System.out.println(geSubStringLen(s));

        s = "bbbbb";

        System.out.println(geSubStringLen(s));

        s = "pwwkew";
        System.out.println(geSubStringLen(s));


       s=  "abccdefghhhgifkle";
        System.out.println(geSubStringLen(s));
    }

    /*
    maxlen1 = max(maxlen1, maxlen2)

    maxlen2 =

    maxlen = max (maxlen1,maxlen2);

    stop conditon = end of string;
 1. start with index 0 , continue untill any repeating char found .

            2. loop agian from first index of repeat charachter + 1

    maxlen;

*/

    public static int geSubStringLen(String str){
        int maxlen = 0;
        int maxlen1 = 0;

       StringBuffer buff = new StringBuffer();  // 2x

       char[] chars =  str.toCharArray(); // -- ref

        for (int i = 0 ; i < str.length(); i++) {  // n

            if( hasRepeatChar(buff.toString(), chars[i]) ){  // n
              // repeat loop from index
                maxlen = Math.max(maxlen, maxlen1);
                maxlen1 = 0;

               // buff = new StringBuffer(); //

            }

            buff.append(chars[i]);
            maxlen1++;

        }

       // System.out.println(buff.toString());

        return Math.max(maxlen, maxlen1);
    }

    public static boolean hasRepeatChar(String s, char c){
       return !(  s.indexOf(c) < 0 );
    }
}
