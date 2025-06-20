package com.dao;

import com.entity.ShijianrenshuLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShijianrenshuLiuyanView;

/**
 * 任务留言 Dao 接口
 *
 * @author 
 */
public interface ShijianrenshuLiuyanDao extends BaseMapper<ShijianrenshuLiuyanEntity> {

   List<ShijianrenshuLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
