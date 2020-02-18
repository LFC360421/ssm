package com.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pojo.Page;
import com.pojo.Teacher;
import com.service.TeacherService;


import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Controller
public class CurdController {
    @Autowired
    TeacherService teacherService;

//    登录处理
    @RequestMapping(value = "/")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(int id,String name, Model model){
        Teacher list=teacherService.findByIdAndName(id,name);
        if(list!=null){
            return "redirect:/getAll";
        }
        model.addAttribute("error","工号或姓名错误！");
        return "login";
    }

//分页查询所有信息
    @RequestMapping("/getAll")
//    修改findAllTeacher,接受分页信息的注入
    public String findAllTeacher(ModelMap map, Page page){
//        在调用teacherService.findAll();之前执行,实现分页
        PageHelper.offsetPage(page.getStart(),4);
        List<Teacher> ts=teacherService.findAll();
        int total = (int) new PageInfo<>(ts).getTotal();
        page.caculateLast(total);
        map.addAttribute("ts",ts);
        map.addAttribute("total",total);
        return "getAll";
    }
//    通过id查询
    @RequestMapping("/getById")
    public String findById(int id, ModelMap map){
//        if(null != id) {
            Teacher ls = teacherService.findById(id);
            if (ls != null) {
                map.addAttribute("ls", ls);
                return "getById";
            }
            return "redirect:/getAll";
//        }

    }

//    添加信息
    @RequestMapping("/add")
    public String add( Teacher teacher){
//        //获取校验的错误信息
//        if(result.hasErrors()) {
//            //输出错误信息
//            List<ObjectError> allErrors = result.getAllErrors();
//            for(ObjectError error : allErrors) {
//                //将每一条错误信息传到model中，以用来页面展示
//                model.addAttribute(error.getCode(), error.getDefaultMessage());
//            }
//            //将全部错误信息传到model中，以用来页面展示
//            model.addAttribute("allErrors", allErrors);
//            return "getAll";
//        }else {

            teacherService.add(teacher);
            return "redirect:/getAll";
       // }
    }

//    删除信息
    @RequestMapping("/delete")
    public String delete(int id){
        teacherService.delete(id);
        return "redirect:/getAll";
    }

//    跳转编辑页面
    @RequestMapping("/edit")
    public String edit(int id,ModelMap map){
        Teacher t=teacherService.findById(id);
        map.addAttribute("t",t);
        return "edit";
    }

//修改信息
    @RequestMapping("/update")
    public String update(Teacher teacher){
        teacherService.update(teacher);
        return "redirect:/getAll";
    }

//跳转到excle导入导出处理页面
    @RequestMapping("/excelupload")
    public String toExcelupload(){

        return "excelupload";
    }
//excle导入
    @RequestMapping("/importexcel")
    public String uploadExcle(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        teacherService.uploadExcle( file, request);
        return "success";
    }
//    public String uploadExcel(@RequestParam("file") MultipartFile file, HttpServletRequest request){
//        //System.out.println("已跳转");
//        //获取服务器端路径
//        String path=request.getServletContext().getRealPath("upload");
//        //获取到上传文件名称
//        String fileName=file.getOriginalFilename();
//        //创建目标File
//        File targetFile=new File(path+"\\"+fileName);
//        //创建存储目录
//        File targetPath=new File(path);
//
//        //判断服务器端目录是否存在,如果不存在创建目录
//        if(!targetPath.exists()){
//            targetPath.mkdir();
//        }
//        //把上传的文件存储到服务器端
//        try {
//            file.transferTo(targetFile);
//        } catch (IllegalStateException e) {
//
//            e.printStackTrace();
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//        //读取上传到服务器端的文件,遍历excel
//        try {
//            Workbook workbook= WorkbookFactory.create(targetFile);
//            Sheet sheet = workbook.getSheet("Sheet1");
//            //判断行数
//            int rownum = sheet.getPhysicalNumberOfRows();
//            for(int i=1;i<rownum;i++){
//                Row row = sheet.getRow(i);
//                //判断单元格数量
//                int cellnum = row.getPhysicalNumberOfCells();
//                StringBuffer buf=new StringBuffer();
//                for(int j=0;j<cellnum;j++){
//                    Cell cell = row.getCell(j);
//                    if(cell.getCellTypeEnum()== CellType.STRING){
//                        buf.append(cell.getStringCellValue()+"~");
//                    }else if(cell.getCellTypeEnum()==CellType.NUMERIC){
//                        //创建数字格式化工具类
//                        DecimalFormat df=new DecimalFormat("####");
//                        //把从cell单元格读取到的数字,进行格式化防止科学计数法形式显示
//                        buf.append(df.format(cell.getNumericCellValue())+"~");
//                    }
//                }
//                //单元格循环完成后读取到的是一行内容  1~xingming~88~aa
//                String hang=buf.toString();
//                String[] rows=hang.split("~");
//                Teacher tch=new Teacher();
//                tch.setId(Integer.valueOf(rows[0]));
//                tch.setName(rows[1]);
//                tch.setAge(Integer.valueOf(rows[2]));
//                tch.setMajor(rows[3]);
//                tch.setDetail(rows[4]);
//                System.out.println("上传信息:"+tch);
//                teacherService.save(tch);
//            }
//        } catch (InvalidFormatException | IOException e) {
//
//            e.printStackTrace();
//        }
//
//        return "success";
//    }

    //导出excle
    @RequestMapping("/downloadexcel")
    public ResponseEntity<byte[]> down(HttpServletRequest request)
            throws IOException {
        //System.out.println("11111");
        // 从数据库读取数据
        List<Teacher> allTch = teacherService.findAll();
        // 获取服务路径
        String path = request.getServletContext().getRealPath("down");
        String filename = "信息表.xlsx";
        // 存储File
        File tfile = new File(path + "\\" + filename);
        // 目录
        File mfile = new File(path);
        if (!tfile.exists()) {
            mfile.mkdir();
        }
        // 生成excel
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建一个excel标签页
        XSSFSheet sheet = workbook.createSheet("学员信息表");
        //创建第一行 放表头
        XSSFRow row1 = sheet.createRow(0);
        row1.setHeightInPoints(20);
        //创建列
        XSSFCell cell = row1.createCell(1);

        cell = row1.createCell(0);
        cell.setCellValue("编号");

        cell = row1.createCell(1);
        cell.setCellValue("姓名");


        cell = row1.createCell(2);
        cell.setCellValue("年龄");

        cell = row1.createCell(3);
        cell.setCellValue("专业");
        cell=row1.createCell(4);
        cell.setCellValue("其他信息");
        int rownum = 1;
        for (Teacher tch : allTch) {
            XSSFRow row = sheet.createRow(rownum);
            row.createCell(0).setCellValue(tch.getId());
            row.createCell(1).setCellValue(tch.getName());
            row.createCell(2).setCellValue(tch.getAge());
            row.createCell(3).setCellValue(tch.getMajor());
            row.createCell(4).setCellValue(tch.getDetail());
            rownum++;
        }
        // 保存workbook
        try {
            workbook.write(new FileOutputStream(tfile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建请求头对象
        HttpHeaders headers = new HttpHeaders();
        // 下载显示的文件名，解决中文名称乱码问题
        String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        // 通知浏览器以attachment(下载方式)打开
        headers.setContentDispositionFormData("attachment", downloadFileName);
        // application/octet-stream:二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(tfile),
                headers, HttpStatus.CREATED);
        return responseEntity;
    }

}
