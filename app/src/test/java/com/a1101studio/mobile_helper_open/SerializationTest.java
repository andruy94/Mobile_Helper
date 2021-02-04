package com.a1101studio.mobile_helper_open;

import com.a1101studio.mobile_helper_open.models.CheckBoxItem;
import com.a1101studio.mobile_helper_open.models.Detail;
import com.a1101studio.mobile_helper_open.models.LowCheckListItem;
import com.a1101studio.mobile_helper_open.singleton.WorkData;
import com.google.gson.Gson;

import org.easymock.EasyMock;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)


public class SerializationTest {

    @org.junit.Test
    public void test() throws Exception {

        /*Options opt = new OptionsBuilder()
                .include(Test.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();*/
        LowCheckListItem lowCheckListItem = new LowCheckListItem("LowCheckListItem",new CheckBoxItem[]{new CheckBoxItem(false,"CheckBoxTitle")});
        System.out.println(new Gson().toJson(lowCheckListItem));
    }



    @State(Scope.Benchmark)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public static class Test {
        public static final int INT = 1024;
        boolean flag = true;
        public Test(int detailsCount) {

        }

        public Test(){

            init();
        }


        public WorkData workData;

        public void init() {
            workData = WorkData.getInstance();
            Detail[] details = new Detail[INT];
            for (int i = 0; i < details.length; i++) {
                CheckBoxItem checkBoxItem = new CheckBoxItem(false, getRandomString());
                details[i] = new Detail(checkBoxItem, null);
                ArrayList<Detail[]> list = new ArrayList<>();
                list.add(details);
                workData.setDetails(list);
            }

        }

        @Benchmark
        public void test() throws IOException {

            File file = new File("C:\\Users\\andruy94\\Desktop\\mockFileName");
            file.createNewFile();

            /*FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(new Gson().toJson(workData));
            fileWriter.flush();
            if(flag) {
                System.out.println("Длина файла=" + file.length());
                flag = false;
            }

            fileWriter.close();*/

            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
                outputStream.writeObject(workData);
                if(flag) {
                    System.out.println("Длина файла=" + file.length());
                    flag = false;
                }
                outputStream.close();
            }
        }
    }

    static String getRandomString() {
        final String RANDOM_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        final int RANDOM_STRING_LENGTH = 64;
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < RANDOM_STRING_LENGTH) {
            int index = (int) (rnd.nextFloat() * RANDOM_STRING.length());
            result.append(RANDOM_STRING.charAt(index));
        }
        return result.toString();

    }
}