package com.service.impl;

import com.mapper.TeacherMapper;
import com.pojo.Goods;
import com.pojo.Teacher;
import com.service.TeacherService;
import com.tool.BarChartTools;
import com.tool.LineChartTools;
import com.tool.PieChartTools;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMappepr;

    @Override
    public void add(Teacher teacher) {

        teacherMappepr.add(teacher);
        teacherMappepr.add2(teacher);
    }

    @Override
    public void delete(int id) {

        teacherMappepr.delete(id);
        teacherMappepr.delete2(id);
    }

    @Override
    public void update(Teacher teacher) {

        teacherMappepr.update(teacher);
        teacherMappepr.updata2(teacher);
    }

    @Override
    public Teacher findById(int id) {
        return teacherMappepr.findById(id);
    }

    @Override
    public Teacher findByIdAndName(int id, String name) {

        return teacherMappepr.findByIdAndName(id, name);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherMappepr.findAll();
    }

    @Override
    public  void save(Teacher teacher){
        teacherMappepr.add(teacher);
        teacherMappepr.add2(teacher);
    }

    //excle导入
    @Override
    public void uploadExcle(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        //获取服务器端路径
        String path=request.getServletContext().getRealPath("upload");
        //获取到上传文件名称
        String fileName=file.getOriginalFilename();
        //创建目标File
        File targetFile=new File(path+"\\"+fileName);
        //创建存储目录
        File targetPath=new File(path);

        //判断服务器端目录是否存在,如果不存在创建目录
        if(!targetPath.exists()){
            targetPath.mkdir();
        }
        //把上传的文件存储到服务器端
        try {
            file.transferTo(targetFile);
        } catch (IllegalStateException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        //读取上传到服务器端的文件,遍历excel
        try {
            Workbook workbook= WorkbookFactory.create(targetFile);
            Sheet sheet = workbook.getSheet("Sheet1");
            //判断行数
            int rownum = sheet.getPhysicalNumberOfRows();
            for(int i=1;i<rownum;i++){
                Row row = sheet.getRow(i);
                //判断单元格数量
                int cellnum = row.getPhysicalNumberOfCells();
                StringBuffer buf=new StringBuffer();
                for(int j=0;j<cellnum;j++){
                    Cell cell = row.getCell(j);
                    if(cell.getCellTypeEnum()== CellType.STRING){
                        buf.append(cell.getStringCellValue()+"~");
                    }else if(cell.getCellTypeEnum()==CellType.NUMERIC){
                        //创建数字格式化工具类
                        DecimalFormat df=new DecimalFormat("####");
                        //把从cell单元格读取到的数字,进行格式化防止科学计数法形式显示
                        buf.append(df.format(cell.getNumericCellValue())+"~");
                    }
                }
                //单元格循环完成后读取到的是一行内容  1~xingming~88
                String hang=buf.toString();
                String[] rows=hang.split("~");
                Teacher tch=new Teacher();
                tch.setId(Integer.valueOf(rows[0]));
                tch.setName(rows[1]);
                tch.setAge(Integer.valueOf(rows[2]));
                tch.setMajor(rows[3]);
                tch.setDetail(rows[4]);
                System.out.println("上传信息:"+tch);
                teacherMappepr.add(tch);
                teacherMappepr.add2(tch);
            }
        } catch (InvalidFormatException | IOException e) {

            e.printStackTrace();
        }
    }

//    //excle导出
//    @Override
//    public ResponseEntity<byte[]> down(HttpServletRequest request) throws IOException{
//        // 从数据库读取数据
//        List<Teacher> allTch = teacherMappepr.findAll();
//        // 获取服务路径
//        String path = request.getServletContext().getRealPath("down");
//        String filename = "demo.xlsx";
//        // 存储File
//        File tfile = new File(path + "\\" + filename);
//        // 目录
//        File mfile = new File(path);
//        if (!tfile.exists()) {
//            mfile.mkdir();
//        }
//        // 生成excel
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("学员信息表");
//        int rownum = 0;
//        for (Teacher tch : allTch) {
//            XSSFRow row = sheet.createRow(rownum);
//            row.createCell(0).setCellValue(tch.getId());
//            row.createCell(1).setCellValue(tch.getName());
//            row.createCell(2).setCellValue(tch.getAge());
//            row.createCell(3).setCellValue(tch.getMajor());
//            row.createCell(4).setCellValue(tch.getDetail());
//            rownum++;
//        }
//        // 保存workbook
//        try {
//            workbook.write(new FileOutputStream(tfile));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 创建请求头对象
//        HttpHeaders headers = new HttpHeaders();
//        // 下载显示的文件名，解决中文名称乱码问题
//        String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
//        // 通知浏览器以attachment(下载方式)打开
//        headers.setContentDispositionFormData("attachment", downloadFileName);
//        // application/octet-stream:二进制流数据（最常见的文件下载）
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(tfile),
//                headers, HttpStatus.CREATED);
//        return responseEntity;
//    }
    @Override
    public List<Goods> findOrder(){
        return   teacherMappepr.findOrder();
    }

    @Override
    public  List<Goods> findOrderData(){
        return teacherMappepr.findOrderData();
    }

    @Override
    public ModelMap getBarChartTools(List<Goods> goods, ModelMap modelMap,
                                     HttpServletRequest request)throws Exception {
        // 获得数据

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(int i=0;i<goods.size();i++){//遍历，添加到dateset
            dataset.addValue(goods.get(i).getSum(), "",goods.get(i).getAreas());
        }
        // 获取柱状图工具类创建的柱状图，（将数据集传入）
        JFreeChart chart = BarChartTools.createBarChart(dataset);
        //6. 将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        String chartURL=request.getContextPath() + "/chart?filename="+fileName;
        modelMap.put("chartURL", chartURL);
        //System.out.println("image:"+chartURL);
        return modelMap;

    }

    @Override
    public ModelMap getPieChartTools(List<Goods> goods, ModelMap modelMap,
                                     HttpServletRequest request)throws Exception{
        //获得数据
        DefaultPieDataset dataset = new DefaultPieDataset();
        for(int i=0;i<goods.size();i++){//遍历，添加到dateset
            dataset.setValue(goods.get(i).getAreas(),goods.get(i).getSum());
        }
        // 获取饼状图工具类创建的柱状图，（将数据集传入）
        JFreeChart chart = PieChartTools.createPieChart(dataset);
        //将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        String pieChartURL=request.getContextPath() + "/chart?filename="+fileName;
        modelMap.put("pieChartURL", pieChartURL);
        return modelMap;
    }
    @Override
    public ModelMap getLineChartTools(List<Goods> goods, ModelMap modelMap,
                                      HttpServletRequest request)throws Exception{
        //获得数据
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        for (int i = 0; i < goods.size(); i++) {//遍历，添加到dateset
           ds.setValue(goods.get(i).getSum(),"",goods.get(i).getOrderdata());
        }
        // 获取曲线图工具类创建的柱状图，（将数据集传入）
        JFreeChart chart = LineChartTools.createLineChart(ds);
        //将图形转换为图片，传到前台
        String fileName = ServletUtilities.saveChartAsJPEG(chart, 700, 400, null, request.getSession());
        String lineChartURL=request.getContextPath() + "/chart?filename="+fileName;
        modelMap.put("lineChartURL", lineChartURL);
        return modelMap;
    }

    @Override
    public List<Goods> findOrderByGoodsId(int goodsId){
        return teacherMappepr.findOrderByGoodsId(goodsId);
    }

    @Override
    public  List<Goods> findOrderDataByGoodsId(int goodsId){
        return teacherMappepr.findOrderDataByGoodsId(goodsId);
    }

    @Override
    public void deleteOrder2(){
         teacherMappepr.deleteOrder2();
    }
    @Override
    public void addOrder2(Goods goods){
        teacherMappepr.addOrder2(goods);
    }
}
