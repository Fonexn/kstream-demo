package com.multi.streams.demo.bindings;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface KStreamsBinding {

  String FIRST_KSTREAM_OUT = "firstKStreamOut";
  String FIRST_KSTREAM_IN = "firstKStreamIn";

  @Input(FIRST_KSTREAM_IN)
  KStream<?, ?> firstKStreamIn();

  @Output(FIRST_KSTREAM_OUT)
  KStream<?, ?> firstKStreamOut();

  String SECOND_KSTREAM_OUT = "secondKStreamOut";
  String SECOND_KSTREAM_IN = "secondKStreamIn";

  @Input(SECOND_KSTREAM_OUT)
  KStream<?, ?> secondKStreamOut();

  @Output(SECOND_KSTREAM_IN)
  KStream<?, ?> secondKStreamIn();
}
