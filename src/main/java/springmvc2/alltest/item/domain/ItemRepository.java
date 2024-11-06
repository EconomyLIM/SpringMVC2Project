package springmvc2.alltest.item.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import springmvc2.alltest.item.file.FileStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date           : 2024-11-06
 * created by     : 임경재 ( bmw4117@entropykorea.com )
 * description    :
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class ItemRepository {
    Map<Long, Item> itemStore = new HashMap<>();
    private static Long sequence = 0L;

    private final FileStore fileStore;

    public Item saveItem(Item item) throws IOException {
        item.setId(++sequence);
        itemStore.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return itemStore.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(itemStore.values());
    }
}
