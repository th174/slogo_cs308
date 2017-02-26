package SLogo.Parse;

import java.io.IOException;

/**
 * Created by th174 on 2/16/2017.
 */
public interface Parser {
    /**
     *
     * @param command User written command
     * @return Expression built from command
     */
    RecursiveExpression parse(String command);

    /**
     * @param locale Desired Locale of commands
     * @throws IOException If language is not supported
     */
    void setLocale(String locale) throws IOException;
}
