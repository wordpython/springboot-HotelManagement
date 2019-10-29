package com.wordpython.controller;

import com.wordpython.entity.AdPage;
import com.wordpython.entity.Room;
import com.wordpython.entity.User;
import com.wordpython.service.AllRoomService;
import com.wordpython.service.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author wordpython
 * @Date 2019/10/27
 **/
@Controller
@RequestMapping(value = "/all")
public class AllHotelController {
    @Autowired
    private AllRoomService allRoomService;
    @Autowired
    private AllUserService allUserService;

    /**
     * //查询房间信息
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @GetMapping("/list")
    @ResponseBody
    public Object list(HttpSession session) {

        System.out.println("查询房间列表");
        Room room=new Room();
        room.setStart(0);
        room.setRows(10);
        List<Room> data = allRoomService.selectPartRoom(room);
        System.out.println(data);
        AdPage adPage = new AdPage(data, "0", "1", "啦啦啦");
        System.out.println(adPage);
        return adPage;
    }

    /**
     *
     *  查询用户信息
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @GetMapping("/user")
    @ResponseBody
    public Object user(@RequestParam(value = "username") String username){
        User user = allUserService.findByUsername(username);
        return user;
    }
}
