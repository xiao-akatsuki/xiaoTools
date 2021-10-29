package com.xiaoTools.lang.constant;

import com.xiaoTools.date.dateTime.DateTime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * [统一常量](Uniform constant)
 * @description: zh - 统一常量
 * @description: en - Uniform constant
 * @version: V1.0
 * @author XiaoXunYao
 * @since 2021/6/14 11:49 上午
*/
public class Constant {

    /*-----------------------------------------------------------int*/

    public static final int NEGATIVE_ONE = -1;

    public static final int ZERO = 0;

    public static final int ONE = 1;

    public static final int TWO = 2;

    public static final int THREE = 3;

    public static final int FOUR = 4;

    public static final int FIVE = 5;

    public static final int SIX = 6;

    public static final int SEVEN = 7;

    public static final int EIGHT = 8;

    public static final int NINE = 9;

    public static final int TEN = 10;

    public static final int ELEVEN = 11;

    public static final int TWELVE = 12;

    public static final int THIRTEEN = 13;

    public static final int FIFTEEN = 15;

    public static final int SIXTEEN = 16;

    public static final int SEVENTEEN = 17;

    public static final int TWENTY = 20;

    public static final int TWENTY_THREE = 23;

    public static final int TWENTY_FOUR = 24;

    public static final int TWENTY_SEVEN = 27;

    public static final int TWENTY_EIGHT = 28;

    public static final int THIRTY_ONE = 31;

    public static final int THIRTY_TWO = 32;

    public static final int THIRTY_THREE = 33;

    public static final int THIRTY_SIX = 36;

    public static final int FORTY_EIGHT = 48;

    public static final int FIFTY = 50;

    public static final int SIXTY = 60;

    public static final int SIXTY_TWO = 62;

    public static final int SIXTY_THREE = 63;

    public static final int SIXTY_FOUR = 64;

    public static final int EIGHTY = 80;

    public static final int NINETY_SIX = 96;

    public static final int HUNDRED = 100;

    public static final int ONE_TWO_EIGHT = 128;

    public static final int HUNDRED_THIRTY_ONE = 131;

    public static final int CIRCULAR = 180;

    public static final int TWO_FIVE = 255;

    public static final int TWO_FIFTY_SIX = 256;

    public static final int ONE_THOUSAND = 1000;

    public static final int THREE_SIX_DOUBLE_ZERO = 3600;

    public static final int NOTHING_BOM = 65279;

    public static final int FIVE_THREE_EIGHT_ONE = 5381;

    public static final int UTF8_BOM = 8234;

    public static final int FILE_BYTE = 1444;

    public static final int ONE_MEGA_BYTES = 1024;

    public static final int ONE_NINE_DOUBLE_ZERO = 1900;

    public static final int TEN_THOUSAND = 10000;

    public static final int ONE_HUNDRED_MILLION = 100000000;

    public static final int HEXADECIMAL_ZERO = 0xFFFFFFFF;

    public static final int HEXADECIMAL_SEVEN = 0x7FFFFFFF;

    /*-----------------------------------------------------------hash*/

    public static final int HASH_VALUE_ZERO = 63689;

    public static final int HASH_VALUE_ONE = 378551;

    public static final int HASH_VALUE_TWO = 16777216;

    public static final int HASH_VALUE_THREE = 16777619;

    public static final int HASH_VALUE_FOUR = 1315423911;

    public static final long HASH_VALUE_FIVE = 16777216L;

    public static final long HASH_VALUE_SIX = 2166136261L;

    public static final long HASH_VALUE_SEVEN = 4278190080L;

    /*-----------------------------------------------------------uuid*/

    public static final long LONG_UUID_VALUE_ONE = 4095L;

    public static final long LONG_UUID_VALUE_TWO = 65535L;

    public static final long LONG_UUID_VALUE_THREE = 4611404543450677248L;

    public static final long LONG_UUID_VALUE_FOUR = 281474976710655L;

    /*-----------------------------------------------------------long*/

    public static final long LONG_ZERO = 0L;

    public static final long LONG_ONE = 1L;

    public static final long LONG_THREE = 3L;

    public static final long LONG_FIVE = 5L;

    public static final long LONG_SEVEN = 7L;

    public static final long LONG_FIFTEEN = 15L;

    public static final long LONG_SIXTY_FOUR = 64L;

    public static final long HEXADECIMAL_F = 0xF0000000L;

    public static final long START_TIMESTAMP = 1480166465631L;

    /*-----------------------------------------------------------double*/

    public static final double CLEVER = 10E-5;

    public static final double DOUBLE_HUNDRED = 100.0;

    public static final double PI = Math.PI;

    public static final double E = Math.E;

    public static final double NUMBER_SUPPORT_START = 99999999999999.99;

    public static final double NUMBER_SUPPORT_END = -99999999999999.99;

    /*-----------------------------------------------------------entity*/

    public static final Object NULL = null;

    public static final File FILE_NULL = null;

    /*-----------------------------------------------------------boolean*/

    public static final boolean TRUE = true;

    public static final boolean FALSE = false;

    /*-----------------------------------------------------------char*/

    public static final char DOUBLE_CHAR_SLASH = '\\';

    public static final char CHAR_SPACE = ' ';

    public static final char CHAR_COMMA = ',';

    public static final char CHAR_SLASH_SPOT = '\'';

    public static final char CHAR_CR = '\r';

    public static final char CHAR_CN = '\n';

    public static final char CHAR_CT = '\t';

    public static final char CHAR_YEAR = '年';

    public static final char CHAR_MONTH = '月';

    public static final char CHAR_DAY = '日';

    public static final char CHAR_TIME = '时';

    public static final char CHAR_BRANCH = '分';

    public static final char CHAR_SECOND = '秒';

    public static final char CHAR_CHINA_ZERO = '零';

    public static final char CHAR_NUM_ZERO = '〇';

    public static final char CHAR_DOWN_A = 'a';

    public static final char CHAR_UP_A = 'A';

    public static final char CHAR_UP_W = 'W';

    public static final char CHAR_DOWN_W = 'w';

    public static final char CHAR_UP_D = 'D';

    public static final char CHAR_DOWN_D = 'd';

    public static final char CHAR_DOWN_H = 'h';

    public static final char CHAR_UP_H = 'H';

    public static final char CHAR_UP_S = 'S';

    public static final char CHAR_DOWN_S = 's';

    public static final char CHAR_DOWN_E = 'e';

    public static final char CHAR_UP_E = 'E';

    public static final char CHAR_DOWN_U = 'u';

    public static final char CHAR_DOWN_F = 'f';

    public static final char CHAR_UP_F = 'F';

    public static final char CHAR_UP_M = 'M';

    public static final char CHAR_DOWN_M = 'm';

    public static final char CHAR_UP_G = 'G';

    public static final char CHAR_DOWN_G = 'g';

    public static final char CHAR_DOWN_X = 'x';

    public static final char CHAR_UP_X = 'X';

    public static final char CHAR_UP_K = 'K';

    public static final char CHAR_DOWN_K = 'k';

    public static final char CHAR_DOWN_L = 'l';

    public static final char CHAR_UP_L = 'L';

    public static final char CHAR_UP_Y = 'Y';

    public static final char CHAR_DOWN_Y = 'y';

    public static final char CHAR_DOWN_T = 't';

    public static final char CHAR_UP_T = 'T';

    public static final char CHAR_UP_Z = 'Z';

    public static final char CHAR_DOWN_Z = 'z';

    public static final char CHAR_ZERO = '0';

    public static final char CHAR_NINE = '9';

    public static final char CHAR_DASH = '-';

    public static final char SINGLE_CHAR_SLASH = '/';

    public static final char CHAR_VERTICAL_LINE = '|';

    public static final char CHAR_QUESTION_MARK = '?';

    public static final char CHAR_SPOT = '.';

    public static final char CHAR_PLUS = '+';

    public static final char CHAR_STAR = '*';

    public static final char CHAR_UNDERLINE = '_';

    public static final char CHAR_DOLLAR = '$';

    public static final char CHAR_COLON = ':';

    public static final char CHAR_POWER = '^';

    public static final char CHAR_LEFT_SMALL_BRACKETS = '(';

    public static final char CHAR_RIGHT_SMALL_BRACKETS = ')';

    public static final char CHAR_LEFT_MIDDLE_BRACKETS = '[';

    public static final char CHAR_RIGHT_MIDDLE_BRACKETS = ']';

    public static final char CHAR_LEFT_BIG_BRACKETS = '{';

    public static final char CHAR_RIGHT_BIG_BRACKETS = '}';

    public static final char CHAR_ONE = 0x01;

    public static final char CHAR_TWO = 0x02;

    public static final char CHAR_FOUR = 0x04;

    public static final char CHAR_EIGHT = 0x08;

    public static final char CHAR_TEN = 0x10;

    public static final char CHAR_TWENTY = 0x20;

    public static final char CHAR_FORTY = 0x40;

    public static final char CHAR_EIGHTY = 0x80;

    /*-----------------------------------------------------------String*/

    public static final String STRING_SPACE = " ";

	public static final String STRING_COMMA = ",";

    public static final String STRING_CHINA_ZERO = "零";

    public static final String STRING_DAY = "天";

    public static final String STRING_HOUSE = "小时";

    public static final String STRING_MINUTE = "分";

    public static final String STRING_SECOND = "秒";

    public static final String STRING_MILLISECOND = "毫秒";

    public static final String STRING_ZERO = "0";

    public static final String STRING_ONE = "1";

    public static final String STRING_FOURS_ZERO = "0000";

    public static final String STRING_ZERO_X = "0x";

    public static final String PERCENT_SIGN = "%";

    public static final String PERCENT_SIGN_U = "%u";

    public static final String STRING_S_S = "%s%s";

    public static final String STRING_WELL_WELL = "#.##";

    public static final String EMPTY = "";

    public static final String STRING_COLON = ":";

    public static final String DASH = "-";

    public static final String SPOT = ".";

	public static final String DOUBLE_DOT = "..";

    public static final String BRACKETS = "{}";

    public static final String STRING_PLUS = "+";

    public static final String DOUBLE_BRACKETS = "{}: {}";

    public static final String STRING_DOUBLE_BRACKETS = "{} {}";

    public static final String STRING_S_ONE_TWO = "[S]{1,2}";

    public static final String LEFT_BRACKETS = "{";

    public static final String RIGHT_BRACKETS = "}";

    public static final String STRING_SMALL_LEFT_BRACKETS = "(";

    public static final String STRING_SMALL_RIGHT_BRACKETS = ")";

    public static final String STRING_QUESTION_MARK_I_U = "((?iu)";

    public static final String SINGLE_STRING_SLASH = "/";

    public static final String STRING_VERTICAL_LINE = "|";

    public static final String UTF_8 = "UFT-8";

    public static final String GBK = "GBK";

    public static final String GMT = "GMT";

    public static final String UTC = "UTC";

    public static final String STRING_TRUE = "true";

    public static final String STRING_FALSE = "false";

    public static final String STRING_ON = "on";

    public static final String STRING_OFF = "off";

    public static final String STRING_YES = "yes";

    public static final String STRING_NO = "no";

    public static final String STRING_JA = "ja";

    public static final String STRING_JP = "JP";

    public static final String ENTER = "\r\n";

    public static final String STRING_PATTERN_U = "\\u";

    public static final String STRING_NULL = null;

    public static final String STRING_HOUR_OF_DAY = "HourOfDay";

    public static final String STRING_YEAR_OF_ERA = "YearOfEra";

    public static final String NORM_MONTH_PATTERN = "yyyy-MM";

    public static final String SIMPLE_MONTH_PATTERN = "yyyyMM";

    public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";

    public static final String NORM_TIME_PATTERN = "HH:mm:ss";

    public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";

    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String ISO8601_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";

    public static final String CHINESE_DATE_PATTERN = "yyyy年MM月dd日";

    public static final String CHINESE_DATE_TIME_PATTERN = "yyyy年MM月dd日HH时mm分ss秒";

    public static final String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    public static final String JDK_DATETIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static final String UTC_SIMPLE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String UTC_SIMPLE_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final String UTC_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static final String UTC_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String UTC_MS_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static final String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";

    public static final String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";

    public static final String PURE_DATE_PATTERN = "yyyyMMdd";

    public static final String PURE_DATE_TIME_PATTERN = "yyMMddHHmm";

    public static final String PURE_TIME_PATTERN = "HHmmss";

    public static final String RFC_822_TIME_ZONE =  "[+-]\\d{4}";

    public static final String UTC_TIME_ZONE_WITH_OFFSET = "[+-]\\d{2}:\\d{2}";

    public static final String GMT_OPTION = "GMT[+-]\\d{1,2}:\\d{2}";

    public static final String ISO_8601_1_STRATEGY = "(Z|(?:[+-]\\d{2}))";

    public static final String ISO_8601_2_STRATEGY = "(Z|(?:[+-]\\d{2}\\d{2}))";

    public static final String ISO_8601_3_STRATEGY = "(Z|(?:[+-]\\d{2}(?::)\\d{2}))";

    public static final String STRING_REGULAR_CHINA = "[\u4E00-\u9FFF]";

    public static final String STRING_UP_Z = "Z";

    public static final String STRING_DOLLAR = "$";

    public static final String STRING_WELL_NUMBER = "#";

    public static final String STRING_NULL_OUT = "null";

    public static final String STRING_UNDEFINED = "undefined";

    public static final String STRING_BIG_SPACE = "  ";

	public static final String JAR_FILE_EXT = ".jar";

    /*-----------------------------------------------------------byte*/

    public static final int BYTE_ZERO = 0x0f;

    public static final int ZERO_X_ZERO_ONE = 0x01;

    public static final int ZERO_X_F_ZERO = 0xF0;

    public static final int ZERO_X_F_ZERO_DOWN = 0xf0;

    public static final int ZERO_X_ZERO_F = 0x0F;

    public static final int ZERO_X_F_F = 0xFF;

    public static final int BYTE_ONE =  0x0f0;

    public static final int BYTE_TWO = 0xff;

    public static final int BYTE_THREE = 0xFFFF;

    /*-----------------------------------------------------------IO*/

    public static final BufferedReader BUFFERED_READER_NULL = null;

    public static final BufferedWriter BUFFERED_WRITER_NULL = null;

    public static final Field[] FIELDS_NULL = null;

    public static final Throwable THROWABLE_NULL = null;

    public static final Object[] OBJECTS_ARRAY_NULL = null;

    public static final Type TYPE_NULL = null;

    public static final Type[] TYPES_NULL = null;

    public static final Boolean BOOLEAN_NULL = null;

    public static final Number NUMBER_NULL = null;

    public static final TimeZone TIME_ZONE_NULL = null;

    public static final Locale LOCALE_NULL = null;

    public static final Date DATE_NULL = null;

    public static final Instant INSTANT_NULL = null;

    public static final LocalDateTime LOCAL_DATE_TIME_NULL = null;

    public static final DateTimeFormatter DATE_TIME_FORMATTER_NULL = null;

    public static final LocalDate LOCAL_DATE_NULL = null;

    public static final List<String> LIST_STRING_NULL = null;

    public static final DateTime DATE_TIME_NULL = null;

    public static final Character CHARACTER_NULL = null;

    public static final Integer INTEGER_NULL = null;

    public static final String[] STRINGS_NULL = null;

    public static final Integer[] INTEGERS_NULL = null;

    public static final Long[] LONGS_NULL = null;

    public static final long[] LONGS_DOWN_NULL = null;

    public static final Character[] CHARACTERS_NULL = null;

    public static final char[] CHARS_NULL = null;

    public static final Byte[] BYTES_UP_NULL = null;

    public static final int[] INTS_NULL = null;

    public static final byte[] BYTES_NULL = null;

    public static final Short[] SHORTS_NULL = null;

    public static final short[] SHORTS_DOWN_NULL = null;

    public static final Float[] FLOATS_UP_NULL = null;

    public static final float[] FLOATS_DOWN_NULL = null;

    public static final Double[] DOUBLES_UP_NULL = null;

    public static final double[] DOUBLES_DOWN_NULL = null;

    public static final Boolean[] BOOLEANS_UP_NULL = null;

    public static final boolean[] BOOLEANS_DOWN_NULL = null;

    public static final StringReader STRING_READER_NULL = null;

    public static final Charset CHARSET_NULL = null;

    public static final BigInteger BIG_INTEGER_NULL = null;

    /*-----------------------------------------------------------IO*/

    public static final float FLOAT_ZERO = 0f;

    public static final double DOUBLE_ZERO = 0D;

    /*-----------------------------------------------------------Date*/

    public static final int SUNDAY = Calendar.SUNDAY;

    public static final int MONDAY = Calendar.MONDAY;

    public static final int TUESDAY = Calendar.TUESDAY;

    public static final int WEDNESDAY = Calendar.WEDNESDAY;

    public static final int THURSDAY = Calendar.THURSDAY;

    public static final int FRIDAY = Calendar.FRIDAY;

    public static final int SATURDAY = Calendar.SATURDAY;

    public static final int ERA = Calendar.ERA;

    public static final int YEAR = Calendar.YEAR;

    public static final int MONTH = Calendar.MONTH;

    public static final int WEEK_OF_YEAR = Calendar.WEEK_OF_YEAR;

    public static final int WEEK_OF_MONTH = Calendar.WEEK_OF_MONTH;

    public static final int DAY_OF_MONTH = Calendar.DAY_OF_MONTH;

    public static final int DAY_OF_YEAR = Calendar.DAY_OF_YEAR;

    public static final int DAY_OF_WEEK = Calendar.DAY_OF_WEEK;

    public static final int DAY_OF_WEEK_IN_MONTH = Calendar.DAY_OF_WEEK_IN_MONTH;

    public static final int AM_PM = Calendar.AM_PM;

    public static final int HOUR = Calendar.HOUR;

    public static final int HOUR_OF_DAY = Calendar.HOUR_OF_DAY;

    public static final int MINUTE = Calendar.MINUTE;

    public static final int SECOND = Calendar.SECOND;

    public static final int MILLISECOND = Calendar.MILLISECOND;

    public static final int JANUARY = Calendar.JANUARY;

    public static final int FEBRUARY = Calendar.FEBRUARY;

    public static final int MARCH = Calendar.MARCH;

    public static final int APRIL = Calendar.APRIL;

    public static final int MAY = Calendar.MAY;

    public static final int JUNE = Calendar.JUNE;

    public static final int JULY = Calendar.JULY;

    public static final int AUGUST = Calendar.AUGUST;

    public static final int SEPTEMBER = Calendar.SEPTEMBER;

    public static final int OCTOBER = Calendar.OCTOBER;

    public static final int NOVEMBER = Calendar.NOVEMBER;

    public static final int DECEMBER = Calendar.DECEMBER;

    public static final int UNDECIMBER = Calendar.UNDECIMBER;

    public static final String WEEK_NAME_PRE = "星期";

    public static final String WEEK_SUNDAY = "日";

    public static final String WEEK_ONE = "一";

    public static final String WEEK_TWO = "二";

    public static final String WEEK_THREE = "三";

    public static final String WEEK_FOUR = "四";

    public static final String WEEK_FIVE = "五";

    public static final String WEEK_SIX = "六";

    public static final Float FLOAT_DOUBLE_ZERO  = 0.0f;

    public static final Float FLOAT_DOUBLE_NEGATIVE_ZERO  = -0.0f;

    public static final Double DOUBLE_DOUBLE_ZERO = 0.0d;

    public static final Double DOUBLE_DOUBLE_NEGATIVE_ZERO = 0.0d;
}
