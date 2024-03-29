package UML_Project;

public class ButtonState {
    private final BasicObject[] mode ;
    private BasicObject curMode ;

    ButtonState(Canvas canvas){
        mode = new BasicObject[]{new Select(canvas),
                new Association_Line1(canvas),
                new GeneralizationLine1(canvas),
                new CompositionLine1(canvas),
                new Paint_My_Class(canvas),
                new Paint_Use_Case(canvas)} ;
        curMode = mode[0];
        canvas.state = this;
    }
    public void setMode(int id){
        curMode = mode[id];
    }
    public BasicObject getMode(){
        return curMode;
    }
}
