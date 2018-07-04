package infopulse.servlets;

import infopulse.exceptions.NegativeValueException;
import infopulse.factoryOfBuilding.Factory;
import infopulse.lines.Line;
import infopulse.mainComponents.Metro;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for adding list of lines to a page
 *
 * @author Ishchenko Vladyslav
 * @since 1.0
 * @version 1.0
 */
public class GetStationsServlet extends HttpServlet {
    /**
     * Object of a subway
     */
    private Metro metro;

    /**
     * Method for adding list of lines to a page
     * @param req HTTP request
     * @param resp HTTP respond
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lines", metro.getLinesInSubway());

        req.getRequestDispatcher("lines.jsp").forward(req, resp);
    }

    /**
     * Method for initializing a subway
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        metro = new Metro();

        try {
            metro.buildNewLine(new Line(1, "Red Line", Factory.buildNewDepotOnTheLine(15)));
            metro.buildNewLine(new Line(2, "Blue Line", Factory.buildNewDepotOnTheLine(15)));
            metro.buildNewLine(new Line(3, "Green Line", Factory.buildNewDepotOnTheLine(15)));

            for(int i = 0; i < metro.countLines(); i++) {
                metro.getLinesInSubway(i).buildAllPossibleTrain();
            }

        } catch (NegativeValueException e) {
            e.printStackTrace();
        }
    }
}
