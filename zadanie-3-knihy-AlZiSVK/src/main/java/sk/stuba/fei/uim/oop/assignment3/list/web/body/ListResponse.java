package sk.stuba.fei.uim.oop.assignment3.list.web.body;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendList;

import java.util.List;

@Getter
public class ListResponse {
    private final Long id;
    private final List<Book> lendingList;
    private final boolean lended;

    public ListResponse(LendList lendingListObject){
        this.id = lendingListObject.getId();
        this.lendingList = lendingListObject.getLendingList();
        this.lended = lendingListObject.isLended();
    }
}
