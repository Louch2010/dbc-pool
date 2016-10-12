package com.louch2010.dbc.pool.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class IOUtil extends IOUtils {
	
	public static byte[] readFully(InputStream is, int size) throws IOException {
		isTrue(size >= 0, "readFully(size<0)");
		byte[] out = new byte[size];
		if (size == 0) {
			return out; // @return byte[0]
		}
		int n, offset = 0;
		do {
			n = is.read(out, offset, size - offset);
			if (n == -1) {
				throw new IOException("readFully()...EOF");
			}
			offset += n;
		} while (offset < size);
		return out;
	}

	public static byte[] readFully(InputStream is) throws IOException {
		// 处理cLen==-1的问题（无Content-Length项），读到无数据为止！
		final int bufSize = 1024;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (!(is instanceof BufferedInputStream)) {
			is = new BufferedInputStream(is, bufSize);// 为了提高速度
		}
		byte[] buf = new byte[bufSize];
		do {
			int rc = is.read(buf, 0, buf.length);// 读超？
			if (rc == -1) {
				break; // 直到无数据为止！！！
			}
			baos.write(buf, 0, rc); // 写入；
		} while (true);
		return baos.toByteArray();
	}

	public static void isTrue(boolean expression, String message,
			Object... values) {
		if (expression == false) {
			throw new IllegalArgumentException(String.format(message, values));
		}
	}
}
