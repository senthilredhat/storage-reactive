package com.brightly.storage.grpc.client;

import com.brightly.storage.grpc.ItemsRequest;
import com.brightly.storage.grpc.ItemsServiceGrpc;
import com.brightly.storage.grpc.MutinyItemsServiceGrpc;
import com.brightly.storage.kafka.model.Part;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ItemsGrpcClient {

    @GrpcClient("hello")
    MutinyItemsServiceGrpc.MutinyItemsServiceStub mutinyItemsServiceStub;

    public Uni<Long> createItem(Part part) {
        ItemsRequest item = ItemsRequest.newBuilder()
                .setPartId(Int64Value.of(part.getPartId()))
                .setPartNumber(part.getPartNo())
                .setCostCenterId(part.getCostCenterId())
                .setName(part.getName())
                .setCreatedOn(toProtobufTimestamp(part.getCreatedOn()))
                .setCreatedBy(part.getCreatedBy())
                .setLastModifiedOn(toProtobufTimestamp(part.getLastModifiedOn()))
                .setLastModifiedBy(part.getLastModifiedBy())
                .build();

        return mutinyItemsServiceStub.createItems(item).onItem().transform(i -> i.getValue());
    }

    public static Timestamp toProtobufTimestamp(OffsetDateTime value) {
        var instant = value.toInstant();
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }
}
