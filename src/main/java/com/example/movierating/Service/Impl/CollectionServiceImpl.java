package com.example.movierating.Service.Impl;
import com.example.movierating.Service.CollectionService;
import com.example.movierating.db.dao.CollectionDao;
import com.example.movierating.db.po.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    @Override
    public List<Collection> getUserCollection(Integer userId) {
        // 获取用户的所有收藏记录
        List<Collection> collections = collectionDao.getCollectionsByUserId(userId);
        return collections;
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