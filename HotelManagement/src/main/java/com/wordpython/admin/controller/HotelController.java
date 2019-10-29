package com.wordpython.admin.controller;

import com.wordpython.admin.entity.AdPage;
import com.wordpython.admin.entity.Hotel;
import com.wordpython.admin.entity.Photo;
import com.wordpython.admin.entity.Room;
import com.wordpython.admin.service.AdminService;
import com.wordpython.admin.service.HotelService;
import com.wordpython.admin.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @Author wordpython
 * @Date 2019/10/18
 **/
@Controller
@RequestMapping("/hotel")
public class HotelController {
    private static String PREFIX = "admin/hotel/";
    @Autowired
    private RoomService roomService;
    @Autowired
    private HotelService hotelService;

    /**
     * 跳转到查看房间列表的页面
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("")
    public String index(HttpSession session) {
        System.out.println("跳转到查看酒店管理的页面");
        session.setAttribute("src", "/hotel");
        return PREFIX + "hotel";
    }

    /**
     * 跳转到添加房间列表的页面
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/hotel_add")
    public String hotel_add(HttpSession session) {
        System.out.println("添加酒店房间的页面");
        //该session用于保存某一房间图片的文件夹名
        session.setAttribute("img_url",UUID.randomUUID().toString());
        String img_url="/images/"+session.getAttribute("img_url")+"/";
        System.out.println("img_url--:"+img_url);
        return PREFIX + "hotel_add";
    }
    /**
     * 添加酒店房间form信息
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/hotel_addform")

    @ResponseBody
    public String hotel_addform(@RequestBody Room room, HttpSession session) {
        System.out.println("添加酒店房间form信息");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        //该session用于保存某一房间图片的文件夹名
        room.setType_id(UUID.randomUUID().toString());
        room.setRoom_id(UUID.randomUUID().toString());
        room.setStatus("可预订");
        room.setCreate_time(Timestamp.valueOf(date));
        room.setUpdate_time(Timestamp.valueOf(date));
        room.setHotel_id(session.getAttribute("hotel_id").toString());
        System.out.println(room);
        //存入房间信息
        int r = roomService.insertRoom(room);
        Photo photo=(Photo) session.getAttribute("photo");
        photo.setRoom_number(room.getRoom_number());
        photo.setHotel_id(session.getAttribute("hotel_id").toString());
        int p=roomService.updatePhoto(photo);
        System.out.println("   r:"+r+"   p:"+p);
        return "房间信息添加成功";
    }


    /**
     * 跳转到编辑房间页面
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/room_edit")
    public String htoelEdit(@RequestParam("room_number") String room_number,HttpSession session) {
        session.setAttribute("room_number",room_number);
        System.out.println("跳转到编辑房间页面");
        return PREFIX + "hotel_edit";
    }

    /**
     * 获取房间详情
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/getRoomInfo")
    @ResponseBody
    public Object getUserInfo(HttpSession session) {
        System.out.println("获取房间详情");
        Room r=new Room();
        r.setHotel_id(session.getAttribute("hotel_id").toString());
        r.setRoom_number(session.getAttribute("room_number").toString());
        Room room = roomService.selectRoom(r);
        session.setAttribute("type_id",room.getType_id());
        session.setAttribute("room_id",room.getRoom_id());
        System.out.println("room_id:"+room.getRoom_id());
        System.out.println(room);
        return room;
    }

    /**
     * 修改房间信息
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@RequestBody Room room, HttpSession session) {;
        System.out.println("修改房间信息");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        room.setUpdate_time(Timestamp.valueOf(date));
        room.setType_id(session.getAttribute("type_id").toString());
        room.setRoom_id(session.getAttribute("room_id").toString());
        room.setHotel_id(session.getAttribute("hotel_id").toString());
        System.out.println("room_id:"+session.getAttribute("room_id").toString());
        System.out.println(room);
        int i=roomService.updateRoom(room);
        System.out.println("修改房间数："+i);
        if(i>0){
            return "修改成功";
        }
        return "修改失败，请刷新重试";
    }


    /**
     * 删除房间信息
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam("room_number") String room_number,HttpSession session) {
        System.out.println("删除房间信息");
        Room room=new Room();
        room.setRoom_number(room_number);
        room.setHotel_id(session.getAttribute("hotel_id").toString());
        int i=roomService.deleteRoom(roomService.selectRoom(room));
        if(i>0){
            return "删除成功";
        }
        return "删除失败，请刷新重试";
    }


    /**
     * 查询管理员列表
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String timeLimit,
                       @RequestParam(required = false) Long deptId,HttpSession session) {

        System.out.println("查询房间列表");
        //拼接查询条件
        String beginTime = "";
        String endTime = "";
//        AdUsers data=new AdUsers("123abc","wordpython","男","超级管理员","开发部","djsk@qq.com","13610079097","2016-01-29 08:49:53", "正常");
        Room room=new Room();
        room.setHotel_id(session.getAttribute("hotel_id").toString());
        System.out.println(room);
        List<Room> data = roomService.selectRooms(room);
        System.out.println(data);
        AdPage adPage = new AdPage(data, "0", "1", "啦啦啦");
        System.out.println(adPage);
        return adPage;
    }

    /**
     * 跳转到编辑房间页面
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/show_photo")
    public String showPhoto(@RequestParam(value = "room_number") String room_number,HttpSession session) {
        session.setAttribute("room_number",room_number);//标记房间号，用于查询该房间图片
        return PREFIX + "show_photo";
    }

    /**
     * 查询房间图片
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping(value = "/photo")
    @ResponseBody
    public Object photo(HttpSession session){
        System.out.println("查询房间图片");
        Room room=new Room();
        room.setHotel_id(session.getAttribute("hotel_id").toString());
        room.setRoom_number(session.getAttribute("room_number").toString());
        System.out.println(room);
        Photo photos = roomService.selectPhoto(room);
        System.out.println(photos);
        return photos;
    }
    /**
     * 添加酒店
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @PostMapping("/hotel")
    @ResponseBody
    public String hotel_add(@RequestBody Hotel hotel, HttpSession session) {
        System.out.println("添加酒店");
        String admin_id=session.getAttribute("admin_id").toString();
        hotel.setAdmin_id(admin_id);
        hotel.setHotel_id(UUID.randomUUID().toString());
        System.out.println(hotel);
        hotelService.insertHotel(hotel);
        session.setAttribute("hotel_id",hotelService.selectHotelByAdmin_id(admin_id).getHotel_id());
        return "房间信息添加成功";
    }
}
