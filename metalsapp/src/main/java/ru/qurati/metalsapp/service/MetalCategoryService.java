package ru.qurati.metalsapp.service;

import ru.qurati.metalsapp.model.MetalCategory;
import ru.qurati.metalsapp.repository.MetalCategoryDao;

import java.util.List;

public class MetalCategoryService {
    private MetalCategoryDao metalCategoryDao = new MetalCategoryDao();

    public MetalCategoryService() {
    }

    public List<MetalCategory> findAll() {
        return metalCategoryDao.findAll();
    }

    public MetalCategory findOne(final long id) {
        return metalCategoryDao.findOne(id);
    }

    public void save(final MetalCategory entity) {
        if (entity == null)
            return;
        metalCategoryDao.save(entity);
    }

    public void update(final MetalCategory entity) {
        if (entity == null)
            return;
        metalCategoryDao.update(entity);
    }

    public void delete(final MetalCategory entity) {
        if (entity == null)
            return;
        metalCategoryDao.delete(entity);
    }

    public void deleteById(final Long id) {
        if (id == null)
            return;
        metalCategoryDao.deleteById(id);
    }
}