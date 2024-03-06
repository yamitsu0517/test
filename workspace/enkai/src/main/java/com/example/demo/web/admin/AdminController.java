package com.example.demo.web.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

@Controller
@RequestMapping ("/admin")
// ログイン後TOP画面関連のコントローラ

public class AdminController {
    @Autowired
    EventService eventService;
    
    /**
     * ダッシュボード表示
     * 
     * @param  model モデル
     * @return
     */
    @GetMapping (path = { "/", "" })
    public String list (Model model) {
        List<Event> events = eventService.findAll ();
        
        model.addAttribute ("events", events);
        
        return "admin/admin/list";
    }
    
}
