package TextureViewer.cg2.utils;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.util.texture.Texture;

public interface GLAction
{
    public Texture execute(GL target);
}
