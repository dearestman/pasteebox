package ru.test.pastbox.repositories;

import org.springframework.stereotype.Repository;
import ru.test.pastbox.exceptions.NotFoundEntityException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Stupakov D. L.
 **/
@Repository
public class PasteBoxRepositoryMap implements PasteBoxRepository{
    private final Map<String, PasteBoxEntety> vault = new ConcurrentHashMap<>();



    @Override
    public PasteBoxEntety getByHash(String hash) {
        PasteBoxEntety pasteBoxEntety = vault.get(hash);
        if (pasteBoxEntety==null){
            throw new NotFoundEntityException("Pastebox not found with hash=" + hash);
        }

        return pasteBoxEntety;
    }

    @Override
    public List<PasteBoxEntety> getListOfPublicAndALive() {
        LocalDateTime now = LocalDateTime.now();

        return vault.values().stream()
                .filter(PasteBoxEntety::isPublic)
                .filter(pasteBoxEntety -> pasteBoxEntety.getLifeTime().isAfter(now))
                .sorted(Comparator.comparing(PasteBoxEntety::getId).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public void add(PasteBoxEntety pasteBoxEntety) {
        vault.put(pasteBoxEntety.getHash(), pasteBoxEntety);
    }
}
