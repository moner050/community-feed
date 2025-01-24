package org.lmh.admin.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");

        mav.addObject("result", new ArrayList<>());
        return mav;
    }
}
