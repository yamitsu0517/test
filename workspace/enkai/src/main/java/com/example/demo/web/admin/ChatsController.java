package com.example.demo.web.admin;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.common.DataNotFoundException;
import com.example.demo.common.FlashData;
import com.example.demo.entity.Chat;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.service.ChatService;
import com.example.demo.service.EventService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping ("/admin/chats")
// ログイン後TOP画面関連のコントローラ

public class ChatsController {
    private static Log log = LogFactory.getLog (ChatsController.class);
    
    @Autowired
    ChatService chatService;
    
    @Autowired
    EventService eventService;
    
    @Autowired
    UserService userService;
    
    /**
     * ダッシュボード表示
     * 
     * @param  model モデル
     * @return
     */
    @GetMapping (path = { "/talk/{id}", "" })
    public String list (@PathVariable Integer id, Chat chat, Model model) {
        List<Chat> chats = chatService.findByEventId (id);
        User user = userService.getLoginUser ();
        Event event = new Event ();
        try {
            event = eventService.findById (id);
            log.info ("正常に終了しました。");
        } catch (DataNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace ();
        }
        
        model.addAttribute ("chats", chats);
        model.addAttribute ("chat", chat);
        model.addAttribute ("event", event);
        model.addAttribute ("myId", user.getId ());
        
        return "admin/chats/talk";
    }
    
    /**
     * チャットを登録
     * 
     * @param  model モデル
     * @return
     */
    @PostMapping (path = "/create/")
    public String register (Chat chat, Integer eventId, Model model, RedirectAttributes ra) {
        Event event = new Event ();
        
        User user = userService.getLoginUser ();
        
        FlashData flash = new FlashData ();
        try {
            event = eventService.findById (eventId);
            
            chat.setUser (user);
            chat.setEvent (event);
            chatService.findByEventId (eventId);
            
            chatService.save (chat);
            
            log.info ("正常に終了しました。");
            flash = new FlashData ().success ("送信しました。");
            
        } catch (DataNotFoundException e) {
            e.printStackTrace ();
            flash = new FlashData ().danger ("該当データがありません。");
            
        } catch (Exception e) {
            e.printStackTrace ();
            flash = new FlashData ().danger ("予期しないエラーが発生しました。");
        }
        
        List<Chat> chats = chatService.findByEventId (eventId);
        ra.addFlashAttribute ("flash", flash);
        
        ra.addFlashAttribute ("chats", chats);
        ra.addFlashAttribute ("chatObj", chat);
        
        ra.addFlashAttribute ("event", event);
        ra.addFlashAttribute ("myId", user.getId ());
        return "redirect:/admin/chats/talk/" + eventId;
    }
    
}
