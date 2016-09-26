package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 把流转换成字符串
 * @author lingzhizpl
 *
 */
public class StreamTools {
	public static String readStream(InputStream is) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] bt = new byte[1024];
		int len = -1;
		while ((len = is.read(bt)) != -1) {
			os.write(bt, 0, len);
		}
		os.close();
		return os.toString();
	}
}
