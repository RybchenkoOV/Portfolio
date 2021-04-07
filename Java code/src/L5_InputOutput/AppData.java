package L5_InputOutput;

import java.io.Serializable;
import java.util.Arrays;

public class AppData implements Serializable { // Для первой реализации мы будем использовать объекты, поэтому нужна сериализация

    private String[] headers;

    public int[][] data;

    public AppData(String[] headers, int[][] data) {
        this.headers = headers;
        this.data = data;
    }

    @Override
    public String toString() {
        return "AppData{" +
                "headers=" + Arrays.toString(headers) +
                ", data=" + Arrays.deepToString(data) + // Для вывода многомерных массивов используем Arrays.deepToString
                '}';
    }

    public String[] getHeaders() {
        return headers;
    }

    public int[][] getData() {
        return data;
    }
}
