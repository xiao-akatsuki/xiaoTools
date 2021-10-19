package com.xiaoTools.core.eval;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xiaoTools.core.eval.operator.Operator;

public class Eval {
	// 各个符号的常量, 匹配的符号映射, 各个符号的优先级
    private final static char ADD = '+';
    private final static char SUB = '-';
    private final static char MUL = '*';
    private final static char DIV = '/';
    private final static char MOD = '%';
    private final static char LEFT_BRACKET = '(';
    private final static char RIGHT_BRACKET = ')';
    private final static Map<Character, Character> matched = new HashMap<>();
    private static Map<Character, Integer> priority = new HashMap<>();

    // 初始化
    static {
        matched.put(LEFT_BRACKET, RIGHT_BRACKET);

        priority.put(ADD, 0);
        priority.put(SUB, 0);
        priority.put(MUL, 1);
        priority.put(DIV, 1);
        priority.put(MOD, 1);
        priority.put(LEFT_BRACKET, 2);
        priority.put(RIGHT_BRACKET, 2);
    }


	public static int eval(String formula) {
		formula = formula.replace(" ", "");
        List<Integer> resInBrackets = new LinkedList<>();
        List<Operator> optr = new LinkedList<Operator>();
        for(int i=0; i<formula.length(); i++) {
            char c = formula.charAt(i);
            switch (c ) {
                case ADD :
                case SUB :
                case MUL :
                case DIV :
                case MOD :
                    optr.add(new Operator(c, i));
                    break;
                case LEFT_BRACKET :
                    int nextI = getNextMatched(formula, i+1, LEFT_BRACKET);
                    resInBrackets.add(eval(formula.substring(i+1, nextI)) );
                    optr.add(new Operator(LEFT_BRACKET, i) );
                    optr.add(new Operator(RIGHT_BRACKET, nextI) );
                    i = nextI;
                default :
                    break;
            }
        }

        int val = eval0(formula, optr, resInBrackets, null);


        return val;
    }

	private static int eval0(String formula, List<Operator> optr, List<Integer> resInBrackets, Operator lastOptNow) {
        int res = 0;
        if(lastOptNow == null) {
            Operator firstOpt = optr.get(0);
            if(firstOpt.getOper() == LEFT_BRACKET) {
                optr.remove(0);
                optr.remove(0);
                res = resInBrackets.remove(0);
            } else {
                res = parseFirstInt(formula, firstOpt );
            }
        } else {
            res = parseInt(formula, lastOptNow, optr.get(0) );
        }

        while(optr.size() > 1) {
            Operator optNow = optr.get(0);
            Operator optNext = optr.get(1);
            optr.remove(0);
            if(priority.get(optNext.getOper()) > priority.get(optNow.getOper()) ) {
                if(optNext.getOper() == LEFT_BRACKET) {
                    Operator optNNext = null;
                    if(optr.size() > 2) {
                        optNNext = optr.get(2);
                    }
                    if((optNNext != null) && (priority.get(optNNext.getOper()) > priority.get(optNow.getOper())) ) {
                        return calc(res, optNow, eval0(formula, optr, resInBrackets, null) );
                    } else {
                        optr.remove(0);
                        optr.remove(0);
                        res = calc(res, optNow, resInBrackets.remove(0) );
                    }
                } else {
                    return calc(res, optNow, eval0(formula, optr, resInBrackets, optNow) );
                }
            } else {
                res = calc(res, optNow, parseInt(formula, optNow, optNext) );
            }
        }

        if(optr.size() > 0) {
            res = calc(res, optr.get(0), parseLastInt(formula, optr.get(0)) );
        }

        return res;
    }

	private static int calc(int val01, Operator opt, int val02) {
        int val = 0;
        switch(opt.getOper()) {
            case ADD :
                val = val01 + val02;
                break;
            case SUB :
                val = val01 - val02;
                break;
            case MUL :
                val = val01 * val02;
                break;
            case DIV :
                val = val01 / val02;
                break;
            case MOD :
                val = val01 % val02;
                break;
        }

        return val;
    }

	private static int parseInt(String formula, Operator optNow, Operator optNext) {
        String intStr = null;
        try {
            intStr = formula.substring(optNow.getIndex()+1, optNext.getIndex()).trim();
            return Integer.parseInt(intStr);
        } catch (Exception e) {
            throw e;
        }
    }
    private static int parseLastInt(String exp, Operator optNow) {
        try {
            return Integer.parseInt(exp.substring(optNow.getIndex()+1).trim() );
        } catch(Exception e) {
            throw e;
        }
    }
    private static int parseFirstInt(String exp, Operator optNow) {
        try {
            return Integer.parseInt(exp.substring(0, optNow.getIndex()).trim() );
        } catch(Exception e) {
			throw e;
        }
    }

	private static int getNextMatched(String formula, int idx, char left) {
        Deque<Character> stack = new LinkedList<>();
        stack.push(left);
        for(int i=idx; i<formula.length(); i++) {
            char ch = formula.charAt(i);
            if(matched.containsKey(ch) ) {
                stack.push(ch);
            }
            if(ch == matched.get(stack.peek()) ) {
                stack.pop();
                if(stack.size() == 0) {
                    idx = i;
                    break ;
                }
            }
        }

        return idx;
    }

}
