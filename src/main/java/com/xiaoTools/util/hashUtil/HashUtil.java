package com.xiaoTools.util.hashUtil;

import com.xiaoTools.lang.constant.Constant;

/**
 * [hash算法大全](Hash algorithm)
 * @description: zh - hash算法大全
 * @description: en - Hash algorithm
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/7 12:57 下午
*/
public class HashUtil {
    /**
     * [hash算法的加法](Addition of hash algorithm)
     * @description: zh - hash算法的加法
     * @description: en - Addition of hash algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 1:26 下午
     * @param key: [字符串](character string)
     * @param prime: [一个质数](A prime number)
     * @return int
    */
    public static int additiveHash(String key,int prime){
        int hash = key.length();
        for (int i = Constant.ZERO; i < key.length(); i++) { hash += key.charAt(i); }
        return hash % prime;
    }

    /**
     * [翻转的hash算法](Flip hash algorithm)
     * @description: zh - 翻转的hash算法
     * @description: en - Flip hash algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 1:43 下午
     * @param key: [字符串](character string)
     * @param prime: [一个质数](A prime number)
     * @return int
    */
    public static int rotatingHash(String key, int prime) {
        int hash = key.length();
        for ( int i = Constant.ZERO; i < key.length(); ++i) {
            hash = (hash << Constant.FOUR) ^ (hash >> Constant.TWENTY_EIGHT) ^ key.charAt(i);
        }
        return hash % prime;
    }

    /**
     * [一次一个hash](One hash at a time)
     * @description: zh - 一次一个hash
     * @description: en - One hash at a time
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 1:53 下午
     * @param key: [输入的字符串](Input string)
     * @return int
    */
    public static int oneByOneHash(String key) {
        int hash;
        int i;
        for (hash = Constant.ZERO, i = Constant.ZERO; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << Constant.TEN);
            hash ^= (hash >> Constant.SIX);
        }
        hash += (hash << Constant.THREE);
        hash ^= (hash >> Constant.ELEVEN);
        hash += (hash << Constant.FIFTEEN);
        return hash;
    }

    /**
     * [bernstein的hash算法](Bernstein's hash algorithm)
     * @description: zh - bernstein的hash算法
     * @description: en - Bernstein's hash algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 2:00 下午
     * @param key: [输入的字符串](Input string)
     * @return int
    */
    public static int bernstein(String key) {
        int hash = Constant.ZERO;
        for ( int i = Constant.ZERO; i < key.length(); ++i) { hash = Constant.THIRTY_THREE * hash + key.charAt(i); }
        return hash;
    }

    /**
     * [universal的hash算法](Universal hash algorithm)
     * @description: zh - universal的hash算法
     * @description: en - Universal hash algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 2:33 下午
     * @param key: [字节数组](Byte array)
     * @param mask: [掩码](Mask)
     * @param tab: [tab](tab)
     * @return int
    */
    public static int universal(char[] key, int mask, int[] tab){
        int hash = key.length, i, len = key.length;
        for (i = Constant.ZERO; i < (len << Constant.THREE); i += Constant.EIGHT) {
            char k = key[i >> Constant.THREE];
            if ((k & Constant.CHAR_ONE) == Constant.ZERO) {
                hash ^= tab[i];
            }
            if ((k & Constant.CHAR_TWO) == Constant.ZERO) {
                hash ^= tab[i + Constant.ONE];
            }
            if ((k & Constant.CHAR_FOUR) == Constant.ZERO) {
                hash ^= tab[i + Constant.TWO];
            }
            if ((k & Constant.CHAR_EIGHT) == Constant.ZERO) {
                hash ^= tab[i + Constant.THREE];
            }
            if ((k & Constant.CHAR_TEN) == Constant.ZERO) {
                hash ^= tab[i + Constant.FOUR];
            }
            if ((k & Constant.CHAR_TWENTY) == Constant.ZERO) {
                hash ^= tab[i + Constant.FIVE];
            }
            if ((k & Constant.CHAR_FORTY) == Constant.ZERO) {
                hash ^= tab[i + Constant.SIX];
            }
            if ((k & Constant.CHAR_EIGHTY) == Constant.ZERO) {
                hash ^= tab[i + Constant.SEVEN];
            }
        }
        return (hash & mask);
    }

    /**
     * [zobrist的hash算法](Zobrist's hash algorithm)
     * @description: zh - zobrist的hash算法
     * @description: en - Zobrist's hash algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 2:39 下午
     * @param key: [字节数组](Byte array)
     * @param mask: [掩码](Mask)
     * @param tab: [tab](tab)
     * @return int
    */
    public static int zobrist(char[] key, int mask, int[][] tab) {
        int hash = key.length;
        for (int i = Constant.ZERO; i < key.length; ++i) { hash ^= tab[i][key[i]]; }
        return (hash & mask);
    }

    /**
     * [改进的32位FNV算法1](Improved 32-bit FNV algorithm 1)
     * @description: zh - 改进的32位FNV算法1
     * @description: en - Improved 32-bit FNV algorithm 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 2:54 下午
     * @param data: [数组](array)
     * @return int
    */
    public static int fnvHash(byte[] data) {
        final int p = Constant.HASH_VALUE_THREE;
        int hash = (int) Constant.HASH_VALUE_SIX;
        for (byte b : data) {
            hash = (hash ^ b) * p;
        }
        hash += hash << Constant.THIRTEEN;
        hash ^= hash >> Constant.SEVEN;
        hash += hash << Constant.THREE;
        hash ^= hash >> Constant.SEVENTEEN;
        hash += hash << Constant.FIVE;
        return Math.abs(hash);
    }

    /**
     * [改进的32位FNV算法1](Improved 32-bit FNV algorithm 1)
     * @description: zh - 改进的32位FNV算法1
     * @description: en - Improved 32-bit FNV algorithm 1
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 2:56 下午
     * @param data: [输入的字符串](Input string)
     * @return int
    */
    public static int fnvHash(String data) {
        final int p = Constant.HASH_VALUE_THREE;
        int hash = (int) Constant.HASH_VALUE_SIX;
        for (int i = Constant.ZERO; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << Constant.THIRTEEN;
        hash ^= hash >> Constant.SEVEN;
        hash += hash << Constant.THREE;
        hash ^= hash >> Constant.SEVENTEEN;
        hash += hash << Constant.FIVE;
        return Math.abs(hash);
    }

    /**
     * [Thomas Wang的算法，整数hash](Thomas Wang's algorithm, integer hash)
     * @description: zh - Thomas Wang的算法，整数hash
     * @description: en - Thomas Wang's algorithm, integer hash
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 3:00 下午
     * @param key: [整数](integer)
     * @return int
    */
    public static int intHash(int key) {
        key += ~(key << Constant.FIFTEEN);
        key ^= (key >>> Constant.TEN);
        key += (key << Constant.THREE);
        key ^= (key >>> Constant.SIX);
        key += ~(key << Constant.ELEVEN);
        key ^= (key >>> Constant.SIXTEEN);
        return key;
    }

    /**
     * [RS算法hash](RS algorithm hash)
     * @description: zh - RS算法hash
     * @description: en - RS algorithm hash
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 3:08 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int rsHash(String str) {
        int b = Constant.HASH_VALUE_ONE;
        int a = Constant.HASH_VALUE_ZERO;
        int hash = Constant.ZERO;
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        return hash & Constant.HEXADECIMAL_SEVEN;
    }

    /**
     * [js的hash算法](Hash algorithm of JS)
     * @description: zh - js的hash算法
     * @description: en - Hash algorithm of JS
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 3:16 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int jsHash(String str) {
        int hash = Constant.HASH_VALUE_FOUR;
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash ^= ((hash << Constant.FIVE) + str.charAt(i) + (hash >> Constant.TWO));
        }
        return hash & Constant.HEXADECIMAL_SEVEN;
    }

    /**
     * [PJW 的hash算法](Hash algorithm of pjw)
     * @description: zh - PJW 的hash算法
     * @description: en - Hash algorithm of pjw
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 3:21 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int pjwHash(String str) {
        int bitsInUnsignedInt = Constant.THIRTY_TWO;
        int threeQuarters = (bitsInUnsignedInt * Constant.THREE) / Constant.FOUR;
        int oneEighth = bitsInUnsignedInt / Constant.EIGHT;
        int highBits = Constant.HEXADECIMAL_ZERO << (bitsInUnsignedInt - oneEighth);
        int hash = Constant.ZERO;
        int test;
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash = (hash << oneEighth) + str.charAt(i);
            if ((test = hash & highBits) != Constant.ZERO) {
                hash = ((hash ^ (test >> threeQuarters)) & (~highBits));
            }
        }
        return hash & Constant.HEXADECIMAL_SEVEN;
    }

    /**
     * [elf的hash算法](Hash algorithm of ELF)
     * @description: zh - elf的hash算法
     * @description: en - Hash algorithm of ELF
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 3:34 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int elfHash(String str) {
        int hash = Constant.ZERO;
        int x;
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash = (hash << Constant.FOUR) + str.charAt(i);
            if ((x = (int) (hash & Constant.HEXADECIMAL_F)) != Constant.ZERO) {
                hash ^= (x >> Constant.TWENTY_FOUR);
                hash &= ~x;
            }
        }
        return hash & Constant.HEXADECIMAL_SEVEN;
    }

    /**
     * [BKDR算法](Bkdr algorithm)
     * @description: zh - BKDR算法
     * @description: en - Bkdr algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:17 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int bkdrHash(String str) {
        int seed = Constant.HUNDRED_THIRTY_ONE;
        int hash = Constant.ZERO;
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash = (hash * seed) + str.charAt(i);
        }
        return hash & Constant.HEXADECIMAL_SEVEN;
    }

    /**
     * [SDBM算法](Sdbm algorithm)
     * @description: zh - SDBM算法
     * @description: en - Sdbm algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:19 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int sdbmHash(String str) {
        int hash = Constant.ZERO;
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash = str.charAt(i) + (hash << Constant.SIX) + (hash << Constant.SIXTEEN) - hash;
        }
        return hash & Constant.HEXADECIMAL_SEVEN;
    }

    /**
     * [DJB算法](DJB algorithm)
     * @description: zh - DJB算法
     * @description: en - DJB algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:20 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int djbHash(String str) {
        int hash = Constant.FIVE_THREE_EIGHT_ONE;
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash = ((hash << Constant.FIVE) + hash) + str.charAt(i);
        }
        return hash & Constant.HEXADECIMAL_SEVEN;
    }

    /**
     * [DEK算法](DEK algorithm)
     * @description: zh - DEK算法
     * @description: en - DEK algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:21 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int dekHash(String str) {
        int hash = str.length();
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash = ((hash << Constant.FIVE) ^ (hash >> Constant.TWENTY_SEVEN)) ^ str.charAt(i);
        }
        return hash & Constant.HEXADECIMAL_SEVEN;
    }

    /**
     * [AP算法](AP algorithm)
     * @description: zh - AP算法
     * @description: en - AP algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:22 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int apHash(String str) {
        int hash = Constant.ZERO;
        for (int i = Constant.ZERO; i < str.length(); i++) {
            hash ^= ((i & Constant.ONE) == Constant.ZERO) ? ((hash << Constant.SEVEN) ^ str.charAt(i) ^ (hash >> Constant.THREE)) : (~((hash << Constant.ELEVEN) ^ str.charAt(i) ^ (hash >> Constant.FIVE)));
        }
        return hash;
    }

    /**
     * [TianL Hash算法](Tianl hash algorithm)
     * @description: zh - TianL Hash算法
     * @description: en - Tianl hash algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:25 下午
     * @param str: [字符串](character string)
     * @return long
    */
    public static long tianlHash(String str) {
        long hash;
        int iLength = str.length();
        if (iLength == Constant.ZERO) { return Constant.ZERO; }
        if (iLength <= Constant.TWO_FIFTY_SIX) {
            hash = Constant.HASH_VALUE_FIVE * (iLength - Constant.ONE);
        } else {
            hash = Constant.HASH_VALUE_SEVEN;
        }
        int i;
        char ucChar;
        if (iLength <= Constant.NINETY_SIX) {
            for (i = Constant.ONE; i <= iLength; i++) {
                ucChar = str.charAt(i - Constant.ONE);
                if (ucChar <= Constant.CHAR_UP_Z && ucChar >= Constant.CHAR_UP_A) { ucChar = (char) (ucChar + Constant.THIRTY_TWO); }
                hash += (Constant.LONG_THREE * i * ucChar * ucChar + Constant.FIVE * i * ucChar + Constant.SEVEN * i + Constant.ELEVEN * ucChar) % Constant.HASH_VALUE_TWO;
            }
        } else {
            for (i = Constant.ONE; i <= Constant.NINETY_SIX; i++) {
                ucChar = str.charAt(i + iLength - Constant.NINETY_SIX - Constant.ONE);
                if (ucChar <= Constant.CHAR_UP_Z && ucChar >= Constant.CHAR_UP_A) {
                    ucChar = (char) (ucChar + Constant.THIRTY_TWO);
                }
                hash += (Constant.LONG_THREE * i * ucChar * ucChar + Constant.LONG_FIVE * i * ucChar + Constant.LONG_SEVEN * i + Constant.ELEVEN * ucChar) % Constant.HASH_VALUE_TWO;
            }
        }
        if (hash < Constant.ZERO) {
            hash *= Constant.NEGATIVE_ONE;
        }
        return hash;
    }

    /**
     * [JAVA自己带的算法](Java with its own algorithm)
     * @description: zh - JAVA自己带的算法
     * @description: en - Java with its own algorithm
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:26 下午
     * @param str: [字符串](character string)
     * @return int
    */
    public static int javaDefaultHash(String str) {
        int h = Constant.ZERO;
        int off = Constant.ZERO;
        int len = str.length();
        for (int i = Constant.ZERO; i < len; i++) {
            h = Constant.THIRTY_ONE * h + str.charAt(off++);
        }
        return h;
    }

    /**
     * [混合hash算法，输出64位的值](Hybrid hash algorithm, output 64 bit value)
     * @description: zh - 混合hash算法，输出64位的值
     * @description: en - Hybrid hash algorithm, output 64 bit value
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:27 下午
     * @param str: [字符串](character string)
     * @return long
    */
    public static long mixHash(String str) {
        long hash = str.hashCode();
        hash <<= Constant.THIRTY_TWO;
        hash |= fnvHash(str);
        return hash;
    }

    /**
     * [根据对象的内存地址生成相应的Hash值](Generate the corresponding hash value according to the memory address of the object)
     * @description: zh - 根据对象的内存地址生成相应的Hash值
     * @description: en - Generate the corresponding hash value according to the memory address of the object
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/6/7 4:27 下午
     * @param obj: [对象](object)
     * @return int
    */
    public static int identityHashCode(Object obj) {
        return System.identityHashCode(obj);
    }

}
