package com.example.app_data.net.retrofit;

import androidx.annotation.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

// Retrofit by default crashes on empty body, but we sometimes use it for convenience,
// so this class modifies retrofit behavior in such cases to return null instead.
// Solution taken from https://kenkyee.medium.com/retrofit2-doesnt-handle-empty-content-responses-bef2b33ee8ea
class ReturnNullOnEmptyBodyConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            @NonNull Type type,
            @NonNull Annotation[] annotations,
            Retrofit retrofit
    ) {
        final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
        return (Converter<ResponseBody, Object>) value -> {
            if (value.contentLength() == 0) return null;
            return delegate.convert(value);
        };
    }
}
