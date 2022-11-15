package ru.test.pastbox.repositories;

import java.util.List;

/**
 * @author Stupakov D. L.
 **/
public interface PasteBoxRepository {
    PasteBoxEntety getByHash(String hash);
    List<PasteBoxEntety> getListOfPublicAndALive();
    void add(PasteBoxEntety pasteBoxEntety);
}
