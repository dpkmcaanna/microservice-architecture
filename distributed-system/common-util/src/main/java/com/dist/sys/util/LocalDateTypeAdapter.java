package  com.dist.sys.util;

import java.lang.reflect.Type;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.dist.sys.util.constant.AppCommonConstant;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

@Component
public class LocalDateTypeAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

	@Override
	public JsonElement serialize(final LocalDate date, final Type type, final JsonSerializationContext ctx) {
		return new JsonPrimitive(date.format(AppCommonConstant.D_MM_YYYY_FORMATTER));
	}

	@Override
	public LocalDate deserialize(final JsonElement json, final Type type, final JsonDeserializationContext ctx)
			throws JsonParseException {
		return LocalDate.parse(json.getAsString(), AppCommonConstant.D_MM_YYYY_FORMATTER);
	}
}
