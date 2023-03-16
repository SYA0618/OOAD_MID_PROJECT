package UML_Project;

public class ButtonState {
    private Canvas canvas ;
    private BasicMode[] mode ;
    private BasicMode curMode ;

    ButtonState(Canvas canvas){
        this.canvas = canvas;
        mode = new BasicMode[]{new PaintSelct(canvas),
                new PaintSelct(canvas),
                new PaintSelct(canvas),
                new PaintSelct(canvas),
                new Paint_My_Class(canvas),
                new Paint_Use_Case(canvas)} ;
        curMode = mode[0];
        canvas.state=this;
    }
    public void setMode(int id){
        curMode = mode[id];
    }
    public BasicMode getMode(){
        return curMode;
    }
}
