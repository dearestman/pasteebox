package ru.test.pastbox.api.requests;

import lombok.Data;

/**
 * @author Stupakov D. L.
 **/
@Data
public class PasteBoxRequest {
    private String data;
    private long expirationTimeSeconds;
    private PublicStatus publicStatus;
}
