package components;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import static java.lang.Math.ceil;

public class PaginationComponent {

    private int cursor;
    private int totalItems;
    private int itemsPerPage;
    private int totalPages;

    public PaginationComponent(int totalItems, int itemsPerPage){
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;

        cursor = 0;
        totalPages = (int) Math.ceil((double) totalItems / (double) itemsPerPage);
    }

    public void render(Pane paginationPane, PaginationPageHandler handler){
        if(totalPages  == 0)
            return;

        paginationPane.getChildren().clear();
        ArrayList<Button> pageButtons = new ArrayList<>();
        for(int i = 0; i< totalPages; i++){
            final int page = i;
            Button button = new Button(Integer.toString(page + 1));
            button.setOnAction(e ->{
                handler.run(page);
                pageButtons.forEach(b -> b.getStyleClass().remove("active-page"));
                button.getStyleClass().add("active-page");
                cursor = page;
            });
            pageButtons.add(button);
        }
        pageButtons.get(cursor).getStyleClass().add("active-page");

        Button prevButton = new Button("<");
        prevButton.setOnAction(e ->{
            if(cursor -1 < 0)
                return;
            cursor--;

            handler.run(cursor);
            pageButtons.forEach(b -> b.getStyleClass().remove("acitve-page"));
            pageButtons.get(cursor).getStyleClass().add("active-page");
        });

        Button nextButton = new Button(">");
        nextButton.setOnAction(e ->{
            if(cursor + 1 >= totalPages)
                return;
            cursor++;

            handler.run(cursor);
            pageButtons.forEach(b ->
                    b.getStyleClass().remove("active-page"));
            pageButtons.get(cursor).getStyleClass().add("active-page");
        });

        paginationPane.getChildren().add(prevButton);
        paginationPane.getChildren().addAll(pageButtons);
        paginationPane.getChildren().addAll(nextButton);


    }

    public int getCursor(){
        return cursor;
    }



    public interface PaginationPageHandler{
        public void run(int page);
    }

}
