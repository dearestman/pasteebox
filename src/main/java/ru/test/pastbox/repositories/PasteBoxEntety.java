package ru.test.pastbox.repositories;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Stupakov D. L.
 **/
@Data
public class PasteBoxEntety {

    private int id;
    private String data;
    private String hash;
    private LocalDateTime lifeTime;
    private boolean isPublic;
}
