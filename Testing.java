import java.util.ArrayList;
import java.util.List;

public class Testing{

    public static void main(String args[]){

        
        List<String>keywords = new ArrayList<>();
        keywords.add("add");
        keywords.add("push");
        keywords.add("pull");
        keywords.add("fetch");
        keywords.add("delete");

        Accessor obj = new Accessor(keywords) ;

        System.out.println(obj.wordSuggestion("pulh"));

        
    }
}
