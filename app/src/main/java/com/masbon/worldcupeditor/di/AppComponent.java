package com.masbon.worldcupeditor.di;

import android.content.Context;

import com.masbon.worldcupeditor.core.model.EditorSticker;
import com.masbon.worldcupeditor.core.model.EditorText;
import com.masbon.worldcupeditor.di.modules.AppModule;
import com.masbon.worldcupeditor.di.modules.EditorModule;
import com.masbon.worldcupeditor.presentation.presenters.fragment.AdjustPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.ColorsPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.DrawingPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.FiltersPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.FontsPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.FramesPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.OverlaysPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.StickersPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.StickersSetPresenter;
import com.masbon.worldcupeditor.presentation.presenters.fragment.ToolsPresenter;
import com.masbon.worldcupeditor.ui.dialogs.FontPickerDialog;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, EditorModule.class})
public interface AppComponent {
    Context getContext();

    void inject(ToolsPresenter toolsPresenter);

    void inject(AdjustPresenter adjustPresenter);

    void inject(FiltersPresenter filtersPresenter);

    void inject(OverlaysPresenter overlaysPresenter);

    void inject(FramesPresenter framesPresenter);

    void inject(StickersSetPresenter stickersSetPresenter);

    void inject(StickersPresenter stickersPresenter);

    void inject(ColorsPresenter colorsPresenter);

    void inject(DrawingPresenter drawingPresenter);

    void inject(FontsPresenter fontsPresenter);

    void inject(FontPickerDialog fontPickerDialog);

    void inject(EditorSticker editorSticker);

    void inject(EditorText editorText);
}