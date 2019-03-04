package com.xhwl.erp.util.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class BusinessExecuteStateSerializer  extends JsonSerializer<Integer>{
	
	@Override
	public void serialize(Integer value, JsonGenerator jsonGenerator, SerializerProvider aSerializerProvider) throws IOException, JsonProcessingException {
		switch (value) {
		case 0:
			jsonGenerator.writeString("前期接洽");
			break;
		case 1:
			jsonGenerator.writeString("方案编制");
			break;
		case 2:
			jsonGenerator.writeString("投标");
			break;
		case 3:
			jsonGenerator.writeString("中标");
			break;
		case 4:
			jsonGenerator.writeString("合同会签");
			break;
		case 5:
			jsonGenerator.writeString("纸质版合同签订");
			break;
		default:
			break;
		}
	}
}