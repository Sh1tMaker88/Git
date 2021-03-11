package serialization;

import api.dao.IBookDao;
import api.dao.IOrderDao;
import api.dao.IRequestDao;
import models.Book;
import models.Order;
import models.Request;
import facade.Facade;
import util.IdGenerator;

import java.io.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deserializator {


    private static final Logger LOGGER = Logger.getLogger(Deserializator.class.getName());
    private final Facade facade = Facade.getInstance();
    private final IBookDao bookDao = facade.getBookService().getBookDao();
    private final IOrderDao orderDao = facade.getOrderService().getOrderDao();
    private final IRequestDao requestDao = facade.getRequestService().getRequestDao();

    public Deserializator() {
        deserializeBooks();
        LOGGER.log(Level.INFO, "Book deserialization completed");

        deserializeRequests();
        LOGGER.log(Level.INFO, "Request deserialization completed");

        deserializeOrders();
        LOGGER.log(Level.INFO, "Order deserialization completed");
    }

    public void deserializeBooks() {
        try (ObjectInputStream inputStreamBooks = new ObjectInputStream(
                new FileInputStream("UI/src/main/resources/serializationFiles/books.bin"));
             ObjectInputStream inputStreamBooksId = new ObjectInputStream
                     (new FileInputStream("UI/src/main/resources/serializationFiles/booksId.bin"))) {
            Map<Book, Integer> idMap = (Map<Book, Integer>) inputStreamBooksId.readObject();
            while (true) {
                Object bookObj = inputStreamBooks.readObject();
                if (bookObj instanceof Book) {
                    for (Map.Entry<Book, Integer> entry : idMap.entrySet()) {
                        if (bookObj.equals(entry.getKey())) {
                            ((Book) bookObj).setId(entry.getValue());
                        }
                    }
                    bookDao.create((Book) bookObj);
                    IdGenerator.setBookId(IdGenerator.getBookId() + 1);
                }

            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }

    public void deserializeRequests() {
        try (ObjectInputStream inputStreamRequests = new ObjectInputStream
                (new FileInputStream("UI/src/main/resources/serializationFiles/requests.bin"));
             ObjectInputStream inputStreamRequestsId = new ObjectInputStream
                     (new FileInputStream("UI/src/main/resources/serializationFiles/requestsId.bin"))) {
            Map<Request, Integer> idMap = (Map<Request, Integer>) inputStreamRequestsId.readObject();
            while (true) {
                Object requestObj = inputStreamRequests.readObject();
                if (requestObj instanceof Request) {
                    for (Map.Entry<Request, Integer> entry : idMap.entrySet()) {
                        if (requestObj.equals(entry.getKey())) {
                            ((Request) requestObj).setId(entry.getValue());
                        }
                    }
                    requestDao.create((Request) requestObj);
                    IdGenerator.setRequestId(IdGenerator.getRequestId() + 1);
                }
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }

    public void deserializeOrders() {
        try (ObjectInputStream inputStreamOrders = new ObjectInputStream
                (new FileInputStream("UI/src/main/resources/serializationFiles/orders.bin"));
             ObjectInputStream inputStreamOrdersId = new ObjectInputStream
                     (new FileInputStream("UI/src/main/resources/serializationFiles/ordersId.bin"));
             ObjectInputStream inputStreamOrderBooksId = new ObjectInputStream
                     (new FileInputStream("UI/src/main/resources/serializationFiles/booksId.bin"))) {
            Map<Order, Integer> idMapOrders = (Map<Order, Integer>) inputStreamOrdersId.readObject();
            Map<Book, Integer> idMapBooks = (Map<Book, Integer>) inputStreamOrderBooksId.readObject();
            while (true) {
                Object orderObj = inputStreamOrders.readObject();
                if (orderObj instanceof Order) {
                    //setting ID to order
                    for (Map.Entry<Order, Integer> entry : idMapOrders.entrySet()) {
                        if (orderObj.equals(entry.getKey())) {
                            ((Order) orderObj).setId(entry.getValue());
                        }
                    }
                    //setting ID to books in order
                    for (Book b : ((Order) orderObj).getBooks()) {
                        for (Map.Entry<Book, Integer> entry : idMapBooks.entrySet()){
                            if (b.equals(entry.getKey())){
                                b.setId(entry.getValue());
                            }
                        }
                    }
                    orderDao.create((Order) orderObj);
                    IdGenerator.setOrderId(IdGenerator.getOrderId() + 1);
                }
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }
}