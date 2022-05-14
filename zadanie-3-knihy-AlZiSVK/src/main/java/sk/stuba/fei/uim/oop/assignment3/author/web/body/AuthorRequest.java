package sk.stuba.fei.uim.oop.assignment3.author.web.body;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class AuthorRequest {
    private Long id;
    private String name;
    private String surname;
    private List<Long> books;
}
