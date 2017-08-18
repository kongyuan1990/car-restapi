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

import com.smartparking.car.manager.bean.TCert;
import com.smartparking.car.manager.bean.TCertMember;
import com.smartparking.car.manager.bean.TUserMember;
import com.smartparking.car.restapi.bean.SmartCarReturn;
import com.smartparking.car.restapi.service.CertMemberService;
import com.smartparking.car.restapi.service.TUserMemberService;

@RequestMapping("/cert")
@RestController
public class CarHostApproveController {

	@Autowired
	CertMemberService certService;
	
	@Autowired
	TUserMemberService tUserMemberService;
	
	@RequestMapping("/upload")
    public SmartCarReturn<Object> upload(HttpSession session,
            @RequestParam("file")MultipartFile[] file,
            @RequestParam(value="certId")Integer[] certid,@RequestParam(value="memberId")Integer memberid){
        
        try {
            List<TCertMember> certsList= new ArrayList<TCertMember>();
            for (int i=0;i<certid.length;i++) {
                TCertMember cert = new TCertMember();
                MultipartFile multipartFile = file[i];
                String uploadfile = uploadfile("/certsimg", multipartFile, session);
                cert.setCertId(certid[i]);
                cert.setMemberId(memberid);
                cert.setIconpath(uploadfile);
                certsList.add(cert);
            }
            //调用业务逻辑进行保存;/删除原有资质，保存新的资质
            certService.insertCerts(certsList);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(e);
            return SmartCarReturn.fail("资质保存失败！", null, null);
        }
        
        return SmartCarReturn.success("保存成功！", null, null);
    }
	
	@RequestMapping("/getCerts")
	public SmartCarReturn<List<TCert>> getCerts(@RequestParam("type")Integer type,
			TUserMember userMember) {
		List<TCert> certsByType = null;
		try {
			tUserMemberService.updateTUserMember(userMember);
			certsByType = certService.getCertsByType(type);
		} catch (Exception e) {
			e.printStackTrace();
			return SmartCarReturn.fail("service异常", null, null);
		}
		if(certsByType == null){
			return SmartCarReturn.fail("certsByType=null", null, null);
		}else{
			return SmartCarReturn.success("获取成功", certsByType, null);
		}

	}
	
	@RequestMapping("/endInfo")
	public SmartCarReturn<Object> endInfo(TUserMember userMember){
		
		try {
			tUserMemberService.updateTUserMember(userMember);
			return SmartCarReturn.success("信息填写完成", null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return SmartCarReturn.fail("service异常", null, null);
		}
		
	}
	
//	@RequestMapping("/sendEmail")
//	public SmartCarReturn<Object> sendEmail(@RequestParam("id")Integer id,@RequestParam("email")String email){
//		try {
//			tUserMemberService.sendEmail(id,email);
//			 return SmartCarReturn.success("邮箱发送成功", null, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return SmartCarReturn.fail("邮箱发送失败", null, null);
//		}
//		
//	}
	
	
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
