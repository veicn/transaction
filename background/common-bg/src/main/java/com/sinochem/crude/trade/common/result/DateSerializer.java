package com.sinochem.crude.trade.common.result;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.sinochem.crude.trade.common.utils.DateTimeUtils;

/**
 * 时间格式统一转换
 * <p>
 * 
 * @JsonSerialize(using = DateSerializer.class) public Date getCreateDate() {
 *                      return createDate; }
 *                      </p>
 *                      Created by bbt on 2017/10/21.
 */
public class DateSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException,
			JsonProcessingException {
		String formattedDate = DateTimeUtils.toDateString(date);
		jsonGenerator.writeString(formattedDate);
	}

}
