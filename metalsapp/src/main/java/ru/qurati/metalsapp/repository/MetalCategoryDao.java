package ru.qurati.metalsapp.repository;

import ru.qurati.metalsapp.model.MetalCategory;

public class MetalCategoryDao extends BaseDao<MetalCategory> {
    public MetalCategoryDao() {
        super(MetalCategory.class);
    }
}