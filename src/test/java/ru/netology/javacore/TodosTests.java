package ru.netology.javacore;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTests {

    Todos sut = new Todos();// ваши тесты для класса Todos

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running TodosTest");
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("TodosTest complete");
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Test start");
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete");
    }

    @Test
    public void testAddTask() {
        //given
        String task = "Учёба";
        List<String> original = Arrays.asList("Учёба");

        //when
        sut.addTask(task);
        var result = sut.todos;

        //then
        assertEquals(original,result);
    }

    @Test
    public void testRemoveTask() {
        //given
        String task = "Учёба";
        sut.todos = new ArrayList<String>(Arrays.asList("Пробежка", "Акробатика", "Учёба"));
        var original = new ArrayList<String>(Arrays.asList("Пробежка", "Акробатика"));

        //when
        sut.removeTask(task);
        var result = sut.todos;

        //then
        assertEquals(original,result);
    }


    @ParameterizedTest
    @MethodSource("source")
    public void testTransferTrue(List<String> todos,String original) {
        //act
        sut.todos = todos;
        var result = sut.getAllTasks();

        //arrange
        assertEquals(original, result);
    }

    private static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(Arrays.asList("Пробежка", "Акробатика", "Учёба"), "Акробатика Пробежка Учёба "),
                Arguments.of(Arrays.asList("Пробежка", "Кино", "Учёба"), "Кино Пробежка Учёба "));
    }

}
