package ru.test.pastbox.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.test.pastbox.api.requests.PublicStatus;

/**
 * @author Stupakov D. L.
 **/
@Data
@RequiredArgsConstructor
public class PasteBoxResponse {
    private final String date;
    private final boolean isPublic;
}
