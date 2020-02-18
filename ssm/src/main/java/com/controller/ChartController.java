package com.controller;


import com.pojo.Goods;
import com.service.TeacherService;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;


@Controller
@EnableScheduling
public class ChartController {
    @Autowired
    TeacherService teacherService;

    //
//    @RequestMapping("/test1")
//    @Scheduled(cron = "0 0/1 * * * ?")//每隔1min执行一次
//    public String configureTasks() throws InterruptedException {
//        System.out.println("执行定时任务成功");
//}
    
//    跳转到报表页面
    @RequestMapping("/test1")
    public String resultmap() {

        //System.out.println(""+teacherService.findOrder());
        return "/resultmap";
    }

//定时从基础表（order1）中抽取数据添加到order2中
    @Scheduled(cron = "0 0/1 * * * ?")//每隔1min执行一次
    public void configureTasks() throws InterruptedException {
        System.out.println("执行定时任务成功");
        teacherService.deleteOrder2();
        List<Goods> goods=teacherService.findOrder();
        for (int i = 0; i < goods.size(); i++) {//遍历，添加到order2
            teacherService.addOrder2(goods.get(i));
            System.out.println(goods.get(i));
        }

}

    //显示每个地区销售状况柱状图
    @RequestMapping("/getBarChart")
    public ModelAndView getBarChart(HttpServletRequest request, ModelMap modelMap) throws Exception{
        List<Goods> goods=teacherService.findOrder();
        teacherService.getBarChartTools(goods,modelMap,request);
        return new ModelAndView("barChart" ,modelMap);

   }


    //显示每个地区销售状况饼状图
    @RequestMapping("/getPieChart")
    public ModelAndView getPieChart(HttpServletRequest request, ModelMap modelMap) throws Exception{

        List<Goods> goods=teacherService.findOrder();
        teacherService.getPieChartTools(goods,modelMap,request);
        return new ModelAndView("pieChart" ,modelMap);
    }

    //显示每个地区销售状况曲线图
    @RequestMapping("/getLineChart")
    public ModelAndView getLineChart(HttpServletRequest request, ModelMap modelMap) throws Exception {

        List<Goods> goods = teacherService.findOrderData();
        teacherService.getLineChartTools(goods,modelMap,request);
        return new ModelAndView("lineChart" ,modelMap);
    }

    //显示某一类商品柱状图
    @RequestMapping("/getBarChart2")
    public ModelAndView getBarChart2(int goodsId,HttpServletRequest request, ModelMap modelMap) throws Exception{

        //System.out.println("111");
        List<Goods> goods=teacherService.findOrderByGoodsId(goodsId);
        //System.out.println(goods);
        teacherService.getBarChartTools(goods,modelMap,request);
        return new ModelAndView("barChart" ,modelMap);

    }


    //显示某一类商品饼状图
    @RequestMapping("/getPieChart2")
    public ModelAndView getPieChart2(int goodsId,HttpServletRequest request, ModelMap modelMap) throws Exception{

        List<Goods> goods=teacherService.findOrderByGoodsId(goodsId);
        teacherService.getPieChartTools(goods,modelMap,request);
        return new ModelAndView("pieChart" ,modelMap);
    }

    //显示某一类商品曲线图
    @RequestMapping("/getLineChart2")
    public ModelAndView getLineChart2(int goodsId,HttpServletRequest request, ModelMap modelMap) throws Exception {

        List<Goods> goods = teacherService.findOrderDataByGoodsId(goodsId);
        teacherService.getLineChartTools(goods,modelMap,request);
        return new ModelAndView("lineChart" ,modelMap);
    }
}
