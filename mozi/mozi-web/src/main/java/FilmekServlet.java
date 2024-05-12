
import hu.alkfejl.dao.FilmDAO;
import hu.alkfejl.dao.FilmDAOImpl;
import hu.alkfejl.model.Film;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FilmekServlet")
public class FilmekServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        FilmDAO filmDAO = new FilmDAOImpl();
        List<Film> filmek = new ArrayList<>();

        filmek = filmDAO.findAll();
        System.out.println(filmek.get(1));
        req.setAttribute("films", filmek);
    }
}
