package ru.test.pastbox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.test.pastbox.api.response.PasteBoxResponse;
import ru.test.pastbox.api.response.PasteBoxUrlResponse;
import ru.test.pastbox.exceptions.NotFoundEntityException;
import ru.test.pastbox.repositories.PasteBoxEntety;
import ru.test.pastbox.repositories.PasteBoxRepository;
import ru.test.pastbox.repositories.PasteBoxRepositoryMap;
import ru.test.pastbox.servicies.PasteBoxService;
import ru.test.pastbox.servicies.PasteBoxServiceImpl;

import javax.swing.text.html.parser.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Stupakov D. L.
 **/
@SpringBootTest
public class PasteBoxServiceTest {
    @Autowired
    private PasteBoxServiceImpl pasteBoxService;

    @MockBean
    private PasteBoxRepositoryMap pasteBoxRepository;


    //проверка на исключение
    @Test
    public void notExistHash(){
        when(pasteBoxRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class, ()->pasteBoxService.getByHash("dsadasdqw"));
    }


    @Test
    public void getExist(){
        PasteBoxEntety entety = new PasteBoxEntety();
        entety.setHash("1");
        entety.setData("11");
        entety.setPublic(true);
        when(pasteBoxRepository.getByHash("1")).thenReturn(entety);

        PasteBoxResponse expected = new PasteBoxResponse("11", true);
        PasteBoxResponse actual = pasteBoxService.getByHash("1");

        assertEquals(expected, actual);
    }
}
