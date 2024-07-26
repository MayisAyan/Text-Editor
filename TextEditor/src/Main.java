import java.util.Scanner;
import java.util.Stack;

class TextEditor{
    private Stack<Character> undo = new Stack<>();
    private Stack<Character> redo = new Stack<>();

    private String text = "";
    private String finalText = "";
    public TextEditor(){
        userInterFace();
    }

    public void typeCharacter(){
        Scanner sc = new Scanner(System.in);
        text = sc.nextLine();
        for(int i = 0; i < text.length(); ++i){
            undo.push(text.charAt(i));
        }

        finalText += text;

    }

    public void undo(){
        if(!undo.isEmpty()) {
            for(int i = 0; i < text.length(); ++i){
                redo.push(text.charAt(i));
            }
            undo.pop();
            char[] afterOperation = new char[undo.size()];
            for(int i = 0; i < afterOperation.length; ++i){
                afterOperation[i] = undo.get(i);
            }
            text = new String(afterOperation);
        }
    }

    public void redo(){
        if(!redo.isEmpty()){
            char[] afterOperation = new char[redo.size()];
            for(int i = 0; i < afterOperation.length; ++i){
                afterOperation[i] = redo.get(i);
            }
            undo.push(redo.pop());
            text = new String(afterOperation);
        }
    }
    public void viewText(){
        System.out.println(text);
    }

    private void userInterFace(){

        while(true) {
            System.out.println("""
                    Enter 1 to type text.
                    Enter 2 to undo.
                    Enter 3 to redo.
                    Enter 4 to view text.
                    Enter 0 to exit.
                    """);
            String operation = "-1";
            Scanner sc = new Scanner(System.in);
            operation = sc.nextLine();
            switch (operation) {
                case "1" -> typeCharacter();
                case "2" -> undo();
                case "3" -> redo();
                case "4" -> viewText();
                case "0" -> System.exit(0);
                default -> System.out.println("Invalid operation");
            }
        }
    }
}
class Main{
    public static void main(String... args){
        TextEditor tE = new TextEditor();
    }
}