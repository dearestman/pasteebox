package ru.test.pastbox.servicies;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.test.pastbox.api.requests.PasteBoxRequest;
import ru.test.pastbox.api.requests.PublicStatus;
import ru.test.pastbox.api.response.PasteBoxResponse;
import ru.test.pastbox.api.response.PasteBoxUrlResponse;
import ru.test.pastbox.repositories.PasteBoxEntety;
import ru.test.pastbox.repositories.PasteBoxRepositoryMap;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Stupakov D. L.
 **/
@Service
@RequiredArgsConstructor
@PropertySource("classpath:application-host.properties")
public class PasteBoxServiceImpl implements PasteBoxService{
    private final PasteBoxRepositoryMap pasteBoxRepositoryMap;
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Value("${app.host}")
    private String host;
    @Value("${app.public_list_size}")
    private int publicListSize;

    @Override
    public PasteBoxResponse getByHash(String hash) {
        PasteBoxEntety pasteBoxEntety = pasteBoxRepositoryMap.getByHash(hash);

        //todo переписать  на ModelWrapper
        return new PasteBoxResponse(pasteBoxEntety.getData(), pasteBoxEntety.isPublic());
    }

    @Override
    public List<PasteBoxResponse> getFirstPublicPasteBoxes() {

        List<PasteBoxEntety> list = pasteBoxRepositoryMap.getListOfPublicAndALive();
        return list.stream().map(pasteBoxEntety -> new PasteBoxResponse(pasteBoxEntety.getData(), pasteBoxEntety.isPublic())).collect(Collectors.toList());
    }

    @Override
    public PasteBoxUrlResponse create(PasteBoxRequest request) {
        PasteBoxEntety pasteBoxEntety = new PasteBoxEntety();
        int hash = generateId();
        //todo переписать при помощи model wrapper
        pasteBoxEntety.setData(request.getData());
        pasteBoxEntety.setId(hash);
        pasteBoxEntety.setHash(Integer.toHexString(hash));
        pasteBoxEntety.setPublic(request.getPublicStatus()== PublicStatus.PUBLIC);
        pasteBoxEntety.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        pasteBoxRepositoryMap.add(pasteBoxEntety);



        return new PasteBoxUrlResponse(host+"/"+pasteBoxEntety.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
