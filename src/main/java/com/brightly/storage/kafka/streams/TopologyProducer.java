package com.brightly.storage.kafka.streams;

import com.brightly.storage.grpc.client.ItemsGrpcClient;
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
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ApplicationScoped
public class TopologyProducer {

    static final String ZION_PART_TOPIC = "zion-part";
    static final String ZION_PART_KIT_TOPIC = "zion-part-kit";
    static final String ZION_KIT_TOPIC = "zion-kit";

    @Inject
    ItemsGrpcClient itemsGrpcClient;

    @Produces
    public Topology buildTopology() {
        ObjectMapperSerde<Part> partSerde = new ObjectMapperSerde<>(Part.class);
        ObjectMapperSerde<PartKit> partKitSerde = new ObjectMapperSerde<>(PartKit.class);
        ObjectMapperSerde<Kit> kitSerde = new ObjectMapperSerde<>(Kit.class);

        StreamsBuilder builder = new StreamsBuilder();

        KStream<Long, Part> partStream = builder.stream(
                ZION_PART_TOPIC,
                Consumed.with(Serdes.Long(), partSerde));

        partStream.peek((k,v) -> itemsGrpcClient.createItem(v));

        KStream<Long, Kit> kitStream = builder.stream(
                ZION_KIT_TOPIC,
                Consumed.with(Serdes.Long(), kitSerde));

        kitStream.peek((k,v) -> System.out.println("Kit Key: "+k+" Value: "+v));

        KStream<Long, PartKit> partKitStream = builder.stream(
                ZION_PART_KIT_TOPIC,
                Consumed.with(Serdes.Long(), partKitSerde));

        partKitStream.peek((k,v) -> System.out.println("Part Kit Key: "+k+" Value: "+v));

        return builder.build();
    }
}