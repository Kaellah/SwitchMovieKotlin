package com.kaellah.domain.util;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class Mapper<To, From> {

    public abstract To map(@NotNull From from, @Nullable Object payload);

    public From reverseMap(@NotNull To to, @Nullable Object payload){
        throw notImplementedException();
    }

    public AbstractMethodError notImplementedException() {
        return new AbstractMethodError("Not implemented method");
    }

    public List<To> mapList(List<From> typeList,@Nullable Object payload) {

        List<To> list = new ArrayList<>();

        for (From type : typeList) {

            list.add(map(type, payload));

        }

        return list;

    }

    public List<From> reverseMapList(List<To> typeList,@Nullable Object payload) {

        List<From> list = new ArrayList<>();

        for (To type : typeList) {

            list.add(reverseMap(type, payload));

        }

        return list;

    }

}