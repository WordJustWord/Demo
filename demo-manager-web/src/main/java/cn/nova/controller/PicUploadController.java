package cn.nova.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.nova.common.utils.FastDFSClient;
import cn.nova.common.utils.IDUtils;

@Controller
public class PicUploadController {

	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;

	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map Upload(MultipartFile uploadFile) {
		Map map = new HashMap<>();
		try {
			String filename = uploadFile.getOriginalFilename();
			String extName = filename.substring(filename.lastIndexOf(".") + 1);
			// 上传文件到图片
			FastDFSClient dfsClient = new FastDFSClient("classpath:configers/client.conf");
			String url = dfsClient.uploadFile(filename.getBytes(), extName);
			url = IMAGE_SERVER_URL + url;
			map.put("error", 0);
			map.put("url", url);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "图片上传失败！");
		}
		return map;
	}
}
