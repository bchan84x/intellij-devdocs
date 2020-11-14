package com.bchan84.intellij.devdocs;

import com.intellij.lang.Language;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class SearchAction extends AnAction {
  
  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
    CaretModel caretModel = editor.getCaretModel();
    PsiFile file = e.getData(CommonDataKeys.PSI_FILE);
    if (file != null) {
      String languageTag = file.getLanguage().getDisplayName();
      String selectedText = SearchUtils.getSelectedText(file, caretModel);
      SearchUtils.searchText(languageTag, selectedText);
    }
  }
}
