package com.se.songs.mapper;

import com.se.songs.model.Type;
import com.se.songs.model.TypeDto;

import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class TypeMapperTest {
    private static TypeDto T1;
    private static TypeDto T2;

    private final TypeMapper mapper = Mappers.getMapper(TypeMapper.class);


    @BeforeClass
    public static void setup() {
        T1 = new TypeDto(Type.T1.getDescription());
        T2 = new TypeDto(Type.T2.getDescription());
    }

    @Test
    public void testMapping() {
        testMapping(Type.T1, T1);
        testMapping(Type.T2, T2);
    }

    private void testMapping(Type input, TypeDto expected) {
        TypeDto actual = TypeMapper.INSTANCE.typeToTypeDto(input);

        System.out.println("Type Dto: " + actual.getDescription());
        assertEquals(expected, actual);
    }
}