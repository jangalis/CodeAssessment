import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class ControlServlet extends HttpServlet {
    private Reader reader;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
            reader = new Reader();
        } catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("queryBtn") != null) {
            if (request.getParameter("queryText") != null) {
                String queryText = request.getParameter("queryText");
                request.setAttribute("table1", reader.getCountryAirports(queryText));
                request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
            }
        } else {
            if (request.getParameter("reportsBtn") != null) {
                request.setAttribute("table2", reader.getAirportsQuantityTables());
                request.setAttribute("table3", reader.getSurfacesTable());

                request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
            }
        }
    }
}
