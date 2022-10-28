package com.brightly.storage.applicationservice;

import com.brightly.storage.entity.Item;
import com.brightly.storage.entity.ItemRepository;
import com.brightly.storage.entity.ItemType;
import com.brightly.storage.kafka.model.Part;
import com.google.protobuf.Int64Value;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.control.ActivateRequestContext;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ItemService {

    @Inject
    ItemRepository itemRepository;

    @ActivateRequestContext
    public Uni<Int64Value> createItem(Part part) {

        return Panache
                .withTransaction(() -> {
                    Item item = new Item();
                    item.setItemId(part.getPartId());
                    item.setNumber(part.getPartNo());
                    item.setName(part.getName());
                    item.setAlternateItemNumber(part.getAlternatePartNo());
                    item.setDescription(part.getDescription());
                    item.setBarcode(part.getBarcode());
                    item.setCostCenter(part.getCostCenterId());
                    item.setManufacturerItemNo(part.getManufacturerPartNo());
                    item.setManufacturerBarcode(part.getManufacturerBarcode());
                    item.setManufacturerId(part.getManufacturerId());
                    item.setCreatedDate(part.getCreatedOn());
                    item.setCreatedBy(part.getCreatedBy());
                    item.setUpdatedDate(part.getLastModifiedOn());
                    item.setUpdatedBy(part.getLastModifiedBy());
                    item.setItemType(ItemType.ITEM);
                    item.setActive(part.isActive());
                    item.setNote(part.getNote());
                    return itemRepository.persistAndFlush(item);
                })
                .onItem()
                .transform(inserted -> Int64Value.newBuilder()
                        .setValue(inserted.id)
                        .build());
    }

    public Uni<List<Item>> getItemList() {
        return itemRepository
                .findAll().list();
    }

}
