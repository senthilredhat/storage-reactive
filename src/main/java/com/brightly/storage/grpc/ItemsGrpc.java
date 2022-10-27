package com.brightly.storage.grpc;

import com.brightly.storage.entity.Item;
import com.brightly.storage.entity.ItemRepository;
import com.brightly.storage.entity.ItemType;
import com.google.protobuf.Int64Value;
import com.google.protobuf.Timestamp;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@GrpcService
public class ItemsGrpc extends MutinyItemsServiceGrpc.ItemsServiceImplBase{

    @Inject
    ItemRepository itemRepository;

    @Override
    public Uni<Int64Value> createItems(ItemsRequest request) {
        Item item = new Item();
        item.setItemId(request.getPartId().getValue());
        item.setNumber(request.getPartNumber());
        item.setName(request.getName());
        item.setCostCenter(request.getCostCenterId());
        item.setCreatedDate(toOffsetDateTime(request.getCreatedOn()));
        item.setCreatedBy(request.getCreatedBy());
        item.setUpdatedDate(toOffsetDateTime(request.getLastModifiedOn()));
        item.setUpdatedBy(request.getLastModifiedBy());
        item.setItemType(ItemType.ITEM);

        return itemRepository.persistAndFlush(item).onItem()
                .transform(inserted -> Int64Value.newBuilder()
                        .setValue(inserted.id)
                        .build());
    }

    @Override
    public Uni<ItemsResponse> getItemById(Int64Value request) {
        return super.getItemById(request);
    }

    public static OffsetDateTime toOffsetDateTime(Timestamp value) {
        OffsetDateTime dateTime = null;
        if (value != null && value.getSeconds() != 0) {
            dateTime = OffsetDateTime.ofInstant(Instant.ofEpochSecond(value.getSeconds(), value.getNanos()),
                    ZoneOffset.UTC);
        }
        return dateTime;
    }
}
