package com.persist.chartable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// import java.util.List;
// import java.util.Arrays;

@Controller
public class PodcastController {
    @GetMapping("/")
    public String index() {
        return "index"; // Renders index.html from /templates
    }
}
