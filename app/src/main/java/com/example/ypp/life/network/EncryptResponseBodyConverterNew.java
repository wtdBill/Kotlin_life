package com.example.ypp.life.network;

import com.example.ypp.life.utils.Logger;
import com.google.gson.TypeAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Yin on 2017/3/20.
 */

final class EncryptResponseBodyConverterNew<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    EncryptResponseBodyConverterNew(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            Reader reader = value.charStream();
            String s = reader2String(reader);
            try {
                Logger.INSTANCE.e("data = " + s);
                return adapter.fromJson(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return adapter.fromJson(s);
        } finally {
            value.close();
        }
    }

    private String reader2String(Reader reader) {
        BufferedReader in = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer();
        String line = " ";
        try {
            while ((line = in.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
