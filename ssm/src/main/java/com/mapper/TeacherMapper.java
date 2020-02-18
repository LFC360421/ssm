package com.mapper;

import com.pojo.Goods;
import com.pojo.Order;
import com.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {

    int add(Teacher teacher);
    int add2(Teacher teacher);

    int delete(int id);
    int delete2(int id);

    // 返回值为int,表示修改的行数
    int update(Teacher teacher);
    int updata2(Teacher teacher);

    Teacher findById(int id);

    /* 多参数时,注意一定要在参数前加上@Param来匹配相同参数名的参数,
       否则会报: Parameter 'XXX' not found. Available parameters are [1, 0, param1, param2] 参数不匹配错误*/
    Teacher findByIdAndName(@Param("id")int id, @Param("name")String name);

    List<Teacher> findAll();

    List<Goods> findOrder();

    List<Goods> findOrderByGoodsId(int goodsId);

    List<Goods> findOrderData();

    List<Goods> findOrderDataByGoodsId(int goodsId);

    int deleteOrder2();

    int addOrder2(Goods goods);
}
