package ru.test.pastbox.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.test.pastbox.api.requests.PasteBoxRequest;
import ru.test.pastbox.api.response.PasteBoxResponse;
import ru.test.pastbox.api.response.PasteBoxUrlResponse;
import ru.test.pastbox.servicies.PasteBoxServiceImpl;

import java.util.Collections;
import java.util.List;

/**
 * @author Stupakov D. L.
 **/
@RestController
@RequiredArgsConstructor
public class PastBoxController {
    private final PasteBoxServiceImpl pasteBoxService;

    @GetMapping("/")
    public List<PasteBoxResponse> getPublicPasteList(){
        return pasteBoxService.getFirstPublicPasteBoxes();
    }


    @GetMapping("/{hash}")
    public PasteBoxResponse getByHash(@PathVariable String hash){
        return pasteBoxService.getByHash(hash);
    }

    @PostMapping("/")
    public PasteBoxUrlResponse add(@RequestBody PasteBoxRequest request){
        return pasteBoxService.create(request);
    }


}
