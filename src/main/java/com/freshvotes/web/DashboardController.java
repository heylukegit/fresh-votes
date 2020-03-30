/*
2020-03-29 15:33:54
    学会 RequestMapping RequestMethod 的使用

 */

package com.freshvotes.web;

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
