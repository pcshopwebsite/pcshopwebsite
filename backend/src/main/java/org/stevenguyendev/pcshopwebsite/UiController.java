package org.stevenguyendev.pcshopwebsite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {
    /**
     * FIX WHITELABEL ERROR PAGE (404) WHEN REFRESHING THE PAGE
     * "/{path:[^\\.]*}"): It maps any path segment (denoted by {path}) that does not contain a dot ([^\\.]) to the
     * redirectApi() method.
     * The regular expression [^\\.] ensures that the path segment does not contain a dot, effectively matching any
     * path except those with file extensions.
     *
     * The forward:/ statement instructs Spring MVC to internally forward the request to the root path ("/"),
     * effectively handling the request within the same server-side request context without a full browser redirect.
     *
     */
    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirectApi() {
        return "forward:/";
    }
}
