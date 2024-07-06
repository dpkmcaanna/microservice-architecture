package  com.dist.sys.util;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

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
public class LocalDateTimeTypeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

	@Override
	public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
		return new JsonPrimitive(AppCommonConstant.D_MM_YYYY_HH_MM_SS_FORMATTER.format(localDateTime));
	}

	@Override
	public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return LocalDateTime.parse(json.getAsString(), AppCommonConstant.D_MM_YYYY_HH_MM_SS_FORMATTER);
	}
}