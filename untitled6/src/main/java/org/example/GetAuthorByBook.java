package org.example;

import java.util.List;
import java.util.Map;

public class GetAuthorByBook {
  public String getAuthorNameByBookTitle(Map<Author, List<Book>> authorBooks, String bookTitle) {
    for (Map.Entry<Author, List<Book>> authorListEntry : authorBooks.entrySet()) {
      for (int i= 0; i < authorListEntry.getValue().size(); i++) {
        if (authorListEntry.getValue().get(i).getTitle().equals(bookTitle)) {
          return authorListEntry.getKey().getName();
        }
      }
    }
    return null;
  }
}
