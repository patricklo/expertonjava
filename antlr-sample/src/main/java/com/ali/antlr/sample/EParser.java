// $ANTLR 3.5.2 E.g 2016-10-07 15:51:21
package com.ali.antlr.sample;
import org.antlr.runtime.*;

import org.antlr.runtime.tree.*;


@SuppressWarnings("all")
public class EParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "INT", "STRING", "VAR", "WS", 
		"'('", "')'", "'*'", "'+'", "'-'", "';'", "'='"
	};
	public static final int EOF=-1;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int INT=4;
	public static final int STRING=5;
	public static final int VAR=6;
	public static final int WS=7;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public EParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public EParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return EParser.tokenNames; }
	@Override public String getGrammarFileName() { return "E.g"; }


	public static class program_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "program"
	// E.g:6:1: program : ( statement )+ ;
	public final EParser.program_return program() throws RecognitionException {
		EParser.program_return retval = new EParser.program_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		ParserRuleReturnScope statement1 =null;


		try {
			// E.g:6:9: ( ( statement )+ )
			// E.g:6:11: ( statement )+
			{
			root_0 = (Object)adaptor.nil();


			// E.g:6:11: ( statement )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= INT && LA1_0 <= VAR)||LA1_0==8) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// E.g:6:11: statement
					{
					pushFollow(FOLLOW_statement_in_program23);
					statement1=statement();
					state._fsp--;

					adaptor.addChild(root_0, statement1.getTree());

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "program"


	public static class statement_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "statement"
	// E.g:7:1: statement : ( expression | VAR '=' expression ) ';' ;
	public final EParser.statement_return statement() throws RecognitionException {
		EParser.statement_return retval = new EParser.statement_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token VAR3=null;
		Token char_literal4=null;
		Token char_literal6=null;
		ParserRuleReturnScope expression2 =null;
		ParserRuleReturnScope expression5 =null;

		Object VAR3_tree=null;
		Object char_literal4_tree=null;
		Object char_literal6_tree=null;

		try {
			// E.g:7:10: ( ( expression | VAR '=' expression ) ';' )
			// E.g:7:12: ( expression | VAR '=' expression ) ';'
			{
			root_0 = (Object)adaptor.nil();


			// E.g:7:12: ( expression | VAR '=' expression )
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( ((LA2_0 >= INT && LA2_0 <= STRING)||LA2_0==8) ) {
				alt2=1;
			}
			else if ( (LA2_0==VAR) ) {
				alt2=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}

			switch (alt2) {
				case 1 :
					// E.g:7:13: expression
					{
					pushFollow(FOLLOW_expression_in_statement33);
					expression2=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression2.getTree());

					}
					break;
				case 2 :
					// E.g:7:26: VAR '=' expression
					{
					VAR3=(Token)match(input,VAR,FOLLOW_VAR_in_statement37); 
					VAR3_tree = (Object)adaptor.create(VAR3);
					adaptor.addChild(root_0, VAR3_tree);

					char_literal4=(Token)match(input,14,FOLLOW_14_in_statement39); 
					char_literal4_tree = (Object)adaptor.create(char_literal4);
					adaptor.addChild(root_0, char_literal4_tree);

					pushFollow(FOLLOW_expression_in_statement41);
					expression5=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression5.getTree());

					}
					break;

			}

			char_literal6=(Token)match(input,13,FOLLOW_13_in_statement44); 
			char_literal6_tree = (Object)adaptor.create(char_literal6);
			adaptor.addChild(root_0, char_literal6_tree);

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "statement"


	public static class expression_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "expression"
	// E.g:8:1: expression : ( ( multExpr ( ( '+' | '-' ) multExpr )* ) | STRING );
	public final EParser.expression_return expression() throws RecognitionException {
		EParser.expression_return retval = new EParser.expression_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token set8=null;
		Token STRING10=null;
		ParserRuleReturnScope multExpr7 =null;
		ParserRuleReturnScope multExpr9 =null;

		Object set8_tree=null;
		Object STRING10_tree=null;

		try {
			// E.g:8:12: ( ( multExpr ( ( '+' | '-' ) multExpr )* ) | STRING )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( (LA4_0==INT||LA4_0==8) ) {
				alt4=1;
			}
			else if ( (LA4_0==STRING) ) {
				alt4=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// E.g:8:14: ( multExpr ( ( '+' | '-' ) multExpr )* )
					{
					root_0 = (Object)adaptor.nil();


					// E.g:8:14: ( multExpr ( ( '+' | '-' ) multExpr )* )
					// E.g:8:15: multExpr ( ( '+' | '-' ) multExpr )*
					{
					pushFollow(FOLLOW_multExpr_in_expression53);
					multExpr7=multExpr();
					state._fsp--;

					adaptor.addChild(root_0, multExpr7.getTree());

					// E.g:8:24: ( ( '+' | '-' ) multExpr )*
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( ((LA3_0 >= 11 && LA3_0 <= 12)) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// E.g:8:25: ( '+' | '-' ) multExpr
							{
							set8=input.LT(1);
							if ( (input.LA(1) >= 11 && input.LA(1) <= 12) ) {
								input.consume();
								adaptor.addChild(root_0, (Object)adaptor.create(set8));
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}
							pushFollow(FOLLOW_multExpr_in_expression64);
							multExpr9=multExpr();
							state._fsp--;

							adaptor.addChild(root_0, multExpr9.getTree());

							}
							break;

						default :
							break loop3;
						}
					}

					}

					}
					break;
				case 2 :
					// E.g:8:51: STRING
					{
					root_0 = (Object)adaptor.nil();


					STRING10=(Token)match(input,STRING,FOLLOW_STRING_in_expression71); 
					STRING10_tree = (Object)adaptor.create(STRING10);
					adaptor.addChild(root_0, STRING10_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expression"


	public static class multExpr_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "multExpr"
	// E.g:9:1: multExpr : atom ( '*' atom )* ;
	public final EParser.multExpr_return multExpr() throws RecognitionException {
		EParser.multExpr_return retval = new EParser.multExpr_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token char_literal12=null;
		ParserRuleReturnScope atom11 =null;
		ParserRuleReturnScope atom13 =null;

		Object char_literal12_tree=null;

		try {
			// E.g:9:10: ( atom ( '*' atom )* )
			// E.g:9:12: atom ( '*' atom )*
			{
			root_0 = (Object)adaptor.nil();


			pushFollow(FOLLOW_atom_in_multExpr78);
			atom11=atom();
			state._fsp--;

			adaptor.addChild(root_0, atom11.getTree());

			// E.g:9:17: ( '*' atom )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==10) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// E.g:9:18: '*' atom
					{
					char_literal12=(Token)match(input,10,FOLLOW_10_in_multExpr81); 
					char_literal12_tree = (Object)adaptor.create(char_literal12);
					adaptor.addChild(root_0, char_literal12_tree);

					pushFollow(FOLLOW_atom_in_multExpr83);
					atom13=atom();
					state._fsp--;

					adaptor.addChild(root_0, atom13.getTree());

					}
					break;

				default :
					break loop5;
				}
			}

			}

			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multExpr"


	public static class atom_return extends ParserRuleReturnScope {
		Object tree;
		@Override
		public Object getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// E.g:10:1: atom : ( INT | '(' expression ')' );
	public final EParser.atom_return atom() throws RecognitionException {
		EParser.atom_return retval = new EParser.atom_return();
		retval.start = input.LT(1);

		Object root_0 = null;

		Token INT14=null;
		Token char_literal15=null;
		Token char_literal17=null;
		ParserRuleReturnScope expression16 =null;

		Object INT14_tree=null;
		Object char_literal15_tree=null;
		Object char_literal17_tree=null;

		try {
			// E.g:10:6: ( INT | '(' expression ')' )
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==INT) ) {
				alt6=1;
			}
			else if ( (LA6_0==8) ) {
				alt6=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 6, 0, input);
				throw nvae;
			}

			switch (alt6) {
				case 1 :
					// E.g:10:8: INT
					{
					root_0 = (Object)adaptor.nil();


					INT14=(Token)match(input,INT,FOLLOW_INT_in_atom92); 
					INT14_tree = (Object)adaptor.create(INT14);
					adaptor.addChild(root_0, INT14_tree);

					}
					break;
				case 2 :
					// E.g:10:14: '(' expression ')'
					{
					root_0 = (Object)adaptor.nil();


					char_literal15=(Token)match(input,8,FOLLOW_8_in_atom96); 
					char_literal15_tree = (Object)adaptor.create(char_literal15);
					adaptor.addChild(root_0, char_literal15_tree);

					pushFollow(FOLLOW_expression_in_atom98);
					expression16=expression();
					state._fsp--;

					adaptor.addChild(root_0, expression16.getTree());

					char_literal17=(Token)match(input,9,FOLLOW_9_in_atom100); 
					char_literal17_tree = (Object)adaptor.create(char_literal17);
					adaptor.addChild(root_0, char_literal17_tree);

					}
					break;

			}
			retval.stop = input.LT(-1);

			retval.tree = (Object)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
			retval.tree = (Object)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"

	// Delegated rules



	public static final BitSet FOLLOW_statement_in_program23 = new BitSet(new long[]{0x0000000000000172L});
	public static final BitSet FOLLOW_expression_in_statement33 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_VAR_in_statement37 = new BitSet(new long[]{0x0000000000004000L});
	public static final BitSet FOLLOW_14_in_statement39 = new BitSet(new long[]{0x0000000000000130L});
	public static final BitSet FOLLOW_expression_in_statement41 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_statement44 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_expression53 = new BitSet(new long[]{0x0000000000001802L});
	public static final BitSet FOLLOW_set_in_expression56 = new BitSet(new long[]{0x0000000000000110L});
	public static final BitSet FOLLOW_multExpr_in_expression64 = new BitSet(new long[]{0x0000000000001802L});
	public static final BitSet FOLLOW_STRING_in_expression71 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atom_in_multExpr78 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_10_in_multExpr81 = new BitSet(new long[]{0x0000000000000110L});
	public static final BitSet FOLLOW_atom_in_multExpr83 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_INT_in_atom92 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_8_in_atom96 = new BitSet(new long[]{0x0000000000000130L});
	public static final BitSet FOLLOW_expression_in_atom98 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_atom100 = new BitSet(new long[]{0x0000000000000002L});
}
