package com.shoter.ylper.api;

//Controller to test that Spring is working.

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping(method = RequestMethod.GET, value="/example")
    public @ResponseBody String test()
    {
        return "To jest test";
    }
}
