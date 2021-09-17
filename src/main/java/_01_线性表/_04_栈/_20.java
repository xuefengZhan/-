package _01_线性表._04_栈;

import java.util.LinkedList;

public class _20 {
    public boolean isValid(String s) {
        if(s.length() == 0 || s.length() % 2 == 1){
            return false;
        }
        LinkedList<Character> stack = new LinkedList();
        char[] chars = s.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(isLeft(chars[i])){
                stack.push(chars[i]);
            }else{
                if(stack.isEmpty()) return false;
                if(isMatch(stack.peek(),chars[i])){
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();

    }

    private boolean isLeft(char c){
        return c == '[' || c =='(' || c=='{';
    }
    private boolean isMatch(char c1,char c2){
        return (c1=='[' && c2==']') || (c1=='(' && c2==')') || (c1=='{' && c2=='}') ;
    }
}
