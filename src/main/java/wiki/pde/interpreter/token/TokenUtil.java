package wiki.pde.interpreter.token;

public class TokenUtil {
    public static boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z' || ch == '_';
    }

    public static boolean isDigit(char ch) {
        return '0' <= ch && ch <= '9';
    }

    public static boolean isWhitespace(char ch) {
        return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r';
    }

    // keywords
    public static TokenEnum lookupIdent(String ident) {
        return switch (ident) {
            case "fn" -> TokenEnum.FUNCTION;
            case "let" -> TokenEnum.LET;
            case "return" -> TokenEnum.RETURN;
            case "if" -> TokenEnum.IF;
            case "else" -> TokenEnum.ELSE;
            default -> TokenEnum.IDENT;
        };
    }

    public static boolean isKeyword(String ident) {
        return switch (ident) {
            case "fn", "let", "return", "if", "else" -> true;
            default -> false;
        };
    }
}
