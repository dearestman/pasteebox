package ru.test.pastbox.controllers;

import org.springframework.web.bind.annotation.*;
import ru.test.pastbox.api.requests.PasteBoxRequest;

import java.util.Collections;
import java.util.List;

/**
 * @author Stupakov D. L.
 **/
@RestController
public class PastBoxController {

    @GetMapping("/")
    public List<String> getPublicPasteList(){
        return Collections.emptyList();
    }


    @GetMapping("/{hash}")
    public String getByHash(@PathVariable String hash){
        return hash;
    }

    @PostMapping("/")
    public String add(@RequestBody PasteBoxRequest request){
        return request.getData();
    }


}
