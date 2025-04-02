package com.example.movierating.Service;

import com.example.movierating.db.po.Collection;
import java.util.List;

public interface CollectionService {
    /**
     * 获取用户的收藏夹（包含所有收藏的电影）
     * @param userId 用户ID
     * @return 用户的收藏夹，如果不存在则返回null
     */
    List<Collection> getUserCollection(Integer userId);

    /**
     * 添加电影到用户收藏夹
     * @param userId 用户ID
     * @param movieId 电影ID
     * @return 新增的收藏记录
     */
    Collection addMovieToUserCollection(Integer userId, Integer movieId);

    /**
     * 检查用户是否收藏了特定电影
     * @param userId 用户ID
     * @param movieId 电影ID
     * @return 是否已收藏
     */
    boolean hasUserCollectedMovie(Integer userId, Integer movieId);

    /**
     * 从用户收藏夹中移除电影
     * @param userId 用户ID
     * @param movieId 电影ID
     * @return 操作是否成功
     */
    boolean removeMovieFromCollection(Integer userId, Integer movieId);
}