package com.xiaoTools.core.hashUtil;

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
        for ( int i = 0; i < key.length(); i++) { hash += key.charAt(i); }
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
        for ( int i = 0; i < key.length(); ++i) { hash = (hash << 4) ^ (hash >> 28) ^ key.charAt(i); }
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
        for (hash = 0, i = 0; i < key.length(); ++i) {
            hash += key.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >> 11);
        hash += (hash << 15);
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
        int hash = 0;
        for ( int i = 0; i < key.length(); ++i) { hash = 33 * hash + key.charAt(i); }
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
        for (i = 0; i < (len << 3); i += 8) {
            char k = key[i >> 3];
            if ((k & 0x01) == 0) {
                hash ^= tab[i];
            }
            if ((k & 0x02) == 0) {
                hash ^= tab[i + 1];
            }
            if ((k & 0x04) == 0) {
                hash ^= tab[i + 2];
            }
            if ((k & 0x08) == 0) {
                hash ^= tab[i + 3];
            }
            if ((k & 0x10) == 0) {
                hash ^= tab[i + 4];
            }
            if ((k & 0x20) == 0) {
                hash ^= tab[i + 5];
            }
            if ((k & 0x40) == 0) {
                hash ^= tab[i + 6];
            }
            if ((k & 0x80) == 0) {
                hash ^= tab[i + 7];
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
        for (int i = 0; i < key.length; ++i) { hash ^= tab[i][key[i]]; }
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
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (byte b : data) {
            hash = (hash ^ b) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
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
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < data.length(); i++) {
            hash = (hash ^ data.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
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
        key += ~(key << 15);
        key ^= (key >>> 10);
        key += (key << 3);
        key ^= (key >>> 6);
        key += ~(key << 11);
        key ^= (key >>> 16);
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
        int b = 378551;
        int a = 63689;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * a + str.charAt(i);
            a = a * b;
        }
        return hash & 0x7FFFFFFF;
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
        int hash = 1315423911;
        for (int i = 0; i < str.length(); i++) { hash ^= ((hash << 5) + str.charAt(i) + (hash >> 2)); }
        return hash & 0x7FFFFFFF;
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
        int bitsInUnsignedInt = 32;
        int threeQuarters = (bitsInUnsignedInt * 3) / 4;
        int oneEighth = bitsInUnsignedInt / 8;
        int highBits = 0xFFFFFFFF << (bitsInUnsignedInt - oneEighth);
        int hash = 0;
        int test;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << oneEighth) + str.charAt(i);
            if ((test = hash & highBits) != 0) {
                hash = ((hash ^ (test >> threeQuarters)) & (~highBits));
            }
        }
        return hash & 0x7FFFFFFF;
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
        int hash = 0;
        int x;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 4) + str.charAt(i);
            if ((x = (int) (hash & 0xF0000000L)) != 0) {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }

        return hash & 0x7FFFFFFF;
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
        // 31 131 1313 13131 131313 etc..
        int seed = 131;
        int hash = 0;
        for (int i = 0; i < str.length(); i++) { hash = (hash * seed) + str.charAt(i); }
        return hash & 0x7FFFFFFF;
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
        int hash = 0;
        for (int i = 0; i < str.length(); i++) { hash = str.charAt(i) + (hash << 6) + (hash << 16) - hash; }
        return hash & 0x7FFFFFFF;
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
        int hash = 5381;

        for (int i = 0; i < str.length(); i++) {
            hash = ((hash << 5) + hash) + str.charAt(i);
        }

        return hash & 0x7FFFFFFF;
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
        for (int i = 0; i < str.length(); i++) { hash = ((hash << 5) ^ (hash >> 27)) ^ str.charAt(i); }
        return hash & 0x7FFFFFFF;
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
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash ^= ((i & 1) == 0) ? ((hash << 7) ^ str.charAt(i) ^ (hash >> 3)) : (~((hash << 11) ^ str.charAt(i) ^ (hash >> 5)));
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
        if (iLength == 0) { return 0; }
        if (iLength <= 256) {
            hash = 16777216L * (iLength - 1);
        } else {
            hash = 4278190080L;
        }
        int i;
        char ucChar;
        if (iLength <= 96) {
            for (i = 1; i <= iLength; i++) {
                ucChar = str.charAt(i - 1);
                if (ucChar <= 'Z' && ucChar >= 'A') { ucChar = (char) (ucChar + 32); }
                hash += (3L * i * ucChar * ucChar + 5 * i * ucChar + 7 * i + 11 * ucChar) % 16777216;
            }
        } else {
            for (i = 1; i <= 96; i++) {
                ucChar = str.charAt(i + iLength - 96 - 1);
                if (ucChar <= 'Z' && ucChar >= 'A') { ucChar = (char) (ucChar + 32); }
                hash += (3L * i * ucChar * ucChar + 5L * i * ucChar + 7L * i + 11 * ucChar) % 16777216;
            }
        }
        if (hash < 0) { hash *= -1; }
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
        int h = 0;
        int off = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            h = 31 * h + str.charAt(off++);
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
        hash <<= 32;
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
