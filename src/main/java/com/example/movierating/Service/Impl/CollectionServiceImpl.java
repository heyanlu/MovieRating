package com.example.movierating.Service.Impl;

import com.example.movierating.Service.CollectionService;
import com.example.movierating.db.dao.CollectionDao;
import com.example.movierating.db.po.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Override
    public Collection getUserCollection(Integer userId) {
        // 获取用户的收藏夹
        List<Collection> collections = collectionDao.getCollectionsByUserId(userId);
        // 如果用户有收藏记录，返回第一条记录作为收藏夹
        // 实际上需要根据具体实现调整，可能需要在数据库中添加一个标识字段
        return collections != null && !collections.isEmpty() ? collections.get(0) : null;
    }

    @Override
    @Transactional
    public Collection addMovieToUserCollection(Integer userId, Integer movieId) {
        // 先检查是否已存在该收藏
        Collection existingCollection = collectionDao.getCollectionByUserIdAndMovieId(userId, movieId);
        if (existingCollection != null) {
            // 如果已存在，则直接返回
            return existingCollection;
        }

        // 创建新收藏
        Collection collection = new Collection();
        collection.setUserId(userId);
        collection.setMovieId(movieId);
        collection.setCreateDate(new Date());
        collection.setUpdateDate(new Date());

        collectionDao.insertCollectionSelective(collection);
        return collection;
    }

    @Override
    public boolean hasUserCollectedMovie(Integer userId, Integer movieId) {
        // 检查用户是否收藏了该电影
        Collection collection = collectionDao.getCollectionByUserIdAndMovieId(userId, movieId);
        return collection != null;
    }

    @Override
    @Transactional
    public boolean removeMovieFromCollection(Integer userId, Integer movieId) {
        // 从收藏夹中移除电影
        return collectionDao.deleteCollectionByUserIdAndMovieId(userId, movieId) > 0;
    }
}