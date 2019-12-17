package com.codenation;

import com.codenation.entity.Log;
import com.codenation.enums.Environment;
import com.codenation.enums.Level;

public class LogsForTests {

    public static Log A() {
        Log result = new Log();

        result.setTitle("Título A");
        result.setDetail("Detalhe A");
        result.setOrigin("127.0.0.1");
        result.setGeneratedBy("email@email.com");
        result.setEnvironment(Environment.DEVELOPMENT);
        result.setToken("XXXX-XXXX-XXXX-XXXX");
        result.setLevel(Level.DEBUG);

        return result;
    }

    public static Log B() {
        Log result = new Log();

        result.setTitle("Título B");
        result.setDetail("Detalhe B");
        result.setOrigin("127.0.0.2");
        result.setGeneratedBy("email@email.com");
        result.setEnvironment(Environment.PRODUCTION);
        result.setToken("XXXX-XXXX-XXXX-XXX0");
        result.setLevel(Level.WARNING);

        return result;
    }

    public static Log C() {
        Log result = new Log();

        result.setTitle("Título C");
        result.setDetail("Detalhe C");
        result.setOrigin("127.0.0.3");
        result.setGeneratedBy("email2@email.com");
        result.setEnvironment(Environment.STAGE);
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.ERROR);

        return result;
    }

    public static Log D() {
        Log result = new Log();

        result.setTitle("Título D");
        result.setDetail("Detalhe D");
        result.setOrigin("127.0.0.15");
        result.setGeneratedBy("email@email.com");
        result.setEnvironment(Environment.DEVELOPMENT);
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.DEBUG);

        return result;
    }

    public static Log E() {
        Log result = new Log();

        result.setTitle("Título E");
        result.setDetail("Detalhe E");
        result.setOrigin("127.0.0.3");
        result.setGeneratedBy("email3@email.com");
        result.setEnvironment(Environment.STAGE);
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.ERROR);

        return result;
    }


    public static Log F() {
        Log result = new Log();

        result.setTitle("Título F");
        result.setDetail("Detalhe D");
        result.setOrigin("127.0.0.6");
        result.setGeneratedBy("email3@email");
        result.setEnvironment(Environment.PRODUCTION);
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.DEBUG);

        return result;
    }


    public static Log G() {
        Log result = new Log();

        result.setTitle("Título G");
        result.setDetail("Detalhe D");
        result.setOrigin("127.0.0.10");
        result.setGeneratedBy("email4@email.com");
        result.setEnvironment(Environment.STAGE);
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.WARNING);

        return result;
    }

    //CASE INSENSITIVE
    public static Log H() {
        Log result = new Log();

        result.setTitle("Título H");
        result.setDetail("CasEInSenSiTiVe");
        result.setOrigin("");
        result.setGeneratedBy("admin4@email.com");
        result.setEnvironment(Environment.PRODUCTION);
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.WARNING);

        return result;
    }

    // SEM TITLE
    public static Log I() {
        Log result = new Log();

        result.setTitle("Título I");
        result.setDetail("Detalhe D");
        result.setOrigin("127.0.0.1");
        result.setGeneratedBy("email5@email.com");
        result.setEnvironment(Environment.STAGE);
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.DEBUG);

        return result;
    }

    //SEM DETAIL
    public static Log J() {
        Log result = new Log();

        result.setTitle("Título J");
        result.setDetail("Detalhe D");
        result.setOrigin("127.0.0.1");
        result.setGeneratedBy("email6@email.com");
        result.setEnvironment(Environment.STAGE);
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.ERROR);

        return result;
    }

    //SEM ENV
    public static Log K() {
        Log result = new Log();

        result.setTitle("Título K");
        result.setDetail("Detalhe D");
        result.setOrigin("127.0.0.1");
        result.setGeneratedBy("email@email.com");
        result.setToken("XXXX-XXXX-XXXX-XXXY");
        result.setLevel(Level.DEBUG);
        result.setEnvironment(Environment.DEVELOPMENT);

        return result;
    }

}
