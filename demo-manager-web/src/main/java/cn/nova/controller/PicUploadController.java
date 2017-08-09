package cn.nova.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.nova.common.utils.IDUtils;

@Controller
public class PicUploadController {

	@RequestMapping("/pic/upload")
	@ResponseBody
	public Map Upload(MultipartFile uploadFile) {
		Map map = new HashMap<>();
		try {
			String filename = uploadFile.getOriginalFilename();
			String extName = filename.substring(filename.lastIndexOf("."));
			String realName = IDUtils.genImageName() + extName;
			uploadFile.transferTo(new File("E:\\Develop\\Upload\\Images\\" + realName));
			map.put("error", 0);
			map.put("url", "E:\\Develop\\Upload\\Images\\" + realName);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "图片上传失败！");
		}
		return map;
	}
}
