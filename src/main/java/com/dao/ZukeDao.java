package com.dao;

import com.entity.ZukeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZukeView;

/**
 * 租客 Dao 接口
 *
 * @author 
 */
public interface ZukeDao extends BaseMapper<ZukeEntity> {

   List<ZukeView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
