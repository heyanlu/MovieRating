package com.example.movierating.db.dao;

import com.example.movierating.db.po.Collection;
import java.util.List;

public interface CollectionDao {
    /**
     * 选择性创建一个新的收藏
     * @param collection 收藏信息
     * @return 受影响的行数
     */
    int insertCollectionSelective(Collection collection);

    /**
     * 获取用户的所有收藏
     * @param userId 用户ID
     * @return 收藏列表
     */
    List<Collection> getCollectionsByUserId(Integer userId);

    /**
     * 获取用户对特定电影的收藏
     * @param userId 用户ID
     * @param movieId 电影ID
     * @return 收藏信息
     */
    Collection getCollectionByUserIdAndMovieId(Integer userId, Integer movieId);

    /**
     * 选择性更新收藏信息
     * @param collection 收藏信息
     * @return 受影响的行数
     */
    int updateCollectionByPrimaryKeySelective(Collection collection);

    /**
     * 删除用户对特定电影的收藏
     * @param userId 用户ID
     * @param movieId 电影ID
     * @return 受影响的行数
     */
    int deleteCollectionByUserIdAndMovieId(Integer userId, Integer movieId);
}