package br.com.food.entity;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomClientOrderSerializer extends StdSerializer<CliOrder> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2170714720388528331L;

	public CustomClientOrderSerializer() {
		this(null);
	}

	public CustomClientOrderSerializer(Class<CliOrder> t) {
		super(t);
	}

	@Override
	public void serialize(CliOrder clientOrder, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeStringField("Order Id", String.valueOf(clientOrder.getId()));
		jsonGenerator.writeStringField("Customer", clientOrder.getCustomer().getName());
		jsonGenerator.writeStringField("deliveryAddress", clientOrder.getDeliveryAddress());
		jsonGenerator.writeStringField("contact", clientOrder.getContact());
		jsonGenerator.writeStringField("total", String.valueOf(clientOrder.getTotal()));
		jsonGenerator.writeStringField("status", clientOrder.getStatus());

		jsonGenerator.writeEndObject();

	}

}
