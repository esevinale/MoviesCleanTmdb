package com.esevinale.movieguidetmdb.presentation.view.views;

import com.esevinale.movieguidetmdb.presentation.model.details.CastModel;

import java.util.List;

public interface CastListView extends LoadDataView {

    void renderCastList(List<CastModel> castModelList);
    void addCastToList(List<CastModel> castModelList);

    void viewCast(CastModel castModel);
}