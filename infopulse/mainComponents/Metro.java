package infopulse.mainComponents;

import infopulse.lines.Line;

import java.util.ArrayList;

/**
 * Class describe all subway
 *
 * @author Ishchenko Vladyslav
 * @version 1.0
 * @since 1.0
 */
public class Metro {
    /**
     * Name of a subway
     */
    public static final String name = "Kiev subway";

    /**
     * List of lines
     */
    private ArrayList<Line> linesInSubway;

    /**
     * Default constructor
     */
    public Metro() {
        linesInSubway = new ArrayList<>();
    }

    /**
     * Method for adding new line in subway
     *
     * @param line Line in subway
     */
    public void buildNewLine(Line line) {
        linesInSubway.add(line);
    }

    /**
     * Getter for lines in the subway
     *
     * @param i number of a line
     * @return line in subway
     */
    public Line getLinesInSubway(int i) {
        return linesInSubway.get(i);
    }

    /**
     * Method for giving count of lines in subway
     *
     * @return Count of lines in subway
     */
    public int countLines() {
        return linesInSubway.size();
    }

    /**
     * Getter for list of lines
     *
     * @return lines in subway
     */
    public ArrayList<Line> getLinesInSubway() {
        return linesInSubway;
    }

    /**
     * Setter for list of lines
     *
     * @param linesInSubway list of lines
     */
    public void setLinesInSubway(ArrayList<Line> linesInSubway) {
        this.linesInSubway = linesInSubway;
    }

    /**
     * Getter for line by name
     *
     * @param name name of the Line
     * @return line with such name
     */
    public Line getLine(String name) {
        for (Line line : linesInSubway) {
            if (name.equals(line.getName())) {
                return line;
            }
        }

        return null;
    }
}
