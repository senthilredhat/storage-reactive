package com.brightly.storage.kafka.streams;

import com.brightly.storage.applicationservice.ItemService;
import com.brightly.storage.kafka.model.Kit;
import com.brightly.storage.kafka.model.Part;
import com.brightly.storage.kafka.model.PartKit;
import io.quarkus.kafka.client.serialization.ObjectMapperSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class TopologyProducer {

    static final String ZION_PART_TOPIC = "zion-part";
    static final String ZION_PART_KIT_TOPIC = "zion-part-kit";
    static final String ZION_KIT_TOPIC = "zion-kit";

    @Inject
    ItemService itemService;

    @Produces
    @ActivateRequestContext
    public Topology buildTopology() {
        ObjectMapperSerde<Part> partSerde = new ObjectMapperSerde<>(Part.class);
        ObjectMapperSerde<PartKit> partKitSerde = new ObjectMapperSerde<>(PartKit.class);
        ObjectMapperSerde<Kit> kitSerde = new ObjectMapperSerde<>(Kit.class);

        StreamsBuilder builder = new StreamsBuilder();

        KStream<Long, Part> partStream = builder.stream(
                ZION_PART_TOPIC,
                Consumed.with(Serdes.Long(), partSerde));

        partStream.peek((k,v) -> {
            var value = itemService.createItem(v).await()
                    .indefinitely();
            System.out.println("Part consumed; Value: "+value.getValue());
        });

        return builder.build();
    }
}