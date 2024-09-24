import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    public static final String CAT_URL =
            "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("Assessed Cats")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000) // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000) // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        // создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(CAT_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
        // отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);

        // вывод полученных заголовков
//        Arrays.stream(response.getAllHeaders()).forEach(System.out::println);
        // чтение тела ответа и вывод на экран
//        String body = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
//        System.out.println(body);

        InputStream content = response.getEntity().getContent();
        // преобразование json в список
        List<Cat> cats = mapper.readValue(content, new TypeReference<>() {});
        // вывод на экран всего списка
        System.out.println("\nВесь список:");
        cats.forEach(System.out::println);
        System.out.println("\nОтфильтрованный список по upvotes:");
        // отфильтровать список и вывод на экран
        cats.stream()
                .filter(cat -> cat.upvotes() != null)
                .forEach(System.out::println);
    }
}