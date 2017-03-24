package SLogo.View;

import java.util.List;

/**
 * This class handles all math associated with the turtle moving in its environment.
 *
 * @author Riley Nisbet
 */
public class TurtleMath {

    /**
     * Takes in a location where (0,0) is the middle of the view, and returns a location where (0,0) is the top left
     *
     * @param viewWidth
     * @param viewHeight
     * @param zeroPosition
     * @return
     */
    public double[] zeroToAbsolute(double viewWidth, double viewHeight, double[] zeroPosition) {
        return new double[]{zeroPosition[0] + viewWidth / 2, -1 * (zeroPosition[1] - viewHeight / 2)};
    }

    /**
     * Takes in a location where (0,0) is the top left, and returns a location where (0,0) is the middle of the view
     *
     * @param viewWidth
     * @param viewHeight
     * @param absolutePosition
     * @return
     */
    public double[] absoluteToZero(double viewWidth, double viewHeight, double[] absolutePosition) {
        return new double[]{absolutePosition[0] - viewWidth / 2, (-1 * absolutePosition[1]) + viewHeight / 2};
    }

    /**
     * Finds the intercepts of a vector in the given view size given it's starting location
     *
     * @param viewWidth
     * @param viewHeight
     * @param x
     * @param y
     * @param xVector
     * @param yVector
     * @return a final location, potentially after a "wrap" around the screen
     */
    public double[] findIntercepts(int viewWidth, int viewHeight, double x, double y, double xVector, double yVector) {
        double[] position = absoluteToZero(viewWidth, viewHeight, new double[]{x, y});
        if (checkVerticalOrHorizontalSlope(viewWidth, viewHeight, position[0], position[1], xVector, yVector) != null) {
            return checkVerticalOrHorizontalSlope(viewWidth, viewHeight, position[0], position[1], xVector, yVector);
        }
        double m = yVector / xVector;
        double xRef = viewWidth / 2;
        double yRef = viewHeight / 2;
        if (xVector < 0) {
            xRef *= -1;
        }
        if (yVector < 0) {
            yRef *= -1;
        }
        double refVector = (position[1] - yRef) / (position[0] - xRef);
        double b = position[1] - m * position[0];
        double xInt;
        double yInt;
        if (Math.abs(m) > Math.abs(refVector)) {
            //bouncing off top or bottom
            yInt = yRef;
            xInt = (yRef - b) / m;
        } else {
            //bouncing off right or left
            xInt = xRef;
            yInt = m * (xRef) + b;
        }
        return zeroToAbsolute(viewWidth, viewHeight, new double[]{xInt, yInt});
    }

    /**
     * If the vector is Vertical or Horizontal, handle it differently to avoid dividing by zero
     *
     * @param viewWidth
     * @param viewHeight
     * @param x
     * @param y
     * @param xVector
     * @param yVector
     * @return
     */
    public double[] checkVerticalOrHorizontalSlope(int viewWidth, int viewHeight, double x, double y, double xVector, double yVector) {
        if (xVector == 0) {
            if (yVector > 0) {
                return zeroToAbsolute(viewWidth, viewHeight, new double[]{x, viewHeight / 2});
            } else {
                return zeroToAbsolute(viewWidth, viewHeight, new double[]{x, -1 * viewHeight / 2});
            }
        }
        if (yVector == 0) {
            if (xVector > 0) {
                return zeroToAbsolute(viewWidth, viewHeight, new double[]{viewWidth / 2, y});
            } else {
                return zeroToAbsolute(viewWidth, viewHeight, new double[]{0 - 1 * viewWidth / 2, y});
            }
        }
        return null;
    }

    /**
     * Adds a list of line(s) following the path of the turtle to be drawn by CanvasView
     *
     * @param viewWidth
     * @param viewHeight
     * @param currentLocation
     * @param vector
     * @param linesToMake
     */
    public void addLinesToMake(int viewWidth, int viewHeight, double[] currentLocation, double[] vector, List<double[]> linesToMake, boolean wrappedBounds) {
        //yVector is conventionally facing (+ is up), but this class uses unconventional (+ is down)
        double[] currLocation = currentLocation;
        double[] nextLocation;
        int count = 0;
        while (count < 2000) {
            count++;
            nextLocation = new double[]{currLocation[0] + vector[0], currLocation[1] - vector[1]};
            if ((nextLocation[0] > viewWidth || nextLocation[0] < 0 || nextLocation[1] > viewHeight || nextLocation[1] < 0) && wrappedBounds) {
                double[] intercepts = findIntercepts(viewWidth, viewHeight, currLocation[0], currLocation[1], vector[0], vector[1]);
                double deltaX = intercepts[0] - currLocation[0];
                double deltaY = intercepts[1] - currLocation[1];
                linesToMake.add(new double[]{currLocation[0], currLocation[1], intercepts[0], intercepts[1]});
                currLocation = intercepts;
                if (currLocation[0] == 0) {
                    currLocation[0] = viewWidth;
                } else if (currLocation[0] == viewWidth) {
                    currLocation[0] = 0;
                }
                if (currLocation[1] == 0) {
                    currLocation[1] = viewHeight;
                } else if (currLocation[1] == viewHeight) {
                    currLocation[1] = 0;
                }
                vector[0] -= deltaX;
                vector[1] += deltaY;
            } else {
                linesToMake.add(new double[]{currLocation[0], currLocation[1], nextLocation[0], nextLocation[1]});
                break;
            }
        }
    }
}
