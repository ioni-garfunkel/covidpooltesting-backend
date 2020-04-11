package org.covidpooltesting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.CountingOutputStream;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonJerseyProvider implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

	private static final String DEFAULT_GSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	private final Gson gson = createDefaultGsonBuilder().create();
	private final boolean serializeByDefault = true;
	private final boolean sendSize = false;

	private static GsonBuilder createDefaultGsonBuilder() {
		return new GsonBuilder().setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.setDateFormat(DEFAULT_GSON_DATE_FORMAT).serializeNulls();
	}

	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return serializeByDefault;
	}

	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException {
		return gson.fromJson(new InputStreamReader(entityStream, Charsets.UTF_8), genericType);
	}

	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return serializeByDefault;
	}

	public long getSize(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if (sendSize) {
			try {
				return serialize(object, new CountingOutputStream(ByteStreams.nullOutputStream())).getCount();
			} catch (IOException e) {
				throw new IllegalStateException("Error counting serialization bytes", e);
			}
		} else {
			return -1;
		}
	}

	public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException {
		serialize(object, entityStream);
	}

	private <T extends OutputStream> T serialize(Object src, T outputStream) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charsets.UTF_8);
		gson.toJson(src, writer);
		writer.flush();
		return outputStream;
	}

}
