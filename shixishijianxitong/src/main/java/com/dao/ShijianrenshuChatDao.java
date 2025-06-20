package com.dao;

import com.entity.ShijianrenshuChatEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShijianrenshuChatView;

/**
 * 任务交流 Dao 接口
 *
 * @author 
 */
public interface ShijianrenshuChatDao extends BaseMapper<ShijianrenshuChatEntity> {

   List<ShijianrenshuChatView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
