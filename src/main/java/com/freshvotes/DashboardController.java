package com.freshvotes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String rootView(){
        return "index";
    } // don't need to do "/src/main/resources/templates/index.html"

}
