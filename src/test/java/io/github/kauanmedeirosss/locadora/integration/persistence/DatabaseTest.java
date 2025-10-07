package io.github.kauanmedeirosss.locadora.integration.persistence;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseTest {

    static Connection connection;

    @BeforeAll // indica que será executado uma única vez ANTES de todos os testes
    static void setUpDataBase () throws Exception{
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        connection.createStatement().execute("CREATE TABLE clientes (id INT, nome VARCHAR)");
    }

    @BeforeEach
    void insertClienteTest() throws Exception{
        connection.createStatement().execute("INSERT INTO clientes(id, nome) VALUES (1, 'Kauan')");
    }

    @Test
 // @Disabled -> desabilita teste para que ele não seja executado
    void testClienteExists() throws Exception{
        var resultado = connection
                .createStatement()
                .executeQuery("SELECT * FROM clientes WHERE id = 1");

        Assertions.assertTrue(resultado.next()); // true se deu resultado, false se não deu nada
    }

    @Test
    void testClienteNotExists() throws Exception{
        var resultado = connection
                .createStatement()
                .executeQuery("SELECT * FROM clientes WHERE id = 2");

        Assertions.assertFalse(resultado.next()); // true se deu resultado, false se não deu nada
    }

    @AfterAll // indica que será executado uma única vez DEPOIS de todos os testes
    static void closeDataBase() throws Exception{
        connection.close();
    }
}
