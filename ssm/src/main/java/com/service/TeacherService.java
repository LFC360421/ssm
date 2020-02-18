package com.service;

import com.pojo.Goods;
import com.pojo.Teacher;
import org.jfree.chart.JFreeChart;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface TeacherService {
    void add(Teacher teacher);

    void delete(int id);

    void update(Teacher teacher);

    Teacher findById(int id);

    Teacher findByIdAndName(int id,String name);

    List<Teacher> findAll();

    void save(Teacher teacher);

    void uploadExcle(@RequestParam("file") MultipartFile file, HttpServletRequest request);

//    ResponseEntity<byte[]> down(HttpServletRequest request)throws IOException;

    List<Goods> findOrder();

    List<Goods> findOrderData();

    ModelMap getBarChartTools(List<Goods> goods, ModelMap modelMap,
                              HttpServletRequest request)throws Exception;
    ModelMap getPieChartTools(List<Goods> goods, ModelMap modelMap,
                              HttpServletRequest request)throws Exception;
    ModelMap getLineChartTools(List<Goods> goods, ModelMap modelMap,
                              HttpServletRequest request)throws Exception;

    List<Goods> findOrderByGoodsId(int goodsId);

    List<Goods> findOrderDataByGoodsId(int goodsId);

    void deleteOrder2();
    void addOrder2(Goods goods);

}
