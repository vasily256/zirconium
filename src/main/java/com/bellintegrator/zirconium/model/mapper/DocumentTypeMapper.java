package com.bellintegrator.zirconium.model.mapper;

import com.bellintegrator.zirconium.model.DocumentType;
import com.bellintegrator.zirconium.view.DocumentTypeView;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeMapper implements Mapper<DocumentType, DocumentTypeView> {
    private final MapperFacade mapperFacade;

    @Autowired
    public DocumentTypeMapper(MapperFactory mapperFactory) {
        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public DocumentType toEntity(DocumentTypeView view) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DocumentTypeView toView(DocumentType entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DocumentType> toEntityList(Iterable<DocumentTypeView> views) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<DocumentTypeView> toViewList(Iterable<DocumentType> entities) {
        return mapperFacade.mapAsList(entities, DocumentTypeView.class);
    }
}
