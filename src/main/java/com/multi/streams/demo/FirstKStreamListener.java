package com.multi.streams.demo;

import com.multi.streams.demo.bindings.KStreamsBinding;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({KStreamsBinding.class})
public class FirstKStreamListener {

    @StreamListener("firstKStreamIn")
    @SendTo("firstKStreamOut")
    public KStream<String, String> receiveFirstKStream(KStream<?, String> input) {
        return input
                .map((key, value) -> new KeyValue<>(value, value))
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(5000))
                .count(Materialized.as("first-kstream-store"))
                .toStream().map((key, value) -> new KeyValue<>(key.key(), "word1"));
    }
}
