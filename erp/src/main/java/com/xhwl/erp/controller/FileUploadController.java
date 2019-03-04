package com.xhwl.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.xhwl.erp.util.file.StorageService;
import com.xhwl.erp.util.result.ResultJson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@Api(description="文件上传")
public class FileUploadController {

	 private final StorageService storageService;
	 
	 @Autowired
	 public FileUploadController(StorageService storageService) {
	     this.storageService = storageService;
	 }
	 
	 /******************************* 文件上传 测试页面接口 *******************************/
	 
	 /**
	  * 文件列表展示
	  * @author jiayiwu
	  * @date 2018年2月28日
	  * @param model
	  * @return
	  * @throws IOException
	  */
	 @ApiIgnore
	 @GetMapping("/uploadForm")
	    public String listUploadedFilesForm(Model model) throws IOException{

	        model.addAttribute("files",storageService
	                .loadAll()
	                .map(path -> MvcUriComponentsBuilder
	                            .fromMethodName(FileUploadController.class,"serveFile",path.getFileName().toString())
	                            .build().toString())
	                .collect(Collectors.toList()));

	        return "uploadForm";
	    }
	 
	 /**
	  * 文件上传
	  * @author jiayiwu
	  * @date 2018年2月28日
	  * @param file
	  * @param redirectAttributes
	  * @return
	  */
	 @ApiIgnore
	 @PostMapping("/uploadFiles")
	    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
		    String date=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());  
	        storageService.store(file,date);
	        redirectAttributes.addFlashAttribute("message", " you successfully uploaded " + file.getOriginalFilename() + "!");
	        return "redirect:/uploadForm";
	    }
	 
	 /******************************* 文件上传 真实接口 *******************************/
	 
	 /**
	  * 文件列表展示(json)
	  * @author jiayiwu
	  * @date 2018年2月28日
	  * @param model
	  * @return
	  * @throws IOException
	  */
	 @ApiOperation(value="上传文件列表", notes="上传文件列表")
	 @GetMapping("/files")
	    public @ResponseBody List<String> listUploadedFiles(Model model) throws IOException{

		 List<String> s =  storageService
	                .loadAll()
	                .map(path -> MvcUriComponentsBuilder
	                            .fromMethodName(FileUploadController.class,"serveFile",path.getFileName().toString())
	                            .build().toString())
	                .collect(Collectors.toList());

	        return s;
	    }

	 /**
	  * 文件上传(json)
	  * @author jiayiwu
	  * @date 2018年2月28日
	  * @param file
	  * @param redirectAttributes
	  * @return
	  */
	 @ApiOperation(value="文件上传", notes="文件上传")
	 @PostMapping("/files")
	    @ResponseBody
	    public ResultJson handelFileUpload(@RequestParam("file") MultipartFile file){
		    ResultJson resultJson = new ResultJson(); 
		    String date=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());  
		    /**
		     * 存储文件
		     */
	        storageService.store(file,date);
	        
	        /**
	         * 获得文件的http url路径
	         */
	        Path path = storageService.load(file.getOriginalFilename());
	        String url = MvcUriComponentsBuilder
            .fromMethodName(FileUploadController.class,"serveFile",path.getFileName().toString())
            .build().toString();
	        
	        resultJson.setSuccess(true);
			resultJson.setData(url);
	        return resultJson;
	    } 
	 
	 /**
	  * 读取、下载文件
	  * @author jiayiwu
	  * @date 2018年2月28日
	  * @param filename
	  * @return
	  */
	 @ApiOperation(value="文件下载", notes="文件下载")
	 @GetMapping("/files/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
	        Resource file = storageService.loadAsResource(filename);
	        return ResponseEntity
	                .ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+file.getFilename() + "\"")
	                .body(file);
	    }
}
