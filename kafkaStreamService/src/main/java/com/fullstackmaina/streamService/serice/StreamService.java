package com.fullstackmaina.streamService.serice;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.fullstackmaina.streamService.bindings.StreamBindings;
import com.fullstackmaina.streamService.model.Order;

import lombok.extern.log4j.Log4j2;

@EnableBinding(StreamBindings.class)
@Service
@Log4j2
public class StreamService {

	@StreamListener("order-input-channel")
	@SendTo("order-takeaway-output-channel")
	public KStream<String, Order> takeAway(KStream<String, Order> order) {
		log.info("in TakeAway Topic");		
		return order.filter((k,v)-> v.getDeliveryType().equalsIgnoreCase("takeaway"));
	}

	@StreamListener("order-input-channel")
	@SendTo("order-homedelivery-output-channel")
	public KStream<String, Order> homeDelivery(KStream<String, Order> order) {
		log.info("in homeDelivery Topic");		
		return order.filter((k,v)-> v.getDeliveryType().equalsIgnoreCase("homedelivery"));
	}

}
