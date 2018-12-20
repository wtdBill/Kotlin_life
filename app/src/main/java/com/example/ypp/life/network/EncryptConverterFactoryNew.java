package com.example.ypp.life.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by 88426 on 2017/3/20.
 * 模仿 EncryptConverterFactory 对数据解密后自动转成实体类
 */

public class EncryptConverterFactoryNew extends Converter.Factory {
        /**
         * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         */
        public static EncryptConverterFactoryNew create() {
                GsonBuilder gb = new GsonBuilder();
                gb.registerTypeAdapter(String.class, new GsonStringConverter());
                Gson gson = gb.create();
                return create(gson);
        }

        /**
         * Create an instance using {@code gson} for conversion. Encoding to JSON and
         * decoding from JSON (when no charset is specified by a header) will use UTF-8.
         */
        public static EncryptConverterFactoryNew create(Gson gson) {
                return new EncryptConverterFactoryNew(gson);
        }

        private final Gson gson;

        private EncryptConverterFactoryNew(Gson gson) {
                if (gson == null) throw new NullPointerException("gson == null");
                this.gson = gson;
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
                TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
                return new EncryptResponseBodyConverterNew<>(adapter);
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
                TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
                return new EncryptRequestBodyConverter<>(gson, adapter);
        }
}