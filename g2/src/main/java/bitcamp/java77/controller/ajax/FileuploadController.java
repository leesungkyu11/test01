package bitcamp.java77.controller.ajax;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java77.domain.AjaxResult;
import bitcamp.java77.domain.Board;
import bitcamp.java77.util.MultipartHelper;

@Controller("ajax.FileuploadController")
@RequestMapping("/fileupload/ajax/*")
public class FileuploadController { 
  static Logger logger = Logger.getLogger(FileuploadController.class);
  
  public static final String SAVED_DIR = "/attachfile";
  @Autowired ServletContext servletContext;
      
  @RequestMapping(value="add", method=RequestMethod.POST)
  public AjaxResult add(
      Board board, 
      @RequestParam("files") MultipartFile[] files) throws Exception {
    ArrayList<String> filenames = new ArrayList<>();

    for (MultipartFile file : files) {
      if (file.getSize() > 0) {
        String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
        File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                    + "/" + newFileName);
        file.transferTo(attachfile);
        filenames.add(newFileName);
      }
    }

    logger.debug("fileupload...");
    
    return new AjaxResult("success", filenames);
  }
 
}
