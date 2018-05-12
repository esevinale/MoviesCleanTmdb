package com.esevinale.movieguidetmdb.data.repository.datasource;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtils {

    public static String inputStreamToString(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, bytes.length);
        return new String(bytes);
    }
}
