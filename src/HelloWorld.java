import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class HelloWorld {

	public static void main(String[] args) {

		Base64.Encoder encoder = Base64.getEncoder();
		String encoding = encoder.encodeToString("hello, world!".getBytes(StandardCharsets.UTF_8));
		System.out.println(encoding);
	}
}
