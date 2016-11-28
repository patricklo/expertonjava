package com.ali.antlr3.sample;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

/**
 * Created by kamwa.lkw on 2016/10/7.
 */
public class AntlrDemo {
    public static void main(String[] args) throws Exception  {
        String formula="2+3*5;str=\"hello\";";
        ANTLRStringStream input = new ANTLRStringStream(formula);
        ELexer lexer = new ELexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        EParser parser = new EParser(tokens);
        EParser.program_return r = parser.program();

        //((BaseTree)r.getTree().
        System.out.println(((BaseTree)r.getTree()).toStringTree());
    }
}
