package TextureViewer.cg2.listeners;

import TextureViewer.Window;
import TextureViewer.cg2.elements.Lights;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

@SuppressWarnings("ALL")
/*
    Handles the MouseInput
 */
public class MouseInputListener implements MouseListener {

    final float LightSensitivityFactor = 100.0f;
    final float RotationSensitivityFactor = 3.0f;

    private float[] _currentMouseDownPosition = new float[2];
    private float[] _lastMouseDownPosition = new float[2];

    private float[] _mouseMoveDelta = new float[2];

    private float[] transformMousePosToScreenPos(float x, float y)
    {
        float[] pos = new float[2];
        pos[0] = (2 * x / Window.getWindowWidth()) - 1f;
        pos[1] = 0.5f - (y / Window.getWindowHeight());
        return pos;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: //left
                _currentMouseDownPosition[0] = e.getY();
                _currentMouseDownPosition[1] = e.getX();
                break;
            case MouseEvent.BUTTON3: //right
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Reset all values
        _lastMouseDownPosition[0] =  e.getY();
        _lastMouseDownPosition[1] = e.getX();
        _currentMouseDownPosition[0] =  e.getY();
        _currentMouseDownPosition[1] = e.getX();
        _mouseMoveDelta[0] = 0.0f;
        _mouseMoveDelta[1] = 0.0f;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1: //left

                _lastMouseDownPosition[0] = _currentMouseDownPosition[0];
                _lastMouseDownPosition[1] = _currentMouseDownPosition[1];

                _currentMouseDownPosition[0] = e.getY();
                _currentMouseDownPosition[1] = e.getX();

                _mouseMoveDelta[0] = _currentMouseDownPosition[0] - _lastMouseDownPosition[0];
                _mouseMoveDelta[1] = _currentMouseDownPosition[1] - _lastMouseDownPosition[1];

                EventListener.setRotationVelocity(_mouseMoveDelta[0] / RotationSensitivityFactor, _mouseMoveDelta[1] / RotationSensitivityFactor);
                break;
            case MouseEvent.BUTTON3: //right
                float[] screenPos = transformMousePosToScreenPos(e.getX(), e.getY());
                Lights.setLightPosition( screenPos[0] * LightSensitivityFactor, screenPos[1] * LightSensitivityFactor);
                break;
        }
    }

    @Override
    public void mouseWheelMoved(MouseEvent mouseEvent) {

    }
}
