package com.smartparking.car.restapi.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smartparking.car.manager.bean.TCarport;
import com.smartparking.car.manager.bean.TCert;
import com.smartparking.car.manager.bean.TCertPortowner;
import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.manager.bean.TUserPortowner;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.CertMemberService;
import com.smartparking.car.restapi.service.CertPortownerService;
import com.smartparking.car.restapi.service.TCarportService;
import com.smartparking.car.restapi.service.TUserPortownerService;

@RequestMapping("PortOwnerAppove")
@RestController
public class PortOwnerAppoveController {
	
	@Autowired
	CertMemberService certService;
	@Autowired
	TUserPortownerService userPortownerService;
	@Autowired
	TCarportService carportService;
	@Autowired
	CertPortownerService certPortownerService;

	@RequestMapping("/updatePortownerInfo")
	public SmartCarReturn<Object> updatePortownerInfo(TUserPortowner userPortowner){
		try {
			userPortownerService.updateTUserPortowner(userPortowner);
			return SmartCarReturn.success("更新成功", null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return SmartCarReturn.fail("service异常", null, null);
		}
		
	}
	
	@RequestMapping("/addCarPort")
	public SmartCarReturn<List<TCert>> addCarPort(@RequestParam("type")Integer type/*,
			TCarport cartPort*/){
		List<TCert> certsByType = null;
		try {
//			cartPort.setRemainingPlace(cartPort.getTotalPlace());
//			int id = carportService.addTCarport(cartPort);
//			TUserPortowner portOwner = new TUserPortowner();
//			portOwner.setCarportId(id);
//			userPortownerService.updateTUserPortowner(portOwner );
			certsByType = certService.getCertsByType(type);
			return SmartCarReturn.success("添加成功", certsByType, null);
		} catch (Exception e) {
			e.printStackTrace();
			return SmartCarReturn.fail("service异常", null, null);
		}
		
	}
	
	@RequestMapping("/upload")
    public SmartCarReturn<Object> upload(HttpSession session,
            @RequestParam("file")MultipartFile[] file,
            @RequestParam(value="certId")Integer[] certid,@RequestParam(value="portownerId")Integer portownerId){
        
        try {
            List<TCertPortowner> certsList= new ArrayList<TCertPortowner>();
            for (int i=0;i<certid.length;i++) {
            	TCertPortowner cert = new TCertPortowner();
                MultipartFile multipartFile = file[i];
                String uploadfile = uploadfile("/certsimg", multipartFile, session);
                cert.setCertId(certid[i]);
                cert.setPortownerId(portownerId);
                cert.setIconpath(uploadfile);
                certsList.add(cert);
            }
            //调用业务逻辑进行保存;/删除原有资质，保存新的资质
            certPortownerService.insertCerts(certsList);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e);
            return SmartCarReturn.fail("资质保存失败！", null, null);
        }
        
        return SmartCarReturn.success("保存成功！", null, null);
    }
	
	@RequestMapping("/endInfo")
	public SmartCarReturn<Object> endInfo(@RequestParam("portownerId")Integer portownerId,TUserPortowner userPortowner  ,TCarport cartPort){
		
		try {
			cartPort.setRemainingPlace(cartPort.getTotalPlace());
			int id = carportService.addTCarport(cartPort);
			userPortowner.setCarportId(id);
			userPortowner.setId(portownerId);
			
			
			userPortownerService.updateTUserPortowner(userPortowner);
			return SmartCarReturn.success("信息填写完成", null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return SmartCarReturn.fail("service异常", null, null);
		}
		
	}
	
	 //文件上传
    private String uploadfile(String webPath,MultipartFile file,HttpSession session){
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath(webPath);
        String name = UUID.randomUUID().toString().replace("-", "").substring(0, 10) +"_file_"+ file.getOriginalFilename();
        try {
            //webPath不存在的情况下必须创建
            File file2 = new File(realPath);
            if(!file2.exists()){
                //创建目录
                file2.mkdirs();
            }
            
            file.transferTo(new File(realPath+"/"+name));
            //返回这个图片在服务器下的路径
            return webPath+"/"+name;
        }catch (Exception e) {
            return null;
        }
    }
}
