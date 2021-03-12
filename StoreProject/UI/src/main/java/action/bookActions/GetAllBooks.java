package action.bookActions;

import exceptions.DaoException;
import models.Book;
import facade.Facade;
import exceptions.ActionException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetAllBooks {

    private static final Logger LOGGER = Logger.getLogger(GetAllBooks.class.getName());
    final Facade facade = Facade.getInstance();

    public List<Book> doIt() {
        try {
            return facade.getBookService().getBookDao().getAll();
        } catch (DaoException e){
            LOGGER.log(Level.WARNING, "Method doIt from class GetAllBooks failed");
            throw new ActionException("Method doIt failed", e);
        }
    }
}