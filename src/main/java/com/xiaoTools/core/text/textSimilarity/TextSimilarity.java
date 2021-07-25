package com.xiaoTools.core.text.textSimilarity;

import com.xiaoTools.lang.constant.Constant;
import com.xiaoTools.util.numUtil.NumUtil;
import com.xiaoTools.util.strUtil.StrUtil;

/**
 * [文本相似度计算](Text similarity calculation)
 * @description: zh - 文本相似度计算
 * @description: en - Text similarity calculation
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/7/24 6:24 下午
*/
public class TextSimilarity {

    /*判断两个字符串的相似度------------------------------------------------------------ similar*/

    /**
     * [计算相似度，两个都是空串相似度为1，被认为是相同的串](Calculate the similarity. Both are empty strings, and the similarity is 1, which is considered to be the same string)
     * @description: zh - 计算相似度，两个都是空串相似度为1，被认为是相同的串
     * @description: en - Calculate the similarity. Both are empty strings, and the similarity is 1, which is considered to be the same string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/25 10:40 上午
     * @param strA: 字符串1
     * @param strB: 字符串2
     * @return double
    */
    public static double similar(String strA, String strB) {
        String newStrA, newStrB;
        if (strA.length() < strB.length()) {
            newStrA = removeSign(strB);
            newStrB = removeSign(strA);
        } else {
            newStrA = removeSign(strA);
            newStrB = removeSign(strB);
        }

        // 用较大的字符串长度作为分母，相似子串作为分子计算出字串相似度
        int temp = Math.max(newStrA.length(), newStrB.length());
        if(Constant.ZERO == temp) {
            // 两个都是空串相似度为1，被认为是相同的串
            return Constant.ONE;
        }

        int temp2 = longestCommonSubstring(newStrA, newStrB).length();
        return NumUtil.div(temp2, temp);
    }

    /**
     * [计算相似度百分比](Calculate similarity percentage)
     * @description: zh - 计算相似度百分比
     * @description: en - Calculate similarity percentage
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/25 10:41 上午
     * @param strA: 字符串1
     * @param strB: 字符串2
     * @param scale: 保留小数
     * @return java.lang.String
    */
    public static String similar(String strA, String strB, int scale) {
        return NumUtil.formatPercent(similar(strA, strB), scale);
    }

    /*私有的方法------------------------------------------------------------ private*/

    /**
     * [将字符串的所有数据依次写成一行，去除无意义字符串](Write all the data of the string into one line in turn to remove the meaningless string)
     * @description: zh - 将字符串的所有数据依次写成一行，去除无意义字符串
     * @description: en - Write all the data of the string into one line in turn to remove the meaningless string
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/25 10:45 上午
     * @param value: 字符串
     * @return java.lang.String
    */
    private static String removeSign(String value) {
        int length = value.length();
        StringBuilder sb = StrUtil.builder(length);
        // 遍历字符串str,如果是汉字数字或字母，则追加到ab上面
        char c;
        for (int i = Constant.ZERO; i < length; i++) {
            c = value.charAt(i);
            if(isValidChar(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * [判断字符是否为汉字，数字和字母， 因为对符号进行相似度比较没有实际意义，故符号不加入考虑范围。](Judge whether the characters are Chinese characters, numbers and letters. Because the similarity comparison of symbols has no practical significance, the symbols are not considered.)
     * @description: zh - 判断字符是否为汉字，数字和字母， 因为对符号进行相似度比较没有实际意义，故符号不加入考虑范围。
     * @description: en - Judge whether the characters are Chinese characters, numbers and letters. Because the similarity comparison of symbols has no practical significance, the symbols are not considered.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/25 10:51 上午
     * @param value: 字符
     * @return boolean
    */
    private static boolean isValidChar(char value) {
        return (value >= 0x4E00 && value <= 0X9FFF) ||
                (value >= Constant.CHAR_DOWN_A && value <= Constant.CHAR_DOWN_Z) ||
                (value >= Constant.CHAR_UP_A && value <= Constant.CHAR_UP_Z) ||
                (value >= Constant.CHAR_ZERO && value <= Constant.CHAR_NINE);
    }

    /**
     * [求公共子串，采用动态规划算法。 其不要求所求得的字符在所给的字符串中是连续的。](The dynamic programming algorithm is used to find the common substring. It does not require the evaluated characters to be continuous in the given string.)
     * @description: zh - 求公共子串，采用动态规划算法。 其不要求所求得的字符在所给的字符串中是连续的。
     * @description: en - The dynamic programming algorithm is used to find the common substring. It does not require the evaluated characters to be continuous in the given string.
     * @version: V1.0
     * @author XiaoXunYao
     * @since 2021/7/25 10:52 上午
     * @param strA: 字符串1
     * @param strB: 字符串2
     * @return java.lang.String
    */
    private static String longestCommonSubstring(String strA, String strB) {
        char[] chars_strA = strA.toCharArray();
        char[] chars_strB = strB.toCharArray();
        int m = chars_strA.length;
        int n = chars_strB.length;

        // 初始化矩阵数据,matrix[0][0]的值为0， 如果字符数组chars_strA和chars_strB的对应位相同，则matrix[i][j]的值为左上角的值加1， 否则，matrix[i][j]的值等于左上方最近两个位置的较大值， 矩阵中其余各点的值为0.
        int[][] matrix = new int[m + Constant.ONE][n + Constant.ONE];
        for (int i = Constant.ONE; i <= m; i++) {
            for (int j = Constant.ONE; j <= n; j++) {
                if (chars_strA[i - Constant.ONE] == chars_strB[j - Constant.ONE]) {
                    matrix[i][j] = matrix[i - Constant.ONE][j - Constant.ONE] + Constant.ONE;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - Constant.ONE], matrix[i - Constant.ONE][j]);
                }
            }
        }

        // 矩阵中，如果matrix[m][n]的值不等于matrix[m-1][n]的值也不等于matrix[m][n-1]的值， 则matrix[m][n]对应的字符为相似字符元，并将其存入result数组中。
        char[] result = new char[matrix[m][n]];
        int currentIndex = result.length - Constant.ONE;
        while (matrix[m][n] != Constant.ZERO) {
            if (matrix[m][n] == matrix[m][n - Constant.ONE]) {
                n--;
            } else if (matrix[m][n] == matrix[m - Constant.ONE][n]) {
                m--;
            } else {
                result[currentIndex] = chars_strA[m - Constant.ONE];
                currentIndex--;
                n--;
                m--;
            }
        }
        return new String(result);
    }
}
