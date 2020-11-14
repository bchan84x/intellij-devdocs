package com.bchan84.intellij.devdocs;

import com.intellij.ide.BrowserUtil;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class SearchUtils {
  
  private static final String QUERY_URL = "https://devdocs.io/#q=";
  
  public static void checkSupportedLanguages() {
    Collection<Language> languages = Language.getRegisteredLanguages();
    for (Language language : languages) {
      System.out.println(language.getDisplayName());
    }
  }
  
  public static String getSelectedText(PsiFile file, @NotNull CaretModel caretModel) {
    if (caretModel.getCurrentCaret().hasSelection()) {
      return caretModel.getCurrentCaret().getSelectedText();
    }
    else {
      int offset = caretModel.getCurrentCaret().getOffset();
      PsiElement element = file.findElementAt(offset);
      return element != null ? element.getText() : "";
    }
  }
  
  public static void searchText(String languageTag, String selectedText) {
    if (selectedText != null && !selectedText.equals("")) {
      String query = processLanguageTag(languageTag) + selectedText;
      BrowserUtil.browse(QUERY_URL + query);
    }
  }
  
  public static void searchAllText(String selectedText) {
    if (selectedText != null && !selectedText.equals("")) {
      BrowserUtil.browse(QUERY_URL + selectedText);
    }
  }
  
  private static String processLanguageTag(@NotNull String languageTag) {
    switch (languageTag) {
      case "Angular2":
      case "AngularJS":
        return "angular ";
      
      case "Shell Script":
        return "bash ";
        
      case "CSS":
        return "css ";
      
      case "CoffeeScript":
      case "CoffeeScriptLiterate":
        return "coffeescript ";
        
      case "Dockerfile":
        return "docker ";
        
      case "Flow JS":
        return "flow ";
        
      case "HTML":
      case "XHTML":
        return "html ";
        
      case "Java":
      case "JSP":
      case "JSPX":
        return "java ";
        
      case "ECMA Script Level 4":
      case "ECMAScript 6":
      case "JavaScript":
      case "JavaScript 1.5":
      case "JavaScript 1.8":
        return "js ";
      
      case "Kotlin":
      case "Kotlin/Native Def":
        return "kotlin ";
        
      case "Less":
        return "less ";
        
      case "Generic SQL":
      case "MariaDB":
      case "Microsoft SQL Server":
      case "MySQL":
      case "MySQL based":
      case "Oracle":
      case "SQL":
        return "mariadb ";
        
      case "Markdown":
        return "markdown ";
        
      case "MongoJS":
        return "mongoose ";
        
      case "PostgreSQL":
        return "postgresql ";
        
      case "Sass":
      case "SCSS":
        return "sass ";
        
      case "SQLite":
        return "sqlite ";
      
      case "SVG":
        return "svg ";
        
      case "TypeScript":
        return "typescript ";
        
      case "XPath":
      case "XPath2":
        return "xpath ";
        
      default:
        return "";
    }
  }
}
