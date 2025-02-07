package com.persist.chartable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PodcastController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/discover")
    public String showDiscoverPage() {
        return "discover"; // This refers to discover.html inside templates
    }
    @GetMapping("/analytics")
    public String showAnalytics() {
        return "analytics";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
}
