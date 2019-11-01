package com.wordpython.controller;

import com.wordpython.entity.*;
import com.wordpython.service.AllBookingService;
import com.wordpython.service.AllDiscussService;
import com.wordpython.service.AllRoomService;
import com.wordpython.service.AllUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private AllBookingService allBookingService;
    @Autowired
    private AllDiscussService allDiscussService;

    /**
     * 查询用户信息
     * 参数：username
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/user")
    @ResponseBody
    public Object user(@RequestBody User user) {
        System.out.println("查询用户信息");
        System.out.println(user);
        User result = allUserService.findByUsername(user);
        System.out.println(result);
        return result;
    }

    /**
     * 修改密码
     *参数：username,password
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/update_password")
    @ResponseBody
    public Object update_password(@RequestBody User user,HttpSession session) {
        System.out.println("修改密码");
        System.out.println(user);
        System.out.println(session.getAttribute("isLogin"));
        //先判断是否已登录
        if (session.getAttribute("isLogin").equals("true")) {
            if (allUserService.updatePassword(user)>0) {
                return "true";
            }
        }
        return "false";
    }

    /**
     * 插入实名
     *参数：username,name,idcard,phone
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/set_name")
    @ResponseBody
    public Object set_name(@RequestBody User user,HttpSession session) {
        System.out.println("插入实名");
        System.out.println(user);
        //先判断是否已登录
        if (session.getAttribute("isLogin").equals("true")) {
            //插入实名
            if (allUserService.setName(user)>0) {
                return "true";
            }
        }
        return "false";
    }

    /**
     * //查询房间信息
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) int page,
                       @RequestParam(required = false) int limit,
                       HttpSession session) {
        System.out.println("查询房间列表");
        System.out.println("页码:page="+page+"  一页数据数:limit="+limit);
        int start=(page-1)*limit;
        int rows=limit;
        Room room = new Room();
        room.setStart(start);
        room.setRows(rows);
        List<Room> data = allRoomService.selectPartRoom(room);
        System.out.println(data);
        int count=allRoomService.selectRoomCount();
        if(count%10>0){//求余运算符
            count=count/10+1;
        }else{
            count=count/10;
        }
        AdPage adPage = new AdPage(data, 0, count, "啦啦啦");
        System.out.println(adPage);
        return adPage;
    }

    /**
     * 获取房间详情
     * 参数：room_number,hotel_id
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping("/getRoomInfo")
    @ResponseBody
    public Object getUserInfo(@RequestBody Room room) {
        System.out.println("获取房间详情");
        System.out.println(room);
        Room r = allRoomService.selectRoom(room);
        System.out.println("获取房间详情:结果"+r);
        return r;
    }

    /**
     * 查询房间图片
     * 参数：room_number,hotel_id
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     */
    @RequestMapping(value = "/photo")
    @ResponseBody
    public Object photo(@RequestBody Room room) {
        System.out.println("查询房间图片");
        System.out.println(room);
        Photo photos = allRoomService.selectPhoto(room);
        System.out.println(photos);
        return photos;
    }

    /*
     * 预订
     * 参数：room_number,hotel_id,hotel_name,username
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     * */
    @RequestMapping(value = "/booking")
    @ResponseBody
    public Object booking(@RequestBody Booking booking) {
        //酒店id，酒店名，房间号，用户名
        System.out.println("预订");
        booking.setBooking_id(UUID.randomUUID().toString());
        booking.setStatus("未付款");
        System.out.println(booking);
        //插入预订表
        int i = allBookingService.insertBooking(booking);
        //修改房间信息表
        Room room = new Room(booking.getRoom_number(), booking.getHotel_id(), booking.getStatus());
        return i;
    }

    /*
     * 查询用户订单（一对多）
     * 参数：username
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     * */
    @RequestMapping(value = "/select_booking")
    @ResponseBody
    public Object select_booking(@RequestBody Booking booking) {
        //用户名
        System.out.println(" 查询用户订单（一对多）");
        System.out.println(booking);
        List<Booking> bookings = allBookingService.selectBooking(booking);
        System.out.println(bookings);
        return bookings;
    }

    /*
     * 退订
     * 参数：booking_id
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     * */
    @RequestMapping(value = "/delete_booking")
    @ResponseBody
    public Object delete_booking(@RequestBody Booking booking) {
        System.out.println("退订");
        System.out.println(booking);
        int i = allBookingService.deleteBooking(booking);
        System.out.println(i);
        return i;
    }

    /*
     * 插入评论
     * 参数：insert_discuss
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     * */
    @RequestMapping(value = "/insert_discuss")
    @ResponseBody
    public Object insert_discuss(@RequestBody Discuss discuss) {
        System.out.println("插入评论");
        discuss.setDiscuss_id(UUID.randomUUID().toString());
        System.out.println(discuss);
        int i = allDiscussService.insertDiscuss(discuss);
        System.out.println(i);
        return i;
    }

    /*
     * 查询评论
     * 参数：room_number,hotel_id
     *
     * @author wordpython
     * @Date 2019/10/14 22:43
     * */
    @RequestMapping(value = "/select_discuss")
    @ResponseBody
    public Object select_discuss(@RequestBody Discuss discuss) {
        System.out.println("查询评论");
//        System.out.println("页码:page="+page+"  一页数据数:limit="+limit);
        int start=(discuss.getStart()-1)*discuss.getRows();
        discuss.setStart(start);
//        int rows=limit;
//        Discuss discuss=new Discuss();
//        discuss.setStart(start);discuss.setRows(rows);
        System.out.println(discuss);
        List<Discuss> discusses = allDiscussService.selectPartDiscuss(discuss);
        System.out.println(discusses);
        int count=allDiscussService.selectDiscussCount();
        if(count%10>0){//求余运算符
            count=count/10+1;
        }else{
            count=count/10;
        }
        AdPage adPage = new AdPage(discusses, 0, count, "啦啦啦");
        System.out.println(adPage);
        return adPage;
    }

}
