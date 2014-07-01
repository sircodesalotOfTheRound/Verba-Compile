package main.java.verba.language.lexing;

import main.java.verba.language.exceptions.ParseException;
import main.java.verba.language.lexing.codestream.CodeStream;
import main.java.verba.language.lexing.info.LexInfo;
import main.java.verba.language.lexing.info.LexList;
import main.java.verba.language.lexing.tokenization.Token;
import main.java.verba.language.lexing.tokenization.VerbaLexicalTokenizer;
import main.java.verba.language.lexing.tokens.ignorable.LineCommentToken;
import main.java.verba.language.lexing.tokens.ignorable.WhitespaceToken;

import java.util.*;

/**
 * Created by sircodesalot on 14-2-20.
 */
public class VerbaMemoizingLexer implements Lexer {
    private final String filename;
    private int index = 0;
    private int fileLength;
    private List<LexInfo> tokens;
    private Stack<Integer> undopoints;

    public VerbaMemoizingLexer(String filename, CodeStream stream) {
        this.filename = filename;
        this.tokens = buildList(stream, false, false);
        this.fileLength = stream.getFileLength();
        this.undopoints = new Stack<Integer>();
    }

    public VerbaMemoizingLexer(String filename, CodeStream stream, boolean includeWhitespaces, boolean includeComments) {
        this.filename = filename;
        this.tokens = buildList(stream, includeWhitespaces, includeComments);
        this.fileLength = stream.getFileLength();
        this.undopoints = new Stack<Integer>();
    }

    private List<LexInfo> buildList(CodeStream stream, boolean includeWhitespaces, boolean includeComments) {
        List<LexInfo> tokens = new ArrayList<>();
        LexicalTokenizerBase lexer = new VerbaLexicalTokenizer(this.filename, stream);
        for (LexInfo token : lexer) {
            boolean isWhitespace = token.is(WhitespaceToken.class);
            boolean isComment = token.is(LineCommentToken.class);

            if (isWhitespace && includeWhitespaces) {
                tokens.add(token);
            } else if (isComment && includeComments) {
                tokens.add(token);
            } else if (!isComment && !isWhitespace) tokens.add(token);
        }
        return tokens;
    }

    public boolean isEOF() {
        return !(this.index < tokens.size());
    }

    public boolean notEOF() {
        return this.index < tokens.size();
    }

    public boolean hasNext() {
        return (this.index + 1) < tokens.size();
    }

    public LexInfo next() {
        return tokens.get(++this.index);
    }

    public void advance() {
        this.index++;
    }

    public void advance(int count) {
        this.index += count;
    }

    public LexInfo current() {
        return this.tokens.get(this.index);
    }

    public LexInfo previous() {
        return this.tokens.get(--this.index);
    }

    public LexInfo readCurrentAndAdvance() {
        LexInfo current = this.current();
        this.advance();

        return current;
    }

    public <T extends Token> LexInfo readCurrentAndAdvance(Class<T> type) {
        LexInfo current = this.current(type);
        this.advance();

        return current;
    }

    public <T extends Token> LexInfo readCurrentAndAdvance(Class<T> type, String representation) {
        LexInfo current = this.current(type, representation);
        this.advance();

        return current;
    }


    public LexList peek(int count) {
        this.setUndoPoint();
        LexList list = this.read(count);
        this.rollbackToUndoPoint();

        return list;
    }

    public LexList read(int count) {
        LexList list = new LexList();
        for (int index = 0; index < count; index++) {
            list.add(this.current());
            if (this.hasNext() == false) break;
            this.next();
        }

        return list;
    }

    @Override
    public LexList readToEndOfLine() {
        LexList result = new LexList();
        LexInfo current = this.current();
        int currentLine = current.line();
        result.add(current);

        while (this.hasNext() && (this.next().line() == currentLine)) {
            result.add(this.current());
        }

        return result;
    }

    @Override
    public <T extends Token> LexList readToEndOfLineOr(Class<T> type, String representation) {
        LexList result = new LexList();
        LexInfo current = this.current();
        int currentLine = current.line();
        result.add(current);

        while (this.hasNext()
            && (this.currentIs(type, representation) == false)
            && (this.next().line() == currentLine)) {
            result.add(this.current());
        }

        return result;
    }

    @Override
    public LexList peekToEndOfLine() {
        this.setUndoPoint();
        LexList result = this.readToEndOfLine();
        this.rollbackToUndoPoint();

        return result;
    }

    @Override
    public <T extends Token> LexList peekToEndOfLineOr(Class<T> type, String representation) {
        this.setUndoPoint();
        LexList result = this.readToEndOfLineOr(type, representation);
        this.rollbackToUndoPoint();

        return result;
    }

    public <T extends Token> LexInfo readNext(Class<T> type) {
        LexInfo next = this.next();
        if (next.is(type)) return next;
        else throw new ParseException("Expected %s, found %s", type, next);
    }

    public <T extends Token> LexInfo readNext(Class<T> type, String representation) {
        LexInfo next = this.next();
        if (next.is(type, representation)) return next;
        else throw new ParseException("Expected %s, found %s", type, next);
    }

    public <T extends Token> LexInfo current(Class<T> type) {
        LexInfo current = this.current();
        if (current.is(type)) return current;
        else throw new ParseException("Expected %s, found %s", type, current);
    }

    public <T extends Token> LexInfo current(Class<T> type, String representation) {
        LexInfo current = this.current();
        if (current.is(type, representation)) return current;
        else throw new ParseException("Expected %s, found %s", type, current);
    }


    @Override
    public <T extends Token> boolean nextIs(Class<T> type, String representation) {
        return this.hasNext() && this.next().is(type, representation);
    }

    @Override
    public <T extends Token> boolean nextIs(Class<T> type) {
        return this.hasNext() && this.next().is(type);
    }

    @Override
    public <T extends Token> boolean currentIs(Class<T> type, String representation) {
        return this.notEOF() && this.current().is(type, representation);
    }

    @Override
    public <T extends Token> boolean currentIs(Class<T> type) {
        return (this.notEOF() && type.isAssignableFrom(this.current().type()));
    }

    public void setUndoPoint() {
        this.undopoints.push(this.index);
    }

    public void clearUndoPoint() {
        this.undopoints.pop();
    }

    public void rollbackToUndoPoint() {
        this.index = this.undopoints.pop();
    }

    public LexList readUpTo(String... symbols) {
        LexList resultSet = new LexList();
        Set<String> tokenSetToMatch = new HashSet<String>();
        Collections.addAll(tokenSetToMatch, symbols);

        while (this.hasNext()) {
            resultSet.add(this.current());
            if (tokenSetToMatch.contains(this.current().representation())) break;
            this.next();
        }

        return resultSet;
    }

    @Override
    public LexList peekUpTo(String... symbols) {
        this.setUndoPoint();
        LexList result = this.readUpTo(symbols);
        this.rollbackToUndoPoint();

        return result;
    }

    @Override
    public <T extends Token> LexList peekUpTo(Class<T>... symbols) {
        this.setUndoPoint();
        LexList result = this.readUpTo(symbols);
        this.rollbackToUndoPoint();

        return result;
    }

    @Override
    public <T extends Token> LexList peekUpTo(Class<T> type, String representation) {
        this.setUndoPoint();
        LexList result = this.readUpTo(type, representation);
        this.rollbackToUndoPoint();

        return result;
    }

    public <T extends Token> LexList readUpTo(Class<T>... symbols) {
        LexList resultSet = new LexList();
        Set<Class<T>> tokenSetToMatch = new HashSet<Class<T>>();
        for (Class<T> symbol : symbols) tokenSetToMatch.add(symbol);

        while (this.hasNext()) {
            resultSet.add(this.current());
            if (tokenSetToMatch.contains(this.current().type())) break;

            this.next();
        }

        return resultSet;
    }

    public <T extends Token> LexList readUpTo(Class<T> type, String representation) {
        LexList resultSet = new LexList();
        while (this.hasNext()) {
            resultSet.add(this.current());
            if (this.current().representation().equals(representation) &&
                (type.isAssignableFrom(this.current().type()))) break;

            this.next();
        }

        return resultSet;
    }

    public void moveToFirst() {
        this.index = 0;
    }

    public int size() {
        return this.tokens.size();
    }

    @Override
    public int getCurrentLine() {
        return this.current().line();
    }

    @Override
    public Iterator<LexInfo> iterator() {
        return this.tokens.iterator();
    }

    @Override
    public int fileLength() {
        return this.fileLength;
    }

    @Override
    public String filename() {
        return this.filename;
    }
}
