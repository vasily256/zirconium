package com.bellintegrator.zirconium.model.mapper;

import com.bellintegrator.zirconium.model.Phone;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PhoneConverter extends CustomConverter<Set<String>, Set<Phone>> {
    @Override
    public Set<Phone> convert(Set<String> phones,
                              Type<? extends Set<Phone>> type,
                              MappingContext mappingContext) {

        if (phones == null) {
            return null;
        }

        return phones.stream().map(Phone::new).collect(Collectors.toSet());
    }
}
