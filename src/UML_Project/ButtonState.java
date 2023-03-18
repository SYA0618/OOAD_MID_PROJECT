package UML_Project;

public class ButtonState {
    private Canvas canvas ;
    private BasicObject[] mode ;
    private BasicObject curMode ;

    ButtonState(Canvas canvas){
        this.canvas = canvas;
        mode = new BasicObject[]{new Select(canvas),
                new Select(canvas),
                new Select(canvas),
                new Select(canvas),
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
