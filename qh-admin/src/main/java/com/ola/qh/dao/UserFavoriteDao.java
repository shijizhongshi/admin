package com.ola.qh.dao;

import org.apache.ibatis.annotations.Param;

public interface UserFavoriteDao {

	public int insertUpdateFavorite(@Param("productId")String productId);
}
