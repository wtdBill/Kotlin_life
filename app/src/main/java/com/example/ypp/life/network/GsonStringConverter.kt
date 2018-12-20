package com.example.ypp.life.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer

import java.lang.reflect.Type

/**
 * @author huangyao
 * desc 获取到后端返回的字符传为null时，解析成空字符串
 * created 18-12-14
 */
class GsonStringConverter : JsonSerializer<String>, JsonDeserializer<String> {

    override fun serialize(src: String?, typeOfSrc: Type,
                           context: JsonSerializationContext): JsonElement {
        return if (src == null) {
            JsonPrimitive("")
        } else {
            JsonPrimitive(src)
        }
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type,
                             context: JsonDeserializationContext): String {
        return json.asJsonPrimitive.asString
    }
}
