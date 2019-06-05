package TextureViewer.cg2.utils;

import com.jogamp.opengl.GL;
import java.util.ArrayList;
/*
    Found this class to solve the OpenGL context issue
    http://www.java-gaming.org/index.php?PHPSESSID=6tfcthbfls0ubmadphma5bct64&/topic,12475.msg99927.html#msg99927
 */
public class GLQueue
{
    private static GLQueue instance = null;
    private final ArrayList queue= new ArrayList(16);

    protected GLQueue(){}

    public static synchronized GLQueue getInstance()
    {
        if(instance==null) instance= new GLQueue();
        return instance;
    }

    public void add(GLAction action)
    {
        synchronized (queue) { queue.add(action); }
    }

    public void execute(GL gl)
    {
        // make a copy of the queue to allow thread safe iteration
        ArrayList temp = null;
        synchronized (queue)
        {
            // Only make a copy, if the queue has entries
            if( queue.size() != 0 )
            {
                temp = new ArrayList(queue);
                queue.clear();
            }
        }

        // iterate outside of the synchronization to avoid blocking the queue
        if( temp!=null )
        {
            for (int i=0; i < temp.size();i++)
            {
                ((GLAction) temp.get(i)).execute(gl);
            }
        }
    }
}
