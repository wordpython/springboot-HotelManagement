package com.wordpython.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.wordpython.admin.entity.Photo;
import com.wordpython.admin.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 
 * 上传文件，图片
 * 测试成功
 * 设置上传文件大小限制后可上传上传.zip等大文件
 * 
 * @author wordpython
 *
 */

@Controller
@RequestMapping("/photo")
public class uploadPhotoOrFileController {
    @Autowired
    private RoomService roomService;

	/*
	 * 上传文件或图片（调用该方法页面没有回显）
	 */
	@RequestMapping("load")
	public String send() {
		return "uploadPhotoOrFile";
	}
	/*
	 * 上传图片并回显(js直接回显，点击保存后再上传图片)
	 */
	@RequestMapping("load2")
	public String send2() {
		return "uploadPhotoOrFile2";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public JSONObject Upload(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println(file);
		//返回json串，如果返回字符串的话，前端会报上传接口异常
		JSONObject res=new JSONObject();
		if (file.isEmpty()) {
			res.put("msg","请选择图片/文件");
			return res;
		}
//		String contentType = file.getContentType(); // 图片文件类型
		String fileName = file.getOriginalFilename(); // 图片名字
		/*
		这里使用相对路径，因为是相对路径，启动项目后，
		该路径跟随项目变成动态路径，就可以实现展示上传的图片
		*/
		System.out.println("当前项目路径"+new File("").getCanonicalPath());
		String path=new File("").getCanonicalPath()+"/HotelManagement/src/main/webapp/static/images/";// 文件存的路径
		String filePath = path + fileName;//路径+文件名
		File fileImage = new File(filePath);// 1. 创建图片文件
		System.out.println("---fileImage:"+fileImage.getAbsolutePath());
		if (!fileImage.getParentFile().exists()) {
			fileImage.getParentFile().mkdirs();
			System.out.println("保存文件的目录不存在，已经创建");
		}
		try {
			uploadFile(file.getBytes(), filePath);// 2. 将字节流写入filePath文件
			res.put("msg","上传成功");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			res.put("msg","服务器错误，重新上传");
			return res;
		}
	}
	
	//这里整为工具类
	public static void uploadFile(byte[] file, String filePath) throws Exception {
//		File targetFile = new File(filePath);
//		if (targetFile.exists()) {
//			targetFile.mkdirs();
//			System.out.println("1111111");
//		}
		FileOutputStream out = new FileOutputStream(filePath);
		out.write(file);
		out.flush();
		out.close();
	}

	/**
	 * @function 多文件上传
	 * @param files
	 * @return
	 */
	@PostMapping("/uploadMany")
	@ResponseBody
	public JSONObject UploadImages(@RequestParam(value = "file", required = false) MultipartFile[] files, HttpSession session) throws IOException {
		System.out.println(files);
		//返回json串，如果返回字符串的话，前端会报上传接口异常
		JSONObject res=new JSONObject();
		if(files.length == 0){
			res.put("msg","图片为空！");
			return res;
		}else{
			/*
			这里使用相对路径，因为是相对路径，启动项目后，
			该路径跟随项目变成动态路径，就可以实现展示上传的图片
			*/
//			String  path= "C:/major/images/";// 文件存的路径
            System.out.println("当前项目路径"+new File("").getCanonicalPath());
            // 文件存的路径
            String path=new File("").getCanonicalPath()+"/HotelManagement/src/main/webapp/static/images/";

			String lists = fileMany(files, path,session,roomService);
			res.put("msg","图片上传成功");
			return res;
		}
	}

	/**
	 * @function 多文件上传
	 * @return
	 */
	public static String fileMany(MultipartFile[] files , String saveUrl,HttpSession session,RoomService roomService){
//		List<String> picUrl = null;-
//		String newUrl = saveUrl + uuid;//图片保存路径，images + uuid随机数（路径保存到数据库）
		String newUrl=saveUrl+session.getAttribute("img_url")+"/";//图片文件夹名
        File saveDir = new File(newUrl);//创建路径对象
		if(!saveDir.exists()){
			saveDir.mkdirs();//路径不存在则创建
		}
		for(MultipartFile file : files){
			if(file != null){
				//获取图片拓展名
				String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				//生成图片名字
				String fileName = UUID.randomUUID() + suffix;
				String newFileUrl = newUrl+fileName;
				System.out.println("newFileUrl:"+newFileUrl);
				File saveFile = new File(newFileUrl);
				try {
					file.transferTo(saveFile);
					//将图片id,图片名字和文件名存入数据库
                    String photo_id=UUID.randomUUID().toString();
					String hotel_id=session.getAttribute("hotel_id").toString();
					String img_url="/images/"+session.getAttribute("img_url")+"/";
					Photo photo=new Photo(photo_id,hotel_id,img_url,fileName);
                    session.setAttribute("photo",photo);
					//存入图片信息
					roomService.insertPhoto(photo);
//					picUrl.add(newFileUrl);
				} catch (IOException e) {
					System.out.println("上传失败");
					e.printStackTrace();
				}
			}
		}
		return "";
	}



}
