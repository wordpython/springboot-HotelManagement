package com.wordpython.controller;

import com.wordpython.utils.Search;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class SearchController {
    @PostMapping(value = "/search")
    public String search(@RequestParam("keyword") String keyword) throws IOException {
        System.out.println("keyword--"+keyword);
        Search search=new Search();
        String json = search.getSearch(keyword);
        json=json.substring(25);
        json=json.split("documents")[1].substring(2).split("facets")[0];
        json=json.substring(0,json.length()-2);
        return json;
    }
}
