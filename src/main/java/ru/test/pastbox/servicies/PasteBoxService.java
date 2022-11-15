package ru.test.pastbox.servicies;

import ru.test.pastbox.api.requests.PasteBoxRequest;
import ru.test.pastbox.api.response.PasteBoxResponse;
import ru.test.pastbox.api.response.PasteBoxUrlResponse;

import java.util.List;

/**
 * @author Stupakov D. L.
 **/
public interface PasteBoxService {
    PasteBoxResponse getByHash(String hash);
    List<PasteBoxResponse> getFirstPublicPasteBoxes();
    PasteBoxUrlResponse create(PasteBoxRequest request);
}
