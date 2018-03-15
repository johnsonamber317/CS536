import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the C-- scanner.
 * This version is set up to test all tokens, but more code is needed to test 
 * other aspects of the scanner (e.g., input that causes errors, character 
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {
                                           // exception may be thrown by yylex
        // test all tokens
        testAllTokens();
        testGoodStrings();
        testBad1();
        testBad2();
        testBad3();
        testBad4();
        testSymbols();
        testId1();
        testId2();
        testId3();
        testGoodInt();
        testBadInt();
        testReserved();
        testComments();
        testWhitespace();
        testIllegal();
        CharNum.num = 1;
        //testCharNum(CharNum.num);
    
        // ADD CALLS TO OTHER TEST METHODS HERE
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool"); 
                break;
			case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true"); 
                break;
            case sym.FALSE:
                outFile.println("false"); 
                break;
            case sym.STRUCT:
                outFile.println("struct"); 
                break;
            case sym.CIN:
                outFile.println("cin"); 
                break;
            case sym.COUT:
                outFile.println("cout");
                break;				
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:  
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL: 
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;    
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;				
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;	
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
			case sym.ASSIGN:
                outFile.println("=");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    private static void testGoodStrings() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("goodStrings.in");
            outFile = new PrintWriter(new FileWriter("goodStrings.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File goodStrings.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("goodStrings.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    private static void testBad1() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("bad1.in");
            outFile = new PrintWriter(new FileWriter("bad1.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File bad1.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("bad1.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }

    private static void testBad2() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("bad2.in");
            outFile = new PrintWriter(new FileWriter("bad2.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File bad2.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("bad2.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }

    private static void testBad3() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("bad3.in");
            outFile = new PrintWriter(new FileWriter("bad3.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File bad3.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("bad3.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testBad4() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("bad4.in");
            outFile = new PrintWriter(new FileWriter("bad4.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File bad4.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("bad4.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testSymbols() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("symbols.in");
            outFile = new PrintWriter(new FileWriter("symbols.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File symbols.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("symbols.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }

    private static void testId1() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("id1.in");
            outFile = new PrintWriter(new FileWriter("id1.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File id1.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("id1.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testId2() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("id2.in");
            outFile = new PrintWriter(new FileWriter("id2.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File id2.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("id2.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testId3() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("id3.in");
            outFile = new PrintWriter(new FileWriter("id3.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File id3.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("id3.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testGoodInt() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("goodint.in");
            outFile = new PrintWriter(new FileWriter("goodint.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File goodint.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("goodint.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testBadInt() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("badint.in");
            outFile = new PrintWriter(new FileWriter("badint.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File badint.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("badint.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }

    private static void testReserved() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("reservedWords.in");
            outFile = new PrintWriter(new FileWriter("reservedWords.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File reservedWords.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("reservedWords.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testComments() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("comments.in");
            outFile = new PrintWriter(new FileWriter("comments.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File comments.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("comments.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testWhitespace() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("whitespace.in");
            outFile = new PrintWriter(new FileWriter("whitespace.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File whitespace.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("whitespace.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testIllegal() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("illegal.in");
            outFile = new PrintWriter(new FileWriter("illegal.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File illegal.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("illegal.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }
    
    private static void testLine() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("testLine.in");
            outFile = new PrintWriter(new FileWriter("testLine.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("testLine.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }

    private static void testCharNum(int CharNum) throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("testCharNum.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("testCharNum.out cannot be opened.");
            System.exit(-1);
        }
        
        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
                case sym.BOOL:
                    outFile.println("bool");
                    //System.out.println(CharNum.num);
                    break;
                case sym.INT:
                    outFile.println("int");
                    break;
                case sym.VOID:
                    outFile.println("void");
                    break;
                case sym.TRUE:
                    outFile.println("true");
                    break;
                case sym.FALSE:
                    outFile.println("false");
                    break;
                case sym.STRUCT:
                    outFile.println("struct");
                    break;
                case sym.CIN:
                    outFile.println("cin");
                    break;
                case sym.COUT:
                    outFile.println("cout");
                    break;
                case sym.IF:
                    outFile.println("if");
                    break;
                case sym.ELSE:
                    outFile.println("else");
                    break;
                case sym.WHILE:
                    outFile.println("while");
                    break;
                case sym.RETURN:
                    outFile.println("return");
                    break;
                case sym.ID:
                    outFile.println(((IdTokenVal)token.value).idVal);
                    break;
                case sym.INTLITERAL:
                    outFile.println(((IntLitTokenVal)token.value).intVal);
                    break;
                case sym.STRINGLITERAL:
                    outFile.println(((StrLitTokenVal)token.value).strVal);
                    break;
                case sym.LCURLY:
                    outFile.println("{");
                    break;
                case sym.RCURLY:
                    outFile.println("}");
                    break;
                case sym.LPAREN:
                    outFile.println("(");
                    break;
                case sym.RPAREN:
                    outFile.println(")");
                    break;
                case sym.SEMICOLON:
                    outFile.println(";");
                    break;
                case sym.COMMA:
                    outFile.println(",");
                    break;
                case sym.DOT:
                    outFile.println(".");
                    break;
                case sym.WRITE:
                    outFile.println("<<");
                    break;
                case sym.READ:
                    outFile.println(">>");
                    break;
                case sym.PLUSPLUS:
                    outFile.println("++");
                    break;
                case sym.MINUSMINUS:
                    outFile.println("--");
                    break;
                case sym.PLUS:
                    outFile.println("+");
                    break;
                case sym.MINUS:
                    outFile.println("-");
                    break;
                case sym.TIMES:
                    outFile.println("*");
                    break;
                case sym.DIVIDE:
                    outFile.println("/");
                    break;
                case sym.NOT:
                    outFile.println("!");
                    break;
                case sym.AND:
                    outFile.println("&&");
                    break;
                case sym.OR:
                    outFile.println("||");
                    break;
                case sym.EQUALS:
                    outFile.println("==");
                    break;
                case sym.NOTEQUALS:
                    outFile.println("!=");
                    break;
                case sym.LESS:
                    outFile.println("<");
                    break;
                case sym.GREATER:
                    outFile.println(">");
                    break;
                case sym.LESSEQ:
                    outFile.println("<=");
                    break;
                case sym.GREATEREQ:
                    outFile.println(">=");
                    break;
                case sym.ASSIGN:
                    outFile.println("=");
                    break;
                default:
                    outFile.println("UNKNOWN TOKEN");
            } // end switch
            
            token = scanner.next_token();
        } // end while
        outFile.close();
    }

    
}
