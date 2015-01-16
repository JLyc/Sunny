package ui_creator;

/**
 * Created by socha on 9.10.2014.
 */
public class SunnyGui implements Runnable{

    private static final  SunnyGui INSTANCE = new SunnyGui();

    @Deprecated
    public static void main(String[] args) {
        init();
    }

    public static void init()
    {
        INSTANCE.prepearGuiToLoad();
    }

    private void prepearGuiToLoad()
    {
        Thread guiThread = new Thread(INSTANCE);
        guiThread.setName("GUI-thread");
        javax.swing.SwingUtilities.invokeLater(guiThread);
    }

    @Override
    public void run() {
        new GuiBuilder();
    }
}
